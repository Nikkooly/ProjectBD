package bstu.fit.yarmolik.myapplication;

public class Auditories {
    private String type;
    private String number;
    private String date;
    private String time;

    Auditories(String type, String number, String date, String time){
        this.type=type;
        this.number=number;
        this.date=date;
        this.time=time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number=number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public  String toString(){
        return "Тип: "+type + "\n" +"Номер: "+ number+"\n"+"Дата: "+date+"\n"+"Время: "+time;
    }
}
