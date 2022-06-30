package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFail {

    static String repeat(int n, String value) {
        return new String(new char[n]).replace("\0", value);
    }

    static void doRecurs(File dir, int level) {
        final String indent = repeat(level, "   ");

        if (dir.isDirectory()) {
            File[] list = dir.listFiles();
            if (list == null)
                return;

            for (File name : list) {
                if (name.isFile()) {
                    System.out.println(indent + name.getName());
                } else {
                    System.out.println(indent + name.getName());
                    doRecurs(name, level + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        doRecurs(dir, 0);
    }
}
