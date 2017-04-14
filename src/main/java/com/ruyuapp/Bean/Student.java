package com.ruyuapp.Bean;

public class Student{

    private String Stu_name;
    private  int Stu_num;
    public Student(){
        this.Stu_num =1 ;
    }

    public String getStu_name() {
        return Stu_name;
    }

    public void setStu_name(String stu_name) {
        Stu_name = stu_name;
    }

    public int getStu_num() {
        return Stu_num;
    }

    public void setStu_num(int stu_num) {
        Stu_num = stu_num;
    }
}