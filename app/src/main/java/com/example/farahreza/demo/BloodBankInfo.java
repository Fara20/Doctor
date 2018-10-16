package com.example.farahreza.demo;

public class BloodBankInfo {

    String aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus, type;

    public BloodBankInfo() {

    }

    public BloodBankInfo(String aplus) {
        this.aplus = aplus;
    }

    public BloodBankInfo(String aplus, String bplus, String abplus, String oplus, String aminus, String bminus, String abminus, String ominus, String type) {
        this.aplus = aplus;
        this.bplus = bplus;
        this.abplus = abplus;
        this.oplus = oplus;
        this.aminus = aminus;
        this.bminus = bminus;
        this.abminus = abminus;
        this.ominus = ominus;
        this.type = type;
    }

    public String getAplus() {
        return aplus;
    }

    public void setAplus(String aplus) {
        this.aplus = aplus;
    }

    public String getBplus() {
        return bplus;
    }

    public void setBplus(String bplus) {
        this.bplus = bplus;
    }

    public String getAbplus() {
        return abplus;
    }

    public void setAbplus(String abplus) {
        this.abplus = abplus;
    }

    public String getOplus() {
        return oplus;
    }

    public void setOplus(String oplus) {
        this.oplus = oplus;
    }

    public String getAminus() {
        return aminus;
    }

    public void setAminus(String aminus) {
        this.aminus = aminus;
    }

    public String getBminus() {
        return bminus;
    }

    public void setBminus(String bminus) {
        this.bminus = bminus;
    }

    public String getAbminus() {
        return abminus;
    }

    public void setAbminus(String abminus) {
        this.abminus = abminus;
    }

    public String getOminus() {
        return ominus;
    }

    public void setOminus(String ominus) {
        this.ominus = ominus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
