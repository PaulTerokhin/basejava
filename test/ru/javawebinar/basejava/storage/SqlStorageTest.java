package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}