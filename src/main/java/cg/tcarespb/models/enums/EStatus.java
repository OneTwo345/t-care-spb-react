package cg.tcarespb.models.enums;

public enum EStatus {
    ACTIVE("Hoạt động"),
    WAITING("Đang chờ"),
    LOCKED("Đã khóa");

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
