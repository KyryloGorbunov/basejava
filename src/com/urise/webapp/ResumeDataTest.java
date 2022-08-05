package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

import java.util.Map;

public class ResumeDataTest {
    private static final String PHONE = "+7(921) 855-0482";
    private static final String SKYPE = "skype:grigory.kislin";
    private static final String EMAIL = "gkislin@yandex.ru";
    private static final String LINKEDIN = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB = "https://github.com/gkislin";
    private static final String STACKOVERFLOW = "\"https://stackoverflow.com/users/548473/grigory-kislin\"";
    private static final String PAGE = "http://gkislin.ru/";

//    private static final TextSection OBJECTIVE = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и " +
//            "Enterprise технологиям");
//    private static final TextSection PERSONAL = new TextSection("Аналитический склад ума, сильная логика, креативность, " +
//            "инициативность. Пурист кода и архитектуры.");
//
//    private static final List<String> STRINGS_ACHIEVEMENT = new ArrayList<>();
//    private static final ListSection ACHIEVEMENT = new ListSection(STRINGS_ACHIEVEMENT);
//
//    private static final List<String> STRINGS_QUALIFICATION = new ArrayList<>();
//    private static final ListSection QUALIFICATION = new ListSection(STRINGS_QUALIFICATION);
//
//    private static final Organization ORGANIZATION_EXPERIENCE_1 = new Organization("Java Online Projects",
//            "https://www.https://javaops.ru/.com/", new Organization.Period(DateUtil.of(2013, Month.NOVEMBER), DateUtil.NOW,
//            "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.)"));
//
//    private static final Organization ORGANIZATION_EXPERIENCE_2 = new Organization("Wrike",
//            "https://www.wrike.com/", new Organization.Period(DateUtil.of(2014, Month.NOVEMBER), DateUtil.of(2016, Month.JANUARY),
//            "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike " +
//            "(Java 8 API Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
//            "авторизация по OAuth1, OAuth2, JWT SSO."));
//
//    private static final List<Organization> EXPERIENCE_LIST = new ArrayList<>();
//    private static final OrganizationSection EXPERIENCE = new OrganizationSection(EXPERIENCE_LIST);
//
//    private static final Organization ORGANIZATION_EDUCATION_1 = new Organization("Coursera",
//            "https://www.coursera.org/learn/scala-functional-programming", new Organization.Period(DateUtil.of(2013, Month.MARCH),
//            DateUtil.of(2013, Month.MAY), "'Functional Programming Principles in Scala' by Martin Odersky", null));
//
//    private static final Organization ORGANIZATION_EDUCATION_2 = new Organization("Luxoft",
//            "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
//            new Organization.Period(DateUtil.of(2011, Month.MARCH), DateUtil.of(2011, Month.APRIL), "Курс 'Объектно-ориентированный" +
//                    " анализ ИС. Концептуальное моделирование на UML.'", null));
//
//    private static final List<Organization> EDUCATION_LIST = new ArrayList<>();
//    private static final OrganizationSection EDUCATION = new OrganizationSection(EDUCATION_LIST);
//
    public Resume getResume(String uuid, String fullName) {
        Resume resume1 = new Resume(uuid, fullName);
        System.out.println(resume1.getFullName());

        resume1.contacts.put(ContactType.PHONE, PHONE);
        resume1.contacts.put(ContactType.SKYPE, SKYPE);
        resume1.contacts.put(ContactType.EMAIL, EMAIL);
        resume1.contacts.put(ContactType.LINKEDIN, LINKEDIN);
        resume1.contacts.put(ContactType.GITHUB, GITHUB);
        resume1.contacts.put(ContactType.STACKOVERFLOW, STACKOVERFLOW);
        resume1.contacts.put(ContactType.HOME_PAGE, PAGE);
//
//        resume1.sections.put(SectionType.OBJECTIVE, OBJECTIVE);
//        resume1.sections.put(SectionType.PERSONAL, PERSONAL);
//
//        STRINGS_ACHIEVEMENT.add("\nОрганизация команды и успешная реализация Java проектов для сторонних заказчиков: " +
//                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на" +
//                " Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
//                "комплексных DIY смет");
//        STRINGS_ACHIEVEMENT.add("\nС 2013 года: разработка проектов Разработка Web приложения,Java Enterprise," +
//                " Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
//                "Удаленное взаимодействие (JMS/AKKA). Организация онлайн стажировок и ведение проектов." +
//                " Более 3500 выпускников.");
//        resume1.sections.put(SectionType.ACHIEVEMENT, ACHIEVEMENT);
//
//        STRINGS_QUALIFICATION.add("\nJEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//        STRINGS_QUALIFICATION.add("\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce");
//        STRINGS_QUALIFICATION.add("\nDB: PostgresSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
//                "MySQL, SQLite, MS SQL, HSQLDB");
//        resume1.sections.put(SectionType.QUALIFICATIONS, QUALIFICATION);
//
//        EXPERIENCE_LIST.add(ORGANIZATION_EXPERIENCE_1);
//        EXPERIENCE_LIST.add(ORGANIZATION_EXPERIENCE_2);
//        resume1.sections.put(SectionType.EXPERIENCE, EXPERIENCE);
//
//        EDUCATION_LIST.add(ORGANIZATION_EDUCATION_1);
//        EDUCATION_LIST.add(ORGANIZATION_EDUCATION_2);
//        resume1.sections.put(SectionType.EDUCATION, EDUCATION);
//
        for (Map.Entry<ContactType, String> item : resume1.contacts.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
//
//        for (Map.Entry<SectionType, Section> item : resume1.sections.entrySet()) {
//            System.out.println(item.getKey() + ": " + item.getValue());
//        }
        return resume1;
    }
//
//    public static void main(String[] args) {
//        ResumeDataTest resumeDataTest = new ResumeDataTest();
//        resumeDataTest.getResume("uuid", "Григорий Кислин");
//    }
}
