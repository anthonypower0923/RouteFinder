package com.example.routefinder;

public class Station {
    Integer id;
    double latitude;
    double longitude;
    String name;
    double zone;
    Integer totalLines;
    Integer line;

    public Station(Integer id, double latitude, double longitude, String name, double zone, Integer totalLines, Integer line) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.zone = zone;
        this.totalLines = totalLines;
        this.line = line;
    }

    @Override
    public String toString() {
        return  "Station  " + "\n" +
                "  id = " + id + "\n" +
                "  latitude = " + latitude + "\n" +
                "  longitude = " + longitude + "\n" +
                "  name = " + name + "\n" +
                "  zone = " + zone + "\n" +
                "  totalLines = " + totalLines + "\n" +
                "  line = " + line + "\n";
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

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}
