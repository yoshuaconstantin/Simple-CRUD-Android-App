package com.joshua.r0th.crud2;

public class adapterdata {

    String NomorRumah;
    String JentikDalam;
    String JentikLuar;

    public adapterdata() {
        super();
    }

    public adapterdata(String NomorRumah, String JentikDalam, String JentikLuar) {
        super();
        this.NomorRumah = NomorRumah;
        this.JentikDalam = JentikDalam;
        this.JentikLuar = JentikLuar;
    }

    public adapterdata(String JentikDalam, String JentikLuar) {
        this.JentikDalam = JentikDalam;
        this.JentikLuar = JentikLuar;
    }

    // constructor
    public String getNomorRumah() {
        return NomorRumah;
    }

    public void setNomorRumah(String NomorRumah) {
        this.NomorRumah = NomorRumah;
    }

    public String getJentikDalam() {
        return JentikDalam;
    }

    public void setJentikDalam(String JentikDalam) {
        this.JentikDalam = JentikDalam;
    }

    public String getJentikLuar() {
        return JentikLuar;
    }

    public void setJentikLuar(String JentikLuar) {
        this.JentikLuar = JentikLuar;
    }

}



