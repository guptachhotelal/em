package com.em.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "event")
public class Event extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "organizer_name")
    private String orgniserName;
    @Column(name = "location")
    private String location;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_time")
    private Date  startTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgniserName() {
        return orgniserName;
    }

    public void setOrgniserName(String orgniserName) {
        this.orgniserName = orgniserName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", name=" + name + ", orgniserName=" + orgniserName + ", location=" + location + ", startTime=" + startTime + '}';
    }

}
