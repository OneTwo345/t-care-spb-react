package cg.tcarespb.models.enums;

public enum EPayStatus {
    PAID("Đã trả tiền"),

    NOTPAID("Chưa trả tiền");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    EPayStatus(String name) {
        this.name = name;
    }
}
