package bstu.fit.yarmolik.myapplication;

public class Teachers {
    private String name;
    private String login;
    public Teachers(String name, String login){
      this.name=name;
      this.login=login;
    }
    public String getName(){
        return this.name;
    }
    public String getLogin(){
        return this.login;
    }
}
