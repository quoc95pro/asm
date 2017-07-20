/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import model.WeatherInform;
import app.Process;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author quoc95
 */

public class WeatherBean {

    /**
     * Creates a new instance of WeatherBean
     */
    private String place;
    private String day;
    private String weather;
    private String minTemp;
    private String maxTemp;
    private String wind;
    private String humidity;
    private ArrayList<WeatherInform> arr ;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public ArrayList<WeatherInform> getArr() {
        return arr;
    }

    public void setArr(ArrayList<WeatherInform> arr) {
        this.arr = arr;
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
    
    private ArrayList<String> arrCountry = new Process().listCountry() ; 
    private ArrayList<WeatherInform> arrWeather  = new Process().index();

    public ArrayList<WeatherInform> getArrWeather() {
        return arrWeather;
    }

    public void setArrWeather(ArrayList<WeatherInform> arrWeather) {
        this.arrWeather = arrWeather;
    }

    public ArrayList<String> getArrCountry() {
        return arrCountry;
    }

    public void setArrCountry(ArrayList<String> arrCountry) {
        this.arrCountry = arrCountry;
    }
    
    String listCountry =setListCountry();
   

    public String getListCountry() {
        return listCountry;
    }
    

    public String setListCountry() {
        String lst="[";
        for (String st : arrCountry) {
            lst+="\""+st+"\",";
        }
        lst+="\""+null+"\"]";
        return lst;
    }
    
    private boolean showAlert;

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
    
    
   
    
    
    public WeatherBean() {
    }
    
    public String searchByPlace(){
        Process p = new Process();
        this.arr = p.search(this.place);
        return "detail";
    }
    
    public String addNewWeatherInform(){
        Process p = new Process();
        WeatherInform wi = new WeatherInform();
        wi.setPlace(this.place);
        wi.setDay(this.day);
        wi.setMinTemp(this.minTemp);
        wi.setMaxTemp(this.maxTemp);
        wi.setWeather(this.weather);
        wi.setWind(this.wind);
        wi.setHumidity(this.humidity);
        if(p.check(this.place, this.day)==true){
            if(p.addWeatherInform(wi)){
                this.showAlert = true;
                return "index"; 
                 
            }
            else
                return "fail";
        }else{
            message = "Weather data has already existed !!";
            return "fail";
        }
    }
    
    public void test(){
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
       HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.setContentType("text/plain");
        String place = request.getParameter("place");
       String id = request.getParameter("id");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print("okx"+place+""+id);
        } catch (IOException ex) {
            Logger.getLogger(WeatherBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
           
//        this.maxTemp =arr.get(id).getMaxTemp();
//        this.minTemp=arr.get(id).getMinTemp();
//        this.wind=arr.get(id).getWind();
//        this.humidity=arr.get(id).getHumidity();
        
    }
    
    
}
