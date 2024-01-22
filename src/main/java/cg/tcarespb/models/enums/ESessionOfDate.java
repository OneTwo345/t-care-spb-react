package cg.tcarespb.models.enums;

public enum ESessionOfDate {
    MORNING("Sáng"),
    AFTERNOON("Chiều"),
    EVENING("Tối"),
    NIGHT("Khuya");

    private String name;

    ESessionOfDate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
