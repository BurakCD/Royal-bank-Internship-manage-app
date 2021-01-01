package com.burak.finalprojectmanager;

public class Internship {

    private String id, name, class_no, mail, age, sector, acceptance;

    public Internship(String id, String nameSurname, String class_no, String mail, String age, String sector, String acceptance) {
        this.id= id;
        this.name = nameSurname;
        this.class_no = class_no;
        this.mail = mail;
        this.age = age;
        this.sector = sector;
        this.acceptance=acceptance;
    }

    public Internship() {
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
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

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
