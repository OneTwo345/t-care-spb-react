package cg.tcarespb.models.enums;

public enum EStatus {
    ACTIVE("Hoạt động"),
    BAN("Khóa"),
    WAITING("Đang chờ");

    private String name;

    EStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
