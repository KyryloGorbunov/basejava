package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapResumeStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final MapResumeStorage ARRAY_STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Name1");
        Resume r2 = new Resume("uuid2", "Name2");
        Resume r3 = new Resume("uuid3", "Name3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r2);
        System.out.println("Get r2: " + ARRAY_STORAGE.get(r2.getUuid()));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
