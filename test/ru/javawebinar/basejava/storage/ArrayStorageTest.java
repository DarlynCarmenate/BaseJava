package ru.javawebinar.basejava.storage;

class ArrayStorageTest extends AbstractArrayStorageTest{

    Storage storage = new ArrayStorage();

    public ArrayStorageTest() {
        this.storage.save(RESUME_1);
        this.storage.save(RESUME_2);
        this.storage.save(RESUME_3);
    }
}