package cg.tcarespb.models.enums;

public enum ECartStatus {
    READY("Sẵn sàng"),
    UNREADY("Không sẵn sàng");

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
