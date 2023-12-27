package cg.tcarespb.models.enums;

public enum ESessionOfDate {
    MORNING("Buổi sáng"),
    AFTERNOON("Buổi trưa chiều"),
    EVENING("Buổi chiều tối"),
    NIGHT("Buổi tối");

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
