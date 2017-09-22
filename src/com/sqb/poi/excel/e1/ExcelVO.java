package com.sqb.poi.excel.e1;

public class ExcelVO {
    private String id;
    private String name;
    public ExcelVO(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public ExcelVO() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "ExcelVO [id=" + id + ", name=" + name + "]";
    }
    
}
