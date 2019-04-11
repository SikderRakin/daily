package com.example.Expense_Tracker;

public class Expanse_Container {

    private int ID;
    private String expanse_name;
    private int expanse_amount;
    private long expanse_date;
    private long expanse_time;

    public Expanse_Container(int ID, String expanse_name, int expanse_amount, long expanse_date, long expanse_time) {
        this.ID = ID;
        this.expanse_name = expanse_name;
        this.expanse_amount = expanse_amount;
        this.expanse_date = expanse_date;
        this.expanse_time = expanse_time;
    }

    public int getID() {
        return ID;
    }

    public String getExpanse_name() {
        return expanse_name;
    }

    public int getExpanse_amount() {
        return expanse_amount;
    }

    public long getExpanse_date() {
        return expanse_date;
    }

    public long getExpanse_time() {
        return expanse_time;
    }
}
