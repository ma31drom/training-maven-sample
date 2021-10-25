package com.epam.io;

import com.epam.model.Profile;
import com.epam.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectReadWriteTest {

    @Test
    public void objectStreamsWrite() throws IOException {

        File file = createFile("objects.txt");

        Profile profile = new Profile();
        profile.setId(1);
        profile.setNickname("ma31drom");
        profile.setDescripion("Some description here");

        User user = new User();
        user.setId(1);
        user.setLastName("Naumovich");
        user.setFirstName("Max");


        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(profile);
            os.writeObject(user);
        }
    }

    @Test
    public void objectStreamsRead() throws IOException, ClassNotFoundException {

        File file = createFile("objects.txt");

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            Object o = is.readObject();
            Object o2 = is.readObject();

            System.out.println(o.toString());
            System.out.println(o2.toString());
        }
    }

    @Test
    public void objectStreamsWriteJson() throws IOException {

        File file = createFile("objects.json");

        Profile profile = new Profile();
        profile.setId(1);
        profile.setNickname("ma31drom");
        profile.setDescripion("Some description here");
        
        try (FileWriter os = new FileWriter(file)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String p = objectMapper.writeValueAsString(profile);
            os.write(p);
        }
    }

    @Test
    public void objectStreamsReadJson() throws IOException {

        File file = createFile("objects.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Profile profile = objectMapper.readValue(file, Profile.class);
        System.out.println(profile);
    }

    private File createFile(String s) throws IOException {
        File file = new File(s);

        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    @Test
    public void findTest() throws IOException {

        File root = new File("c:\\Users\\Maksim_Naumovich\\workspace");

        AtomicInteger count = new AtomicInteger(0);

        Date start = new Date();
        countChildren(root, count);
        Date end = new Date();
        long period = end.getTime() - start.getTime();
        System.out.println("File count:" + count.get() + " time:" + period + " ms");

        AtomicInteger count2 = new AtomicInteger(0);
        Date start2 = new Date();
        Files.walkFileTree(Paths.get("c:\\Users\\Maksim_Naumovich\\workspace"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                count2.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }
        });
        Date end2 = new Date();
        long period2 = end2.getTime() - start2.getTime();
        System.out.println("File count:" + count2.get() + " time:" + period2 + " ms  -- WalkFileTree");

    }

    private void countChildren(File root, AtomicInteger count) {
        for (File child : root.listFiles()) {
            if (child.isFile()) {
                count.incrementAndGet();
            } else {
                if (child.listFiles() != null) {
                    countChildren(child, count);
                }
            }
        }
    }


}
