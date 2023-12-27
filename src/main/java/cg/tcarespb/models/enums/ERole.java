package cg.tcarespb.models.enums;

public enum ERole {
    USER("Người thuê"),
    EMPLOYEE("Người hỗ trợ");

    private String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
