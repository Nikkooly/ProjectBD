package bstu.fit.yarmolik.myapplication;

public class Phone {
    private String title;
    private String info;
    private String phone;

    Phone(String title, String info, String phone){
        this.title=title;
        this.info=info;
        this.phone=phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info=info;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    @Override
    public  String toString(){
        return title + "\n" + info+"\n"+phone;
    }
}
