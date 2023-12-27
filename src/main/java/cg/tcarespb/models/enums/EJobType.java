package cg.tcarespb.models.enums;

public enum EJobType {
    FULLTIME("Định Kỳ"),
    ONETIME("Một Lần"),
    BOTH("Cả hai");

    private String name;

    EJobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
