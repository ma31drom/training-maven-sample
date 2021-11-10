package com.epam.multitreading;

import java.util.Date;

public class Ticket {

    private Integer terminalNum;
    private Date dateOfExpiration;

    public Integer getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(Integer terminalNum) {
        this.terminalNum = terminalNum;
    }

    public Date getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Date dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }
}
