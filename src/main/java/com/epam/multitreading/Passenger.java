package com.epam.multitreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Passenger extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Passenger.class);
    private Ticket ticket;
    private Airport airport;
    private Integer passNum;

    private boolean inThePlane = false;

    public Passenger(Integer passNum, Ticket ticket, Airport airport) {
        this.passNum = passNum;
        this.ticket = ticket;
        this.airport = airport;
        setName("PASSENGER-" + passNum);
    }

    @Override
    public void run() {
        try {
            sleep(10);

            if (ticket != null) {

                Terminal terminal = airport.getTerminalByNum(ticket.getTerminalNum());

                terminal.addForWait(this);
                LOG.info("Passenger {} in the terminal {} queue", passNum, terminal.getTerminalNum());

                long toWait = ticket.getDateOfExpiration().getTime() - new Date().getTime();
                sleep(toWait < 0 ? 0 : toWait);

                if (!inThePlane) {
                    terminal.leaveQueue(this);
                    LOG.info("Passenger {} tired and went away with expired ticket", passNum);
                }
            } else {
                LOG.info("Passenger {} doesn't have next ticket and went away", passNum);
            }

        } catch (InterruptedException e) {
            interrupt();
            LOG.error("Passenger died", e);
            throw new RuntimeException();
        }
    }

    public void inPlane() {
        inThePlane = true;
    }

    public Integer passNum() {

        return passNum;
    }
}
