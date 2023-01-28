package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayStorageTest extends AbstractArrayStorageTest{

    Storage storage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super();
        System.out.println(storage.toString());
    }
}