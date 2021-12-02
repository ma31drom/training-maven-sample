package com.epam.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonSamples {

    @Test
    public void samples() throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        om.setDateFormat(new SimpleDateFormat("hh:mm dd/MM/yyyy"));
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SampleEmployee sampleEmployee = new SampleEmployee();
        sampleEmployee.setName("Max");
        sampleEmployee.setAge(35);
        sampleEmployee.setBirthDate(new Date());

        String stringObjectRepresentation = om.writeValueAsString(sampleEmployee);
        System.out.println(stringObjectRepresentation);

        String toRead = "{\"name\":\"Max\",\"surName1\":\"Naumovich\",\"age\":35,\"birthDate\":\"07:46 15/11/2021\"}";
        SampleEmployee employee = om.readValue(toRead, SampleEmployee.class);

        System.out.println(employee);
    }

    static class SampleEmployee {
        String name;
        String surName;
        Integer age;
        Date birthDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        @Override
        public String toString() {
            return "SampleEmployee{" +
                    "name='" + name + '\'' +
                    ", surName='" + surName + '\'' +
                    ", age=" + age +
                    ", birthDate=" + birthDate +
                    '}';
        }
    }
}
