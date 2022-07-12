package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, ((ListSection) section).getStrings(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollection(dos, ((OrganizationSection) section).getOrganizations(), org -> {
                        dos.writeUTF(org.getHomePage().getName());
                        dos.writeUTF(org.getHomePage().getUrl());
                        writeCollection(dos, org.getListPeriods(), period -> {
                            writeLocalDate(dos, period.getStartDate());
                            writeLocalDate(dos, period.getEndDate());
                            dos.writeUTF(period.getPosition());
                            dos.writeUTF(period.getDescription());
                        });
                    });
                }
            });
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(
                    readList(dis, () -> new Organization(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readList(dis, () -> new Organization.Period(
                                    readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                            ))
                    )));
        };
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }
}

//    @Override
//    public void doWrite(Resume r, OutputStream os) throws IOException {
//        try (DataOutputStream dos = new DataOutputStream(os)) {
//            dos.writeUTF(r.getUuid());
//            dos.writeUTF(r.getFullName());
//
//            Map<ContactType, String> contacts = r.getContacts();
//
//            writeWithException(dos, contacts.entrySet(), writer -> {
//                dos.writeUTF(writer.getKey().name());
//                dos.writeUTF(writer.getValue());
//            });
//
//            writeWithException(dos, r.getSections().entrySet(), writer -> {
//                SectionType type = writer.getKey();
//                Section section = writer.getValue();
//                dos.writeUTF(type.name());
//                switch (type) {
//                    case PERSONAL, OBJECTIVE -> {
//                        dos.writeUTF(((TextSection) section).getText());
//                    }
//                    case ACHIEVEMENT, QUALIFICATIONS -> {
//                        for (String string : ((ListSection) section).getStrings()) {
//                            dos.writeUTF(string);
//                        }
//                    }
//                    case EXPERIENCE, EDUCATION -> {
//                        for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
//                            dos.writeUTF(organization.getHomePage().getName());
//                            dos.writeUTF(organization.getHomePage().getUrl());
//                            for (Organization.Period period : organization.getListPeriods()) {
//                                writeLocalDate(dos, period.getStartDate());
//                                writeLocalDate(dos, period.getEndDate());
//                                dos.writeUTF(period.getPosition());
//                                dos.writeUTF(period.getDescription());
//                            }
//                        }
//                    }
//                    default -> throw new IllegalStateException("Unexpected value: " + type);
//                }
//            });
//        }
//    }
//
//    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
//        dos.writeInt(ld.getYear());
//        dos.writeInt(ld.getMonth().getValue());
//    }
//
//    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
//        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
//    }
//
//    @Override
//    public Resume doRead(InputStream is) throws IOException {
//        try (DataInputStream dis = new DataInputStream(is)) {
//            String uuid = dis.readUTF();
//            String fullName = dis.readUTF();
//            Resume resume = new Resume(uuid, fullName);
//            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
//            readWithException(dis, () -> {
//                SectionType sectionType = SectionType.valueOf(dis.readUTF());
//                resume.addSection(sectionType, readSection(sectionType, dis));
//            });
//            return resume;
//        }
//    }
//
//    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, WriteCollection<T> write)
//            throws IOException {
//        dos.writeInt(collection.size());
//        for (T t : collection) {
//            write.write(t);
//        }
//    }
//
//    private Section readSection(SectionType sectionType, DataInputStream dis) throws IOException {
//
//        return switch (sectionType) {
//            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
//            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
//            case EXPERIENCE, EDUCATION -> new OrganizationSection(
//                    readList(dis, () -> new Organization(
//                            new Link(dis.readUTF(), dis.readUTF()),
//                            readList(dis, () -> new Organization.Period(
//                                    readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
//                            ))
//                    )));
//        };
//    }
//
//    private <T> List<T> readList(DataInputStream dis, ReadCollection<T> readCollection) throws IOException {
//        int size = dis.readInt();
//        List<T> list = new ArrayList<>(size);
//        for (int i = 0; i < size; i++) {
//            list.add(readCollection.read());
//        }
//        return list;
//    }
//
//    private void readWithException(DataInputStream dis, ReadIterator readIterator) throws IOException {
//        int size = dis.readInt();
//        for (int i = 0; i < size; i++) {
//            readIterator.readWithIterator();
//        }
//    }
//}
//
//interface WriteCollection<T> {
//    void write(T t) throws IOException;
//}
//
//interface ReadCollection<T> {
//    T read() throws IOException;
//}
//
//interface ReadIterator {
//    void readWithIterator() throws IOException;
//}

