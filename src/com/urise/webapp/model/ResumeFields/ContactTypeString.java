package com.urise.webapp.model.ResumeFields;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactTypeString {
    public Map<ContactType, String> contactMap = new LinkedHashMap<>();

    public void save(ContactType contactType, String string) {
        contactMap.put(contactType, string);
    }

    public void getSectionType() {
        for (Map.Entry<ContactType, String> item : contactMap.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
