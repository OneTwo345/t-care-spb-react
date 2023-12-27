package cg.tcarespb.models.enums;

public enum EMemberOfFamily {
    MYPARENT("Cha Mẹ của tôi"),
    MYSPOUSE("Vợ/Chồng của tôi"),
    MYSELF("Bản thân tôi"),
    MYGRANDPARENTS("Ông bà của tôi"),
    OTHER("Khác");

    private String name;

    EMemberOfFamily(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
