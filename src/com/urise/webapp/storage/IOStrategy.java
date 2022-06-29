package com.urise.webapp.storage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IOStrategy {
    void write(ObjectOutputStream oss);

    void read(ObjectInputStream ois);
}
