package com.kafkaconsumer.cosumesoapcall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kafkaconsumer.cosumesoapcall.model.Patient;
import com.kafkaconsumer.cosumesoapcall.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReceiverService {
    @Autowired
    RestTemplate restTemplate;

     @Autowired
     UserDetailsRepository userDetailsRepository;

    @KafkaListener(topics= "${kafka.topic-one}", groupId = "details", containerGroup = "kafkaListenerContainerFactory")
    public String receivePersonalDetails(String xmlString) throws JsonProcessingException {
        System.out.println("Patent id consumed from kafka " + xmlString); //<PatientID><id>123</id></PatientID>
        XmlMapper xmlMapper = new XmlMapper();
        Patient Patient = xmlMapper.readValue(xmlString, Patient.class);
        String id=Patient.getId().toString();
       // System.out.println("requiredFormat  :" +Patient.getId());
        //<id>123</id>


        String url = "http://localhost:8088/xmlresonce/"+id;//endpoint

    HttpHeaders httpHeaders = new HttpHeaders();

   // httpHeaders.setContentType(MediaType.APPLICATION_JSON);

   HttpEntity<String> httpEntity = new HttpEntity(httpHeaders);//payload

    ResponseEntity<String> testOUtput = restTemplate.postForEntity(url, httpEntity, String.class);
    String outputPayload=testOUtput.getBody();
    Patient fromEndpioint = xmlMapper.readValue(outputPayload, Patient.class);
    String listPatient = fromEndpioint.getPatientlist();
        String[] lines=listPatient.split("\n");
 int count=0;
        for (String line : lines) {
            String [] tab= line.split("\t");
            String a1 = tab[0];
            String a2 = tab[1];
            String a3 = tab[2];
            String a4 = tab[3];

            userDetailsRepository.save(a1,a2,a3,a4);


count++;


        }
        System.out.println(" Number of records inserted to SOR :: " +count);


        return "";
    }





}
