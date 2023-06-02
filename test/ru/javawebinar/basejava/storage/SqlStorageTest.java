package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes","postgres","password"));
    }
}