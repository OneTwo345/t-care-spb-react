package cg.tcarespb.models.enums;

public enum EGender {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");

    public String name;

    EGender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
