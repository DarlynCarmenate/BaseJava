package ru.javawebinar.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

class MapStorageTest extends AbstractStorageTest {

    protected MapStorageTest() {
        super(new MapStorage());
    }
}