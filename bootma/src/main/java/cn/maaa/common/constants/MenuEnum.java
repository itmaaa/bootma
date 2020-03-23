package cn.maaa.common.constants;

public enum MenuEnum {
    CATALOGUE(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private Integer val;
    private String name;

    MenuEnum(Integer val, String name) {
        this.val = val;
        this.name = name;
    }

    public Integer getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

}
