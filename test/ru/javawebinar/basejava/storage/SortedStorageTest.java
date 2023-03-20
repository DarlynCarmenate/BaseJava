package ru.javawebinar.basejava.storage;

class SortedStorageTest extends AbstractArrayStorageTest {

    protected SortedStorageTest() {
        super(new SortedArrayStorage());
    }
}