package cg.tcarespb.models.enums;

import java.time.DayOfWeek;

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

    public DayOfWeek convertToDayOfWeek(EDateInWeek enumDateInWeek) {
        switch (enumDateInWeek) {
            case MONDAY:
                return DayOfWeek.MONDAY;
            case TUESDAY:
                return DayOfWeek.TUESDAY;
            case WEDNESDAY:
                return DayOfWeek.WEDNESDAY;
            case THURSDAY:
                return DayOfWeek.THURSDAY;
            case FRIDAY:
                return DayOfWeek.FRIDAY;
            case SATURDAY:
                return DayOfWeek.SATURDAY;
            case SUNDAY:
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Invalid EnumDateInWeek value: " + enumDateInWeek);
        }
    }
}