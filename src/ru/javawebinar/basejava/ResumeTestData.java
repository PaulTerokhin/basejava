package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.Arrays;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume r1 = new Resume("Paul Terokhin");
        r1.addContact(ContactType.PHONE, "380500456792");
        r1.addContact(ContactType.MAIL, "ter@gmail.com");
        r1.addContact(ContactType.LINKEDIN, "linkedin.com");
        r1.addContact(ContactType.GITHUB, "github.com");
        r1.addContact(ContactType.STACKOVERFLOW, "stackoverflow.com");
        r1.addContact(ContactType.HOME_PAGE, "home.com");

        r1.addSection(SectionType.PERSONAL, new TextSection("Good communicator, team player"));
        r1.addSection(SectionType.OBJECTIVE, new TextSection("Seeking a challenging position in software development"));
        r1.addSection(SectionType.ACHIEVEMENT,  new ListSection(Arrays.asList("Won first prize in hackathon", "Published paper in academic journal")));
        r1.addSection(SectionType.QUALIFICATIONS,  new ListSection(Arrays.asList("Java", "Git", "SQL")));
        Section experience = new OrganizationSection(new Organization("Organization11", "http://Organization11.ru",
                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")));
        r1.addSection(SectionType.EXPERIENCE, experience);
        Section education = new OrganizationSection(new Organization("Institute", null,
                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null)));
        r1.addSection(SectionType.EDUCATION, education);

        System.out.println("Resume for " + r1.getFullName() + ":");
        System.out.println("Contacts:");
        for (ContactType type : ContactType.values()) {
            String value = r1.getContact(type);
            System.out.println(type.toString() + ": " + value);
        }
        System.out.println();
        for (SectionType type : SectionType.values()) {
            Section section = r1.getSection(type);
            System.out.println(type.toString() + ":");
            System.out.println(section.toString());
            System.out.println();
        }

    }

}
