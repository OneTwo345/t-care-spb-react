package cg.tcarespb.models.enums;

public enum ECartStatus {
    READY("Đang xác nhận"),
    UNREADY("Chưa xác nhận ");

    private String name;

    ECartStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
