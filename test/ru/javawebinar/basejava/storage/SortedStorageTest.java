package ru.javawebinar.basejava.storage;

class SortedStorageTest extends AbstractStorageTest {

    protected SortedStorageTest() {
        super(new SortedArrayStorage());
    }
}