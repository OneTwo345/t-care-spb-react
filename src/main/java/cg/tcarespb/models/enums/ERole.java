package cg.tcarespb.models.enums;

public enum ERole {
    ROLE_USER("Người thuê"),
    ROLE_SALER("Người bán hàng"),
    ROLE_EMPLOYEE("Người hỗ trợ"),
    ROLE_ADMIN("Người quản lí");

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
