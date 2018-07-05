package com.em.entity;

public class StatusDetail {

    private static final long serialVersionUID = 1L;

    private int total;
    private int arrived;
    private int departed;
    private int cancelled;
    private int notAttending;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getArrived() {
        return arrived;
    }

    public void setArrived(int arrived) {
        this.arrived = arrived;
    }

    public int getDeparted() {
        return departed;
    }

    public void setDeparted(int departed) {
        this.departed = departed;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getNotAttending() {
        return notAttending;
    }

    public void setNotAttending(int notAttending) {
        this.notAttending = notAttending;
    }

}
