package hu.zana.mobilalkfejl_vernyomasapp;

import java.util.Date;

public class MeresItem {
    private int sys;
    private int dia;
    private int pulse;
    private Date date;

    public MeresItem(int sys, int dia, int pulse, Date date) {
        this.sys = sys;
        this.dia = dia;
        this.pulse = pulse;
        this.date = date;
    }

    public int getSys() {return sys;}

    public int getDia() {return dia;}

    public int getPulse() {return pulse;}

    public Date getDate() {return date;}
}
