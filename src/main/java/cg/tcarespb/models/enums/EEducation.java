package cg.tcarespb.models.enums;

public enum EEducation {
    HIGHSCHOOL("THPT"),
    UNIVERSITY("Đại Học"),
    COLLEGE("Cao Đẳng"),
    GRADUTEDEGREE("Cử Nhân");

    private String name;

    EEducation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
