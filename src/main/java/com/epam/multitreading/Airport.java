package com.epam.multitreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {

    private List<Terminal> terminals;


    private Semaphore semaphore;
    private ReentrantLock lock = new ReentrantLock();

    Airport(List<Terminal> terminals) {
        this.terminals = terminals;
        semaphore = new Semaphore(terminals.size());
        terminals.forEach(t -> t.setSemaphore(semaphore));
    }

    public Terminal getTerminal() throws InterruptedException {

        semaphore.acquire();
        lock.lock();
        Terminal freeTerminal = terminals.stream().filter(t -> t.isFree()).findFirst().get();
        freeTerminal.busy();
        lock.unlock();

        return freeTerminal;
    }

    public Terminal getTerminalByNum(int no) {
        return terminals.get(no - 1);
    }

}
