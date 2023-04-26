package com.example.routefinder;

public class Station {
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
        return  "Station  " + "\n" +
                "  name = " + name + "\n" +
                "  id = " + id + "\n" +
                "  latitude = " + latitude + "\n" +
                "  longitude = " + longitude + "\n" +
                "  zone = " + zone + "\n" +
                "  totalLines = " + totalLines + "\n";
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
}
