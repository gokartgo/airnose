package ite.kmitl.airnose.dao;


public class UserInformationData {

    private String comment;
    private String date;
    private String duration;
    private String level;
    private String location;
    private String name;
    private String phone;
    private String smell;
    private String time;

    public UserInformationData(){

    }

    public UserInformationData(String comment, String duration, String date, String level, String location, String name, String phone, String smell, String time){
        this.comment = comment;
        this.date = date;
        this.duration = duration;
        this.level = level;
        this.location = location;
        this.name = name;
        this.phone = phone;
        this.smell = smell;
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
