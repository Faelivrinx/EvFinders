package com.example.dominik.evfinders.command;

/**
 * Created by Dominik on 22.11.2017.
 */

public class CoordinateCommand {

    private double latitude;
    private double longitude;
    private int radius;
    private int recommendationType;

    public CoordinateCommand() {
    }

    public CoordinateCommand(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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

    public int getRecommendationType() {
        return recommendationType;
    }

    public void setRecommendationType(int recommendationType) {
        this.recommendationType = recommendationType;
    }

}
