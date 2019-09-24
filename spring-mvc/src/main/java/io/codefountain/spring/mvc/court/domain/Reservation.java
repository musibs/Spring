package io.codefountain.spring.mvc.court.domain;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {

    private String courtName;
    private Date date;
    private int hour;
    private Player player;
    private SportType sportType;

    public Reservation(String courtName, Date date, int hour, Player player, SportType sportType) {
        this.courtName = courtName;
        this.date = date;
        this.hour = hour;
        this.player = player;
        this.sportType = sportType;
    }

    public String getCourtName() {
        return courtName;
    }

    public Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public Player getPlayer() {
        return player;
    }

    public SportType getSportType() {
        return sportType;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "courtName='" + courtName + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", player=" + player +
                ", sportType=" + sportType +
                '}';
    }
}
