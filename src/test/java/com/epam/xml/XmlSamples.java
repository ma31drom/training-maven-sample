package com.epam.xml;

import com.epam.json.JsonSamples;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

public class XmlSamples {

    @Test
    public void testWithJava() throws JAXBException {

        SampleEmployee sampleEmployee = new SampleEmployee();
        sampleEmployee.setName("Max");
        sampleEmployee.setAge(35);
        sampleEmployee.setBirthDate(new Date());

        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(SampleEmployee.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(sampleEmployee, sw);

        //Verify XML Content
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        SampleEmployee unmarshal = (SampleEmployee) unmarshaller.unmarshal(new StringReader(xmlContent));

        System.out.println(unmarshal);
    }

    @Test
    public void testWithOM() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        SampleEmployee sampleEmployee = new SampleEmployee();
        sampleEmployee.setName("Max");
        sampleEmployee.setAge(35);
        sampleEmployee.setBirthDate(new Date());

        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String s = xmlMapper.writerFor(SampleEmployee.class).writeValueAsString(sampleEmployee);
        System.out.println(s);

        SampleEmployee sampleEmployee1 = xmlMapper.readValue(s, SampleEmployee.class);

        System.out.println(sampleEmployee1);
    }


    @XmlRootElement(name = "employee")
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
