package com.epam.multitreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Plane extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Plane.class);

    private Integer planeNo;

    private List<Passenger> passengerList;
    private Airport airport;

    public Plane(Integer planeNo, List<Passenger> passengerList, Airport airport) {
        this.passengerList = passengerList;
        this.airport = airport;
        this.planeNo = planeNo;
        setName("PLANE-" + planeNo);
    }

    @Override
    public void run() {

        try {
            Terminal terminal = airport.getTerminal();
            LOG.info("Plane no {} landed to terminal {}", planeNo, terminal.getTerminalNum());
            releasePassengers(terminal);

            prepareToFly(terminal);

            fly(terminal);
            LOG.info("Plane no {} left terminal {}", planeNo, terminal.getTerminalNum());

        } catch (InterruptedException e) {
            interrupt();
            LOG.error("Plane crashed", e);
            throw new RuntimeException();
        }
    }

    private void fly(Terminal terminal) {
        passengerList.forEach(p -> {
            p.inPlane();
            LOG.info("Plane no {} has passenger {}", planeNo, p.passNum());
        });

        terminal.release();
    }

    private void releasePassengers(Terminal terminal) throws InterruptedException {

        passengerList.forEach(Thread::start);
        passengerList.clear();
        LOG.info("Plane no {} released passengers", planeNo);
        sleep((long) (100 + Math.random() * 300));
    }

    private void prepareToFly(Terminal terminal) throws InterruptedException {
        List<Passenger> toLoad = terminal.getWaitingPassangers();
        sleep((long) (100 + Math.random() * 300));
        //lock passanger to not go away
        toLoad.forEach(p -> passengerList.add(p));
        toLoad.clear();
        //unlock
        LOG.info("Plane no {} took waiting passengers", planeNo);
    }

}
