package com.h2data.dataTest;

import javax.persistence.*;

@Entity
@Table(name = "FACTS")
public class Facts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Facts")
    private String animalFact;

    @Column(name = "IPAddress")
    private String ipAddress;

    @Column(name = "time")
    private String timeStamp;

    public Facts() {
    }
    public Facts(String animalFact, String ipAddress, String timeStamp) {
        this.animalFact=animalFact;
        this.ipAddress=ipAddress;
        this.timeStamp=timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimalFact() {
        return animalFact;
    }

    public void setAnimalFact(String animalFact) {
        this.animalFact = animalFact;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
