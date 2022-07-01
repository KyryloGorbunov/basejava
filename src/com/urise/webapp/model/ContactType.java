package com.urise.webapp.model;

public enum ContactType {
    PHONE("Work phone"),
    MOBILE("Mobile phone"),
    HOME_PHONE("Home phone"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverflow"),
    HOME_PAGE("Page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
