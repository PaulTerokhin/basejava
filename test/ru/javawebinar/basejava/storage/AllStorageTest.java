package ru.javawebinar.basejava.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ArrayStorageTest.class, SortedArrayStorageTest.class, ListStorageTest.class,
        MapUuidStorageTest.class, MapResumeStorageTest.class, ObjectPathStorageTest.class, ObjectFileStorageTest.class,
        XmlPathStorageTest.class})

public class AllStorageTest {
}
