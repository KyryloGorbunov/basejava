package com.urise.webapp.storage;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "alan040520"));
    }
}
