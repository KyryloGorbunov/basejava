package com.urise.webapp.model;

public enum SectionType {
    PERSONAL("Personal qualities") {
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    },
    OBJECTIVE("Position") {
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    },
    ACHIEVEMENT("Achievements") {
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    },
    QUALIFICATIONS("Qualification"){
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    },
    EXPERIENCE("Work experience"){
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    },
    EDUCATION("Education"){
        @Override
        public String toHtml0(String value) {
            return super.toHtml0(value);
        }
    };

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
