package com.company;

public abstract class LoanP {
    private String clientno;
    private String clientname;
    private double loanamount;
    private int years;
    private String loantype;

    public LoanP(String clientno, String clientname, double loanamount, int years, String loantype) {
        this.clientno = clientno;
        this.clientname = clientname;
        this.loanamount = loanamount;
        this.years = years;
        this.loantype = loantype;
    }
    public LoanP(double loanamount) {
        this.loanamount = loanamount;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public double getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(double loanamount) {
        this.loanamount = loanamount;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    //public abstract void generateTable(JTable table);
}

