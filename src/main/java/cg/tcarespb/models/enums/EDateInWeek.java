package cg.tcarespb.models.enums;

public enum EDateInWeek {

    //    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
    MONDAY("Thứ hai"),
    TUESDAY("Thứ ba"),
    WEDNESDAY("Thứ tư"),
    THURSDAY("Thứ năm"),
    FRIDAY("Thứ sáu"),
    SATURDAY("Thứ bảy"),
    SUNDAY("Chủ nhật");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    EDateInWeek(String name) {

        this.name = name;
    }

}
