/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.AccentRemover;
import model.WeatherInform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author quoc95
 */
public class Process {
    private static String path="F://Weather.xml";
    private static String listCountry ="F://Country.xml";
    public ArrayList<WeatherInform> search(String place){
        ArrayList<WeatherInform> arr = new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=dbf.newDocumentBuilder();
            File f=new File(path);
            try {
                Document doc=builder.parse(f);
                NodeList st=doc.getElementsByTagName("Weather");
//                System.out.println("number of student "+st.getLength());
//                System.out.println("number of student "+st.item(0).getChildNodes().item(1).getTextContent());
                for (int i = 0; i < st.getLength(); i++) {
                    String pl = st.item(i).getChildNodes().item(1).getTextContent();
                    String day = st.item(i).getChildNodes().item(3).getTextContent();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    ArrayList<String> listDate = new ArrayList<>();
                    listDate.add(dateFormat.format(date));
                    for (int j = 1; j < 10; j++) {
                        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24)*j);
                         listDate.add(dateFormat.format(tomorrow));
                    }
                    if(AccentRemover.removeAccent(pl).toLowerCase().trim().replaceAll(" ", "").equals(AccentRemover.removeAccent(place).toLowerCase().trim().replaceAll(" ", ""))&&listDate.contains(day)){
                    WeatherInform wi = new WeatherInform();
                    wi.setPlace(st.item(i).getChildNodes().item(1).getTextContent());
                    wi.setDay(st.item(i).getChildNodes().item(3).getTextContent());
                    wi.setWeather(st.item(i).getChildNodes().item(5).getTextContent());              
                    wi.setMinTemp(st.item(i).getChildNodes().item(7).getTextContent());      
                    wi.setMaxTemp(st.item(i).getChildNodes().item(9).getTextContent());
                    wi.setWind(st.item(i).getChildNodes().item(11).getTextContent());
                    wi.setHumidity(st.item(i).getChildNodes().item(13).getTextContent());
                    arr.add(wi);
                    }
                }
                if(arr.isEmpty()){
                    arr = search("Vietnam");
                }
            } catch (SAXException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
    public ArrayList<WeatherInform> searchToCheck(String place){
        ArrayList<WeatherInform> arr = new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=dbf.newDocumentBuilder();
            File f=new File(path);
            try {
                Document doc=builder.parse(f);
                NodeList st=doc.getElementsByTagName("Weather");
//                System.out.println("number of student "+st.getLength());
//                System.out.println("number of student "+st.item(0).getChildNodes().item(1).getTextContent());
                for (int i = 0; i < st.getLength(); i++) {
                    String pl = st.item(i).getChildNodes().item(1).getTextContent();
                    String day = st.item(i).getChildNodes().item(3).getTextContent();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    ArrayList<String> listDate = new ArrayList<>();
                    listDate.add(dateFormat.format(date));
                    for (int j = 1; j < 11; j++) {
                        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24)*j);
                         listDate.add(dateFormat.format(tomorrow));
                    }
                    if(AccentRemover.removeAccent(pl).toLowerCase().trim().replaceAll(" ", "").equals(AccentRemover.removeAccent(place).toLowerCase().trim().replaceAll(" ", ""))&&listDate.contains(day)){
                    WeatherInform wi = new WeatherInform();
                    wi.setPlace(st.item(i).getChildNodes().item(1).getTextContent());
                    wi.setDay(st.item(i).getChildNodes().item(3).getTextContent());
                    wi.setWeather(st.item(i).getChildNodes().item(5).getTextContent());              
                    wi.setMinTemp(st.item(i).getChildNodes().item(7).getTextContent());      
                    wi.setMaxTemp(st.item(i).getChildNodes().item(9).getTextContent());
                    wi.setWind(st.item(i).getChildNodes().item(11).getTextContent());
                    wi.setHumidity(st.item(i).getChildNodes().item(13).getTextContent());
                    arr.add(wi);
                    }
                }
               
            } catch (SAXException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<String> listCountry(){
        ArrayList<String> arr = new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=dbf.newDocumentBuilder();
            File f=new File(listCountry);
            try {
                Document doc=builder.parse(f);
                NodeList st=doc.getElementsByTagName("item");
                System.out.println("number of student "+st.getLength());
                for (int i = 0; i < st.getLength(); i++) {
                    arr.add(st.item(i).getTextContent());
                }
                
                
            } catch (SAXException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<WeatherInform> index(){
        ArrayList<WeatherInform> arr = new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=dbf.newDocumentBuilder();
            File f=new File(path);
            try {
                Document doc=builder.parse(f);
                NodeList st=doc.getElementsByTagName("Weather");
//                System.out.println("number of student "+st.getLength());
//                System.out.println("number of student "+st.item(0).getChildNodes().item(1).getTextContent());
                for (int i = 0; i < st.getLength(); i++) {
                    String pl = st.item(i).getChildNodes().item(1).getTextContent();
                    String day = st.item(i).getChildNodes().item(3).getTextContent();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    if(dateFormat.format(date).contains(day)){
                    WeatherInform wi = new WeatherInform();
                    wi.setPlace(st.item(i).getChildNodes().item(1).getTextContent());
                    wi.setDay(st.item(i).getChildNodes().item(3).getTextContent());
                    wi.setWeather(st.item(i).getChildNodes().item(5).getTextContent());              
                    wi.setMinTemp(st.item(i).getChildNodes().item(7).getTextContent());      
                    wi.setMaxTemp(st.item(i).getChildNodes().item(9).getTextContent());
                    wi.setWind(st.item(i).getChildNodes().item(11).getTextContent());
                    wi.setHumidity(st.item(i).getChildNodes().item(13).getTextContent());
                    arr.add(wi);
                    }
                }
                if(arr.isEmpty()){
                    arr = search("Vietnam");
                }
            } catch (SAXException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
    public boolean check(String place,String day){
         ArrayList<WeatherInform> arr= searchToCheck(place);
                    for (WeatherInform weatherInform : arr) {
                        if (weatherInform.getDay().equals(day)) {
                            return false;
                        }
                    }
               return true;
    }
    
    public boolean addWeatherInform(WeatherInform wi){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        File f = new File(path);
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            if (!f.exists()) {
                
                doc = builder.newDocument();
                
                Element root = doc.createElement("WeatherList");
               
                Element w = doc.createElement("Weather");
                
                Element place = doc.createElement("Place");
                Text placeValue = doc.createTextNode(wi.getPlace());
                place.appendChild(placeValue);
                
                Element date = doc.createElement("Date");
                Text dateValue = doc.createTextNode(wi.getDay());
                date.appendChild(dateValue);
                
                Element weatherType = doc.createElement("WeatherType");
                Text weatherTypeValue = doc.createTextNode(wi.getWeather());
                weatherType.appendChild(weatherTypeValue);
                
                Element minTemp = doc.createElement("MinTemp");
                Text minTempValue = doc.createTextNode(wi.getMinTemp());
                minTemp.appendChild(minTempValue);
                
                Element maxTemp = doc.createElement("MaxTemp");
                Text maxTempValue = doc.createTextNode(wi.getMaxTemp());
                maxTemp.appendChild(maxTempValue);
                
                Element wind = doc.createElement("Wind");
                Text windValue = doc.createTextNode(wi.getWind());
                wind.appendChild(windValue);
                
                Element humidity = doc.createElement("Humidity");
                Text humidityValue = doc.createTextNode(wi.getHumidity());
                humidity.appendChild(humidityValue);
                
                w.appendChild(place);
                w.appendChild(date);
                w.appendChild(weatherType);
                w.appendChild(minTemp);
                w.appendChild(maxTemp);
                w.appendChild(wind);
                w.appendChild(humidity);
                
                root.appendChild(w);
            
                doc.appendChild(root);
            } else {
                try {
              
                    doc = builder.parse(f);
                 
                    Element root = doc.getDocumentElement();
                 
                    Element w = doc.createElement("Weather");
                
                Element place = doc.createElement("Place");
                Text placeValue = doc.createTextNode(wi.getPlace());
                place.appendChild(placeValue);
                
                Element date = doc.createElement("Date");
                Text dateValue = doc.createTextNode(wi.getDay());
                date.appendChild(dateValue);
                
                Element weatherType = doc.createElement("WeatherType");
                Text weatherTypeValue = doc.createTextNode(wi.getWeather());
                weatherType.appendChild(weatherTypeValue);
                
                Element minTemp = doc.createElement("MinTemp");
                Text minTempValue = doc.createTextNode(wi.getMinTemp());
                minTemp.appendChild(minTempValue);
                
                Element maxTemp = doc.createElement("MaxTemp");
                Text maxTempValue = doc.createTextNode(wi.getMaxTemp());
                maxTemp.appendChild(maxTempValue);
                
                Element wind = doc.createElement("Wind");
                Text windValue = doc.createTextNode(wi.getWind());
                wind.appendChild(windValue);
                
                Element humidity = doc.createElement("Humidity");
                Text humidityValue = doc.createTextNode(wi.getHumidity());
                humidity.appendChild(humidityValue);
                
                w.appendChild(place);
                w.appendChild(date);
                w.appendChild(weatherType);
                w.appendChild(minTemp);
                w.appendChild(maxTemp);
                w.appendChild(wind);
                w.appendChild(humidity);
                    
                    root.appendChild(w);
                } catch (SAXException ex) {
                    Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } catch (IOException ex) {
                    Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return save2File(doc);
    }
    
    private static boolean save2File(Document doc) {
        TransformerFactory trans = TransformerFactory.newInstance();
        try {
            Transformer transformer = trans.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(path);
            Result result = new StreamResult(f);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            try {
                transformer.transform(source, result);
                System.out.println("create file success!");
                return true;
            } catch (TransformerException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void main(String[] args) {
        Process p = new Process();
        ArrayList<WeatherInform> ar = p.index();
        for (WeatherInform weatherInform : ar) {
            System.out.println(""+weatherInform.getPlace());
        }
    }
}
