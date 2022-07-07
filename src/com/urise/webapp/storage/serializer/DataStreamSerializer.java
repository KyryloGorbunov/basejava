package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.Month;
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

            writeWithException(dos, contacts.entrySet(), writer -> {
                dos.writeUTF(writer.getKey().name());
                dos.writeUTF(writer.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();

            writeWithException(dos, sections.entrySet(), writer -> {

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
                        if (organization.getHomePage().getUrl() == null) {
                            dos.writeUTF("null");
                        } else {
                            dos.writeUTF(organization.getHomePage().getUrl());
                        }
                        for (Organization.Period period : organization.getListPeriods()) {
                            dos.writeUTF(period.getStartDate().toString());
                            dos.writeUTF(period.getEndDate().toString());
                            dos.writeUTF(period.getPosition());
                            if (period.getDescription() == null) {
                                dos.writeUTF("null");
                            } else {
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

            readWithException(dis, resume.getContacts().entrySet(), reader ->
                    resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, resume.getSections().entrySet(), reader ->
                    resume.addSection(SectionType.valueOf(dis.readUTF()), readSection(reader.getValue(), dis)));
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

    private <T> void readWithException(DataInputStream dis, Collection<T> collection, ReadCollection<T> read)
            throws IOException {
        dis.readInt();
        for (T t : collection) {
            read.read(t);
        }
    }

    private Section readSection(Object object, DataInputStream dis) throws IOException {
        Section section = null;
        
        if (object instanceof TextSection) {
          section = new TextSection(dis.readUTF());
        }
        if (object instanceof ListSection) {
           section =  new ListSection(dis.readUTF());
        }
        if (object instanceof OrganizationSection) {
          section = new OrganizationSection(readOrganizationSection(dis, object));
        }
        return section;
    }

    private List<Organization> readOrganizationSection(DataInputStream dis, Object object) throws IOException {
        List<Organization> organizations = new ArrayList<>();
        if (object instanceof Organization) {
            
            Link link = new Link(dis.readUTF(), dis.readUTF());
            if (link.getUrl().equals("null")) {
                link.setUrl(null);
            }
            
            Organization.Period period = new Organization.Period(dis.readInt(), Month.of(dis.readInt()), dis.readUTF(), dis.readUTF());
            if (period.getDescription().equals("null")) {
                period.setDescription(null);
            }

            List<Organization.Period> periods = new ArrayList<>();
            periods.add(period);
            
            Organization organization = new Organization(link, periods);
            
            organizations.add(organization);
        }
        return organizations;
    }
}

interface WriteCollection<T> {
    void write(T t) throws IOException;
}

interface ReadCollection<T> {
    void read(T t) throws IOException;
}