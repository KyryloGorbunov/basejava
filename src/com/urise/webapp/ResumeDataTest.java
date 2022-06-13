package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.ResumeFields.AbstractSection;
import com.urise.webapp.model.ResumeFields.ContactType;
import com.urise.webapp.model.ResumeFields.SectionString;
import com.urise.webapp.model.ResumeFields.SectionType;

import java.util.Map;

public class ResumeDataTest {

    public static void main(String[] args) {
        Resume resume1 = new Resume("Григорий Кислин");
        resume1.contactMap.put(ContactType.PHONE, "+7(921) 855-0482");
        resume1.contactMap.put(ContactType.SKYPE, "skype:grigory.kislin");
        resume1.contactMap.put(ContactType.EMAIL, "Почта: gkislin@yandex.ru");
        resume1.contactMap.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume1.contactMap.put(ContactType.GITHUB, "https://github.com/gkislin");
        resume1.contactMap.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume1.contactMap.put(ContactType.PAGE, "http://gkislin.ru/");

        System.out.println(resume1.getFullName());
        for (Map.Entry<ContactType, String> item : resume1.contactMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }

        System.out.println();
        resume1.sectionMap.put(SectionType.POSITION, new SectionString("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        resume1.sectionMap.put(SectionType.PERSONAL, new SectionString("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));

        for (Map.Entry<SectionType, AbstractSection> item : resume1.sectionMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }
}
