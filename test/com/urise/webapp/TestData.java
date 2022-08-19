package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String UUID_NOT_EXIST = UUID.randomUUID().toString();

    public static final ResumeDataTest RESUME = new ResumeDataTest();
    public static final Resume R_1 = RESUME.getResume(UUID_1, "Name1");
    public static final Resume R_2 = RESUME.getResume(UUID_2, "Name2");
    public static final Resume R_3 = RESUME.getResume(UUID_3, "Name3");
    public static final Resume R_4 = RESUME.getResume(UUID_4, "Name4");

//    private static final Resume R_1 = new Resume(UUID_1, "Name1");
//    private static final Resume R_2 = new Resume(UUID_2, "Name2");
//    private static final Resume R_3 = new Resume(UUID_3, "Name3");
//    private static final Resume R_4 = new Resume(UUID_4, "Name4");

}
