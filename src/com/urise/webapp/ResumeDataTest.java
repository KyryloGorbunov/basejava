package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.ResumeFields.*;

import java.util.Map;

public class ResumeDataTest {
    private static final String PHONE = "+7(921) 855-0482";
    private static final String SKYPE = "skype:grigory.kislin";
    private static final String EMAIL = "gkislin@yandex.ru";
    private static final String LINKEDIN = "https://www.linkedin.com/in/gkislin";
    private static final String GITHUB = "https://github.com/gkislin";
    private static final String STACKOVERFLOW = "\"https://stackoverflow.com/users/548473/grigory-kislin\"";
    private static final String PAGE = "http://gkislin.ru/";

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

        TextSection position = new TextSection();
        position.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        TextSection personal = new TextSection();
        personal.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        resume1.sectionFields.put(SectionType.POSITION, position);
        resume1.sectionFields.put(SectionType.PERSONAL, personal);

        ListSection achievement = new ListSection();
        achievement.strings.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на" +
                " Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
                "комплексных DIY смет");
        achievement.strings.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное " +
                "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");

        resume1.sectionFields.put(SectionType.ACHIEVEMENT, achievement);

        ListSection qualification = new ListSection();
        qualification.strings.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.strings.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.strings.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
                "MySQL, SQLite, MS SQL, HSQLDB");

        resume1.sectionFields.put(SectionType.QUALIFICATIONS, qualification);

        Period period1 = new Period();
        period1.setDate("10/2013 - Now");
        period1.setPosition("Автор проекта.");
        period1.setDescription("Создание, организация и проведение Java онлайн проектов и стажировок.");

        Organization organization1 = new Organization();
        organization1.setName("Java Online Projects");
        organization1.setWebsite("https://www.https://javaops.ru/.com/");
        organization1.periods.add(period1);

        Period period2 = new Period();
        period2.setDate("10/2014 - 01/2016");
        period2.setPosition("Старший разработчик (backend)");
        period2.setDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
                " Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация " +
                "по OAuth1, OAuth2, JWT SSO.");

        Organization organization2 = new Organization();
        organization2.setName("Wrike");
        organization2.setWebsite("https://www.wrike.com/");
        organization2.periods.add(period2);

        OrganizationSection experience = new OrganizationSection();
        experience.organizations.add(organization1);
        experience.organizations.add(organization2);

        resume1.sectionFields.put(SectionType.EXPERIENCE, experience);

        Period period3 = new Period();
        period3.setDate("03/2013 - 05/2013");
        period3.setDescription("'Functional Programming Principles in Scala' by Martin Odersky");

        Organization organization3 = new Organization();
        organization3.setName("Coursera");
        organization3.setWebsite("https://www.coursera.org/learn/scala-functional-programming");
        organization3.periods.add(period3);

        Period period4 = new Period();
        period4.setDate("03/2011 - 04/2011");
        period4.setDescription("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");

        Organization organization4 = new Organization();
        organization4.setName("Luxoft");
        organization4.setWebsite("https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html");
        organization4.periods.add(period4);

        OrganizationSection education = new OrganizationSection();
        education.organizations.add(organization3);
        education.organizations.add(organization4);

        resume1.sectionFields.put(SectionType.EDUCATION, education);

        for (Map.Entry<ContactType, String> item : resume1.contactFields.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> item : resume1.sectionFields.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}
