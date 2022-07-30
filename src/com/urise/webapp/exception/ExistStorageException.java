package com.urise.webapp.exception;

import org.postgresql.util.PSQLException;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist.", uuid);
    }

    public ExistStorageException(PSQLException e) {
        super(e.getMessage());
    }
}
