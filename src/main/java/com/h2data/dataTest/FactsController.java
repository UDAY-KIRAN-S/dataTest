package com.h2data.dataTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Random;

@RestController
public class FactsController {
    @Autowired FactsRepository factsRepository;

    @GetMapping("/")
    public String Hello()
    {
        return "<html><body style='padding-top: 100px;'><center>" +
                "<p><b>localhost:8080/cat</b> for cat facts</p><br/>" +
                "<p><b>localhost:8080/dog</b> for dog facts</p><br/>" +
                "<p><b>localhost:8080/getDB</b> for Database </p><br/></center></body></html>";
    }
    @GetMapping("/cat")
    public String catFact()
    {

        RestTemplate restTemplate = new RestTemplate();
        //Getting IP Address
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();

        //Getting Time
        String timeStamp = new SimpleDateFormat("HH.mm.ss.dd.MM.yyyy").format(new java.util.Date());

        //Getting Cat fact from catfacts.ninja api
        String item = restTemplate.getForObject("https://catfact.ninja/fact",String.class);

        //clean in the fact
        item = item.replaceAll("[\\[\\]\\{\\}\"\n]","");
        item = item.replaceAll("fact:","");
        String[] factItem = item.split("length");

        //Calling Facts Constructor
        Facts facts = new Facts(factItem[0].strip(),ipAddress,timeStamp);

        //Saving to Database
        factsRepository.save(facts);

        //Returning Cat fact
        return "<html><body><center><b>" +
                factItem[0].strip()+"</b></center></body></html>";
    }

    @GetMapping("/dog")
    public String dogFact()
    {
        RestTemplate restTemplate = new RestTemplate();

        //Getting IP Address
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();

        //Getting time
        String timeStamp = new SimpleDateFormat("HH.mm.ss.dd.MM.yyyy").format(new java.util.Date());

        //Getting fact from dog-facts-api
        String item = restTemplate.getForObject("https://dog-facts-api.herokuapp.com/api/v1/resources/dogs/all",String.class);

        //Cleaning the fact
        item = item.replaceAll("[\\[\\]\\{\\}\"\n]","");
        String[] factsItem = item.split("fact:");
        factsItem[0] = factsItem[new Random().nextInt(1,factsItem.length)].strip();

        //Calling Facts constructor
        Facts facts = new Facts(factsItem[0],ipAddress,timeStamp);

        //Saving to Database
        factsRepository.save(facts);

        //Returning dog Fact
        return "<html><body><center><b>" +
                factsItem[0].strip()+"</b></center></body></html>";
    }

    @GetMapping("/getDB")
    public Iterable<Facts> getDB()
    {
        //Return Database contents in current run
        return   factsRepository.findAll();


    }
}
