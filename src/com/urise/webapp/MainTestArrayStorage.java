package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapResumeStorage;

import java.util.Arrays;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final MapResumeStorage ARRAY_STORAGE = new MapResumeStorage(); //new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "1");
        Resume r2 = new Resume("uuid2", "3");
        Resume r3 = new Resume("uuid3", "2");
        Resume r4 = new Resume("uuid4", "0");
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        //Arrays.sort(ARRAY_STORAGE,);
        //System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        //System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.println(ARRAY_STORAGE.getAllSorted());
        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println(ARRAY_STORAGE.getAllSorted());
        ARRAY_STORAGE.delete(r2.getUuid());
        System.out.println(ARRAY_STORAGE.getAllSorted());
        ARRAY_STORAGE.delete(r3.getUuid());
        System.out.println(ARRAY_STORAGE.getAllSorted());
        ARRAY_STORAGE.clear();
        System.out.println(ARRAY_STORAGE.getAllSorted());

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r.getUuid() + " " + r.getFullName());
        }
    }
}
