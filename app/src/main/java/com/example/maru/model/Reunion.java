package com.example.maru.model;


import java.util.ArrayList;



public class Reunion {

    private String hour;
    private String date;
    private String place;
    private String subject;
    private ArrayList<String> mail;



    // --- CONSTRUCTOR ----
    public Reunion(String hour, String place, String subject, ArrayList<String> mail, String date) {
        this.hour = hour;
        this.place = place;
        this.subject = subject;
        this.mail = mail;
        this.date = date;
    }


    // --- GETTERS ---
    public String getHour() { return hour; }
    public String getDate() {return date;}
    public String getPlace() { return place; }
    public String getSubject() { return subject; }
    public ArrayList<String> getMail() { return mail; }





}


