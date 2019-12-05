package com.example.attendancetaker;

public class Students {

    String stuName;
    String stuId;
    String stuClass;

    public  Students()
    {

    }

    public Students(String stuName, String stuId, String stuClass)
    {
        this.stuName = stuName;
        this.stuId = stuId;
        this.stuClass = stuClass;
    }

    public  String getStuName()
    {
        return  stuName;
    }

    public  String getStuId()
    {
        return  stuId;
    }

    public  String getStuClass()
    {
        return  stuClass;
    }


}
