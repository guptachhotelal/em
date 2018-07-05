package com.em.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "scheduler")
public class Scheduler extends Audit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Column(name = "place")
    private String place;
    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "departure_mode")
    private String departureMode;
    @Column(name = "departure_ref_no")
    private String departureRefNo;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_room_no")
    private int hotelRoomNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "arrival_date")
    private Date arrivalDate;
    @Column(name = "arrival_mode")
    private String arrivalMode;
    @Column(name = "arrival_ref_no")
    private String arrivalRefNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureMode() {
        return departureMode;
    }

    public void setDepartureMode(String departureMode) {
        this.departureMode = departureMode;
    }

    public String getDepartureRefNo() {
        return departureRefNo;
    }

    public void setDepartureRefNo(String departureRefNo) {
        this.departureRefNo = departureRefNo;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    public void setHotelRoomNumber(int hotelRoomNumber) {
        this.hotelRoomNumber = hotelRoomNumber;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(String arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public String getArrivalRefNo() {
        return arrivalRefNo;
    }

    public void setArrivalRefNo(String arrivalRefNo) {
        this.arrivalRefNo = arrivalRefNo;
    }

    @Override
    public String toString() {
        return "Scheduler{" + "id=" + id + ", doctor=" + doctor + ", event=" + event + ", place=" + place + ", departureDate=" + departureDate + ", departureMode=" + departureMode + ", departureRefNo=" + departureRefNo + ", hotelName=" + hotelName + ", hotelRoomNumber=" + hotelRoomNumber + ", arrivalDate=" + arrivalDate + ", arrivalMode=" + arrivalMode + ", arrivalRefNo=" + arrivalRefNo + '}';
    }

}
