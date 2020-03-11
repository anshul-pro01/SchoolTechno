package com.example.passwordnapoochiyo.schooltechno;

/**
 * Created by FLYCT Softtech on 26/12/2017.
 */

public class EmployeeData {

    String Id, Name, Email, Address, Contact, Gender, ParentId,schoolid,p_password,stud_id;


    public EmployeeData(String id, String name, String email, String address, String contact, String gender, String parentId, String fk_schoolid, String password,String studid) {
        Id = id;
        Name = name;
        Email = email;
        Address = address;
        Contact = contact;
        Gender = gender;
        ParentId = parentId;
        schoolid=fk_schoolid;
        p_password=password;
        stud_id=studid;

    }

    public String getP_password() {
        return p_password;
    }

    public void setP_password(String p_password) {
        this.p_password = p_password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }
    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }
}
