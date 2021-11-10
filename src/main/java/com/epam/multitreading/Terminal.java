package com.epam.multitreading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {

    private Integer terminalNo;
    private boolean free = true;
    private Semaphore semaphore;
    private List<Passenger> passengers = new ArrayList<>();

    public Terminal(Integer terminalNo) {
        this.terminalNo = terminalNo;
    }

    private ReentrantLock passLock = new ReentrantLock();

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public boolean isFree() {
        return free;
    }

    public void busy() {
        free = false;
    }

    public void release() {
        free = true;
        semaphore.release();
    }

    public List<Passenger> getWaitingPassangers() {
        return passengers;
    }

    public void addForWait(Passenger passenger) {
        passLock.lock();
        passengers.add(passenger);
        passLock.unlock();
    }

    public Integer getTerminalNum() {
        return terminalNo;
    }

    public void leaveQueue(Passenger passenger) {
        passLock.lock();
        passengers.remove(passenger);
        passLock.unlock();
    }
}
