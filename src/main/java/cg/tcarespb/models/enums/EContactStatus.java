package cg.tcarespb.models.enums;

public enum EContactStatus {
    MET("Đã gặp mặt"),

    NOTMET("Chưa gặp mặt"),
    CONFIRMING("Đang chờ xác nhận"),
    CANCELED("Đã hủy");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    EContactStatus(String name) {
        this.name = name;
    }
}
