package com.example.routefinder;

import java.util.Comparator;

public class Station implements Comparable<Station> {
    Integer id;
    double latitude;
    double longitude;
    String name;
    double zone;
    Integer totalLines;

    public Station(Integer id, double latitude, double longitude, String name, double zone, Integer totalLines) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.zone = zone;
        this.totalLines = totalLines;
    }

    @Override
    public String toString() {
        return "Station  " + "\n" +
                "  name = " + name + "\n" +
                "  id = " + id + "\n" +
                "  latitude = " + latitude + "\n" +
                "  longitude = " + longitude + "\n" +
                "  zone = " + zone + "\n" +
                "  totalLines = " + totalLines + "\n";
    }

    @Override
    public int compareTo(Station o) {
        return Comparators.id.compare(this, o);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getZone() {
        return zone;
    }

    public void setZone(double zone) {
        this.zone = zone;
    }

    public Integer getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(Integer totalLines) {
        this.totalLines = totalLines;
    }

    public static class Comparators {

        public static final Comparator<Station> NAME = (Station o1, Station o2) -> o1.name.compareTo(o2.name);
        public static final Comparator<Station> id = (Station o1, Station o2) -> Integer.compare(o1.id, o2.id);
        public static final Comparator<Station> NAMEANDid = (Station o1, Station o2) -> NAME.thenComparing(id).compare(o1, o2);
    }
}
