package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.ResumeFields.ContactType;
import com.urise.webapp.model.ResumeFields.SectionType;

public class ResumeDataTest {

    public static void main(String[] args) {
        Resume resume1 = new Resume("Григорий Кислин");
        System.out.println(resume1.getFullName());

        resume1.contactTypeString.save(ContactType.PHONE, "+7(921) 855-0482");
        resume1.contactTypeString.save(ContactType.SKYPE, "skype:grigory.kislin");
        resume1.contactTypeString.save(ContactType.EMAIL, "Почта: gkislin@yandex.ru");
        resume1.contactTypeString.save(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume1.contactTypeString.save(ContactType.GITHUB, "https://github.com/gkislin");
        resume1.contactTypeString.save(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume1.contactTypeString.save(ContactType.PAGE, "http://gkislin.ru/");
        resume1.contactTypeString.getSectionType();

        System.out.println();
        resume1.sectionString.save(SectionType.POSITION, "Ведущий стажировок и корпоративного " +
                " обучения по Java Web и Enterprise технологиям");
        resume1.sectionString.save(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры.");
        resume1.sectionString.getSectionType();

        resume1.sectionList.save(SectionType.ACHIEVEMENT, "Организация команды и успешная реализация Java проектов" +
                " для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга " +
                "показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring " +
                "Boot + Vaadin проект для комплексных DIY смет.");
        resume1.sectionList.save(SectionType.ACHIEVEMENT, "С 2013 года: разработка проектов \"Разработка Web " +
                "приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы" +
                " (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов." +
                " Более 3500 выпускников.");
        resume1.sectionList.getSectionType();

        resume1.sectionMap.save(SectionType.EXPERIENCE, "10/2014 - 01/2016", "Wrike\n" +
                "Старший разработчик (backend)" + "\n" + "Проектирование и разработка онлайн платформы управления " +
                "проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная" +
                " аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        resume1.sectionMap.save(SectionType.EDUCATION, "03/2013 - 05/2013", "Coursera\n" +
                "Functional Programming Principles in Scala' by Martin Odersky");
        resume1.sectionMap.getSectionType();
    }
}
