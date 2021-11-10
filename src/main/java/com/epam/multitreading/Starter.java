package com.epam.multitreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Starter {

    private static Integer passCount = 1;
    private static final Logger LOG = LoggerFactory.getLogger(Starter.class);

    public static void main(String... args) {

        List<Terminal> terminals = terminals();

        Airport airport = new Airport(terminals);

        List<Plane> planes = getPlanes(airport);

        planes.forEach(Thread::start);

        LOG.info("Main thread finished");
    }

    private static List<Plane> getPlanes(Airport airport) {
        List<Plane> planes = new ArrayList<>();
        planes.add(new Plane(1, passangers(airport), airport));
        planes.add(new Plane(2, passangers(airport), airport));
        planes.add(new Plane(3, passangers(airport), airport));
        planes.add(new Plane(4, passangers(airport), airport));
        planes.add(new Plane(5, passangers(airport), airport));
        return planes;
    }

    private static List<Passenger> passangers(Airport airport) {
        List<Passenger> pass = new ArrayList<>();
        pass.add(getPassenger(airport));
        pass.add(getPassenger(airport));
        pass.add(getPassenger(airport));
        return pass;
    }

    private static Passenger getPassenger(Airport airport) {
        Ticket tic = null;
        if ((int) (Math.random() * 100) < 80) {
            tic = new Ticket();
            tic.setDateOfExpiration(new Date(new Date().getTime() + 500l + (long) (Math.random() * 1200)));
            tic.setTerminalNum((int) (Math.random() * 100) < 50 ? 1 : 2);
        }

        Passenger passenger = new Passenger(passCount, tic, airport);
        passCount += 1;
        return passenger;
    }

    private static List<Terminal> terminals() {
        List<Terminal> terminals = new ArrayList<>();
        terminals.add(new Terminal(1));
        terminals.add(new Terminal(2));
        return terminals;
    }

}
