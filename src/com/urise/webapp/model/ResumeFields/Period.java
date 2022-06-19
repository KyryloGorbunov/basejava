package com.urise.webapp.model.ResumeFields;

public class Period {

    private String date;
    private String position;
    private String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return '\n' + date + '\n' + position + '\n' + description;
    }
}
