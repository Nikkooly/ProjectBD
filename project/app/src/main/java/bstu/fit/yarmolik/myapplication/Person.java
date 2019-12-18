package bstu.fit.yarmolik.myapplication;

public abstract class Person {

    private String name;
    private String surname;
    private String email;
    private String phone_number;
    //private String photo;
    private byte [] photo;
    //private Picture photo;
    //private Image photo; - maybe it will be better than Picture class
    private String faculty;
    private String department;
    private String password;

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getDepartment() {
        return department;
    }
    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {this.surname=surname;}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }*/



}