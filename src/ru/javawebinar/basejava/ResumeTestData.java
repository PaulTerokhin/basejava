package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Arrays;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume r1 = new Resume("Paul Terokhin");
        r1.setContact(ContactType.PHONE, "380500456792");
        r1.setContact(ContactType.MAIL, "ter@gmail.com");
        r1.setContact(ContactType.LINKEDIN, "linkedin.com");
        r1.setContact(ContactType.GITHUB, "github.com");
        r1.setContact(ContactType.STACKOVERFLOW, "stackoverflow.com");
        r1.setContact(ContactType.HOME_PAGE, "home.com");

        r1.setSection(SectionType.PERSONAL, new TextSection("Good communicator, team player"));
        r1.setSection(SectionType.OBJECTIVE, new TextSection("Seeking a challenging position in software development"));
        r1.setSection(SectionType.ACHIEVEMENT,  new ListSection(Arrays.asList("Won first prize in hackathon", "Published paper in academic journal")));
        r1.setSection(SectionType.QUALIFICATIONS,  new ListSection(Arrays.asList("Java", "Git", "SQL")));
        Section experience = new OrganizationSection(Arrays.asList(
                new Organization("Samsung", "samsung.com", "Januar 2019", "June 2022", "trainee", "Develop"),
                new Organization("Apple", "apple.com", "June 2022", "now", "Junior developer", "Develop")));
        r1.setSection(SectionType.EXPERIENCE, experience);
        Section education = new OrganizationSection(Arrays.asList(
                new Organization("University of Cambridge", "https://www.cam.ac.uk/", "September 2014", "June 2018", "student", "IT")));
        r1.setSection(SectionType.EDUCATION, education);

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
