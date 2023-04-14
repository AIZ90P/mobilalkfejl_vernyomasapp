package hu.zana.mobilalkfejl_vernyomasapp;

import java.util.Date;

public class MeresItem {
    private String sys;
    private String dia;
    private String pulse;
    private String date;

    public MeresItem() {}

    public MeresItem(String sys, String dia, String pulse, String date) {
        this.sys = sys;
        this.dia = dia;
        this.pulse = pulse;
        this.date = date;
    }

    public String getSys() {return sys;}

    public String getDia() {return dia;}

    public String getPulse() {return pulse;}

    public String getDate() {return date;}
}
