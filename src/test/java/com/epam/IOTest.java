package com.epam;

import com.epam.repo.ListBasedUserRepository;
import com.epam.service.RetriableUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class IOTest {
    private static final Logger LOGGER = getLogger(RetriableUserService.class);

    @Test
    public void fileRead() throws InterruptedException, IOException {

        File file = new File("123.txt");
        file.createNewFile();

        try (ListBasedUserRepository repo = new ListBasedUserRepository();
             FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.println("something happens with the file stream");
        } catch (IOException e) {
            LOGGER.error("Something went wrong", e);
        }
    }
}
