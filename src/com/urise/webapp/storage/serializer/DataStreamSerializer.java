package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.Month;
import java.util.Collection;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {

            writeWithException(dos, r.getContacts().entrySet(), writer -> {
                dos.writeUTF(writer.getKey().name());
                dos.writeUTF(writer.getValue());
            });

            writeWithException(dos, r.getSections().entrySet(), writer -> {
                if (writer.getValue() instanceof TextSection) {
                    dos.writeUTF(writer.getKey().name());
                    dos.writeUTF(((TextSection) writer.getValue()).getText());
                }
                if (writer.getValue() instanceof ListSection) {
                    dos.writeUTF(writer.getKey().name());
                    for (String string : ((ListSection) writer.getValue()).getStrings()) {
                        dos.writeUTF(string);
                    }
                }
                if (writer.getValue() instanceof OrganizationSection) {
                    dos.writeUTF(writer.getKey().name());
                    for (Organization organization : ((OrganizationSection) writer.getValue()).getOrganizations()) {
                        dos.writeUTF(organization.getHomePage().getName());
                        if (organization.getHomePage().getUrl() != null) {
                            dos.writeUTF(organization.getHomePage().getUrl());
                        }
                        for (Organization.Period period : organization.getListPeriods()) {
                            dos.writeUTF(period.getStartDate().toString());
                            dos.writeUTF(period.getEndDate().toString());
                            dos.writeUTF(period.getPosition());
                            if (period.getDescription() != null) {
                                dos.writeUTF(period.getDescription());
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeS = dis.readInt();
            for (int i = 0; i < sizeS; i++) {
                resume.addSection(SectionType.valueOf(dis.readUTF()), (new TextSection(dis.readUTF())));
                resume.addSection(SectionType.valueOf(dis.readUTF()), (new ListSection(dis.readUTF())));
                resume.addSection(SectionType.valueOf(dis.readUTF()), (new OrganizationSection(new Organization(
                        dis.readUTF(), dis.readUTF(), new Organization.Period(Integer.parseInt(dis.readUTF()), Month.of(Integer.parseInt(dis.readUTF())),
                        dis.readUTF(), dis.readUTF())))));
            }
            return resume;
        }
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, WriteCollection<T> write)
            throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            write.write(t);
        }
    }
}

interface WriteCollection<T> {
    void write(T t) throws IOException;
}