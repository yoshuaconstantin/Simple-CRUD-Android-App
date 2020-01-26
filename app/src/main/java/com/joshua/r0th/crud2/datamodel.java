package com.joshua.r0th.crud2;

public class datamodel {
    int id;
    String NomorRumah;
    String JentikDalam;
    String JentikLuar;

    public datamodel(int id, String NomorRumah, String JentikDalam, String JentikLuar) {
        this.id = id;
        this.NomorRumah = NomorRumah;
        this.JentikDalam = JentikDalam;
        this.JentikLuar = JentikLuar;
    }

    public int getId() {
        return id;
    }

    public String getNomorRumah() {
        return NomorRumah;
    }

    public String getJentikDalam() {
        return JentikDalam;
    }
    public String getJentikLuar() {
        return JentikLuar;
    }
}

