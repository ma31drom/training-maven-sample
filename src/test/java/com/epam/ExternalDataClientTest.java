package com.epam;

import com.epam.external.ExternalDataClient;
import org.junit.jupiter.api.Test;

public class ExternalDataClientTest {

    @Test
    public void testGetExternalData() throws Exception {

        ExternalDataClient externalDataClient = new ExternalDataClient();

        String s = externalDataClient.callGoogle("http://www.google.com");

        System.out.println(s);
    }

}
