/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author quoc95
 */
public class WeatherInform {
    private String place;
    private String day;
    private String weather;
    private String minTemp;
    private String maxTemp;
    private String wind;
    private String humidity;

    public WeatherInform() {
    }

    public WeatherInform(String place, String day, String weather, String minTemp, String maxTemp, String wind, String humidity) {
        this.place = place;
        this.day = day;
        this.weather = weather;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    
    
    
    
    
}
