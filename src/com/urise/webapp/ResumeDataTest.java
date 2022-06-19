package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeDataTest {
    private static final String PHONE = "+7(921) 855-0482";
    private static final String SKYPE = "skype:grigory.kislin";
    private static final String EMAIL = "gkislin@yandex.ru";
    private static final String LINKEDIN = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB = "https://github.com/gkislin";
    private static final String STACKOVERFLOW = "\"https://stackoverflow.com/users/548473/grigory-kislin\"";
    private static final String PAGE = "http://gkislin.ru/";

    private static final TextSection OBJECTIVE = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
            "Enterprise технологиям");
    private static final TextSection PERSONAL = new TextSection("Аналитический склад ума, сильная логика, креативность, " +
            "инициативность. Пурист кода и архитектуры.");

    private static final List<String> STRINGS_ACHIEVEMENT = new ArrayList<>();
    private static final ListSection ACHIEVEMENT = new ListSection(STRINGS_ACHIEVEMENT);

    private static final List<String> STRINGS_QUALIFICATION = new ArrayList<>();
    private static final ListSection QUALIFICATION = new ListSection(STRINGS_QUALIFICATION);

    private static final Period PERIOD_EXPERIENCE_1 = new Period("10/2013 - Now", "Автор проекта",
            "Создание, организация и проведение Java онлайн проектов и стажировок.)");
    private static final Organization ORGANIZATION_EXPERIENCE_1 = new Organization("Java Online Projects",
            "https://www.https://javaops.ru/.com/", PERIOD_EXPERIENCE_1);

    private static final Period PERIOD_EXPERIENCE_2 = new Period("10/2014 - 01/2016", "Старший разработчик " +
            "(backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
            " Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация " +
            "по OAuth1, OAuth2, JWT SSO.");
    private static final Organization ORGANIZATION_EXPERIENCE_2 = new Organization("Wrike",
            "https://www.wrike.com/", PERIOD_EXPERIENCE_2);

    private static final List<Organization> EXPERIENCE_LIST = new ArrayList<>();
    private static final OrganizationSection EXPERIENCE = new OrganizationSection(EXPERIENCE_LIST);

    private static final Period PERIOD_EDUCATION_1 = new Period("03/2013 - 05/2013", null,
            "'Functional Programming Principles in Scala' by Martin Odersky");
    private static final Organization ORGANIZATION_EDUCATION_1 = new Organization("Coursera",
            "https://www.coursera.org/learn/scala-functional-programming", PERIOD_EDUCATION_1);

    private static final Period PERIOD_EDUCATION_2 = new Period("03/2011 - 04/2011", null,
            "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
    private static final Organization ORGANIZATION_EDUCATION_2 = new Organization("Luxoft",
            "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html", PERIOD_EDUCATION_2);

    private static final List<Organization> EDUCATION_LIST = new ArrayList<>();
    private static final OrganizationSection EDUCATION = new OrganizationSection(EDUCATION_LIST);

    public static void main(String[] args) {
        Resume resume1 = new Resume("Григорий Кислин");
        System.out.println(resume1.getFullName());

        resume1.contactFields.put(ContactType.PHONE, PHONE);
        resume1.contactFields.put(ContactType.SKYPE, SKYPE);
        resume1.contactFields.put(ContactType.EMAIL, EMAIL);
        resume1.contactFields.put(ContactType.LINKEDIN, LINKEDIN);
        resume1.contactFields.put(ContactType.GITHUB, GITHUB);
        resume1.contactFields.put(ContactType.STACKOVERFLOW, STACKOVERFLOW);
        resume1.contactFields.put(ContactType.PAGE, PAGE);

        resume1.sectionFields.put(SectionType.OBJECTIVE, OBJECTIVE);
        resume1.sectionFields.put(SectionType.PERSONAL, PERSONAL);

        STRINGS_ACHIEVEMENT.add("\nОрганизация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на" +
                " Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
                "комплексных DIY смет");
        STRINGS_ACHIEVEMENT.add("\nС 2013 года: разработка проектов Разработка Web приложения,Java Enterprise," +
                " Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA). Организация онлайн стажировок и ведение проектов." +
                " Более 3500 выпускников.");
        resume1.sectionFields.put(SectionType.ACHIEVEMENT, ACHIEVEMENT);

        STRINGS_QUALIFICATION.add("\nJEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        STRINGS_QUALIFICATION.add("\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce");
        STRINGS_QUALIFICATION.add("\nDB: PostgresSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
                "MySQL, SQLite, MS SQL, HSQLDB");
        resume1.sectionFields.put(SectionType.QUALIFICATIONS, QUALIFICATION);

        EXPERIENCE_LIST.add(ORGANIZATION_EXPERIENCE_1);
        EXPERIENCE_LIST.add(ORGANIZATION_EXPERIENCE_2);
        resume1.sectionFields.put(SectionType.EXPERIENCE, EXPERIENCE);

        EDUCATION_LIST.add(ORGANIZATION_EDUCATION_1);
        EDUCATION_LIST.add(ORGANIZATION_EDUCATION_2);
        resume1.sectionFields.put(SectionType.EDUCATION, EDUCATION);

        for (Map.Entry<ContactType, String> item : resume1.contactFields.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> item : resume1.sectionFields.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
