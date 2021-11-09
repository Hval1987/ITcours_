package by.itacademy.appliances.dao.dom_parse;

import by.itacademy.appliances.model.*;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;


public class DomParser {
           private ArrayList<Appliance> appliancedList;
           private String src;

           //конструктор
           public DomParser() {

           }

           //самый основной метод
           public ArrayList<Appliance> parse(String src) throws IOException, SAXException {

            this.src=src;
            appliancedList=new ArrayList<>();
            DOMParser parser = new DOMParser();
            parser.parse(src);

            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
                NodeList ovensNodes = root.getElementsByTagName("app:oven");
                createOvenList(appliancedList, ovensNodes);
                
                NodeList laptopsNode = root.getElementsByTagName("app:laptop");
                createLaptopList(appliancedList, laptopsNode);
                System.out.println();
                

                NodeList refrigeratorNode = root.getElementsByTagName("app:refrigerator");
                createRefrigeratorList(appliancedList,refrigeratorNode);
               
                NodeList vcCleanerNode=root.getElementsByTagName("app:vacuum-cleaner");
                createVcCleanerList(appliancedList,vcCleanerNode);
               

                NodeList tabletPCNode=root.getElementsByTagName("app:tabletPC");
                createTabletPCList(appliancedList,tabletPCNode);
               
                NodeList speakersNode=root.getElementsByTagName("app:speaker");
                appliancedList=createSpeakerList(appliancedList,speakersNode);
                return appliancedList;

           }

           //getter
           public ArrayList<Appliance> getAppliancedList(){
           return appliancedList;
           }

           private  ArrayList<Appliance> createOvenList(ArrayList<Appliance> appliancedList, NodeList ovens) {

        Oven fillOven =new Oven();

        for (int i = 0; i < ovens.getLength(); i++) {
            fillOven = new Oven();

            Element oven_element =(Element)  ovens.item(i);

            fillOven.setType(getSingleChild(oven_element,"type").getTextContent().trim());
            fillOven.setId(oven_element.getAttribute("id"));
            fillOven.setPowerConsumption(Integer.parseInt(getSingleChild(oven_element,"power-consumption").getTextContent().trim()));
            fillOven.setWeight(Integer.parseInt(getSingleChild(oven_element,"weight").getTextContent().trim()));
            fillOven.setCapacity(Integer.parseInt(getSingleChild(oven_element,"capacity").getTextContent().trim()));
            fillOven.setDepts(Integer.parseInt(getSingleChild(oven_element,"dept").getTextContent().trim()));
            fillOven.setHeight(Double.parseDouble(getSingleChild(oven_element,"height").getTextContent().trim()));
            fillOven.setDepts(Double.parseDouble(getSingleChild(oven_element,"width").getTextContent().trim()));
            appliancedList.add(fillOven);
            fillOven = null;

        }

        return  appliancedList;
    }

           private  ArrayList<Appliance> createLaptopList(ArrayList<Appliance> appliancedList, NodeList laptops) {

        Laptop fillLaptop;

        for (int i = 0; i < laptops.getLength(); i++) {
            fillLaptop=new Laptop();

            Element laptop_element=(Element)laptops.item(i);
            String  x=laptop_element.getAttribute("id");
            fillLaptop.setId(laptop_element.getAttribute("id"));

            fillLaptop.setType(getSingleChild(laptop_element,"type").getTextContent().trim());
            fillLaptop.setBattery_capacity(Double.parseDouble(getSingleChild(laptop_element,"battery-capacity").getTextContent().trim()));
            fillLaptop.setOs(getSingleChild(laptop_element,"os").getTextContent().trim());
            fillLaptop.setMemory_rom(Integer.parseInt(getSingleChild(laptop_element,"memory-rom").getTextContent().trim()));
            fillLaptop.setSystem_memory(Integer.parseInt(getSingleChild(laptop_element,"system-memory").getTextContent().trim()));
            fillLaptop.setCpu(Double.parseDouble(getSingleChild(laptop_element,"cpu").getTextContent().trim()));
            fillLaptop.setDisplay_inch(Double.parseDouble(getSingleChild(laptop_element,"display-inch").getTextContent().trim()));
            appliancedList.add(fillLaptop);
            fillLaptop=null;

        }
        return appliancedList;
    }

           private  ArrayList<Appliance> createRefrigeratorList(ArrayList<Appliance> appliancedList, NodeList RefrigeratorNode) {
        Refrigerator fillRefrigerator;

        for(int i=0;i<RefrigeratorNode.getLength();i++){
            fillRefrigerator=new Refrigerator();
            Element refrElement=(Element)RefrigeratorNode.item(i);

            fillRefrigerator.setType(getSingleChild(refrElement,"type").getTextContent().trim());
            fillRefrigerator.setId(refrElement.getAttribute("id"));
            fillRefrigerator.setPowerConsumption(Integer.parseInt(getSingleChild(refrElement,"power-consumption").getTextContent().trim()));
            fillRefrigerator.setWeight(Integer.parseInt(getSingleChild(refrElement,"weight").getTextContent().trim()));
            fillRefrigerator.setFreezerCapacity(Integer.parseInt(getSingleChild(refrElement,"freezer-capasity").getTextContent().trim()));
            fillRefrigerator.setOveralCapacity(Double.parseDouble(getSingleChild(refrElement,"overal-capacity").getTextContent().trim()));
            fillRefrigerator.setHeight(Double.parseDouble(getSingleChild(refrElement,"height").getTextContent().trim()));
            fillRefrigerator.setWidth(Double.parseDouble(getSingleChild(refrElement,"height").getTextContent().trim()));
            appliancedList.add(fillRefrigerator);
            fillRefrigerator=null;

        }
        return  appliancedList;
    }

           private ArrayList<Appliance> createVcCleanerList(ArrayList<Appliance> appliancedList, NodeList vcCleanerNode) {
        VacuumCleaner fillVcCleaner;

        for(int i=0; i<vcCleanerNode.getLength();i++){

            fillVcCleaner=new VacuumCleaner();

            Element vcCleanElement=(Element)vcCleanerNode.item(i);

            fillVcCleaner.setType(getSingleChild(vcCleanElement,"type").getTextContent().trim());
            fillVcCleaner.setId(vcCleanElement.getAttribute("id"));
            fillVcCleaner.setPowerConsumption(Integer.parseInt(getSingleChild(vcCleanElement,"power-consumption").getTextContent().trim()));
            fillVcCleaner.setFilterType(getSingleChild(vcCleanElement,"filter-type").getTextContent().trim());
            fillVcCleaner.setBagType(getSingleChild(vcCleanElement,"bag-type").getTextContent().trim());
            fillVcCleaner.setWandType(getSingleChild(vcCleanElement,"wand-type").getTextContent().trim());
            fillVcCleaner.setMotorSpeedRegulation(Integer.parseInt(getSingleChild(vcCleanElement,"motor-speed-regulation").getTextContent().trim()));
            fillVcCleaner.setCleaningWidth(Integer.parseInt(getSingleChild(vcCleanElement,"cleaning-width").getTextContent().trim()));
            appliancedList.add(fillVcCleaner);
            fillVcCleaner=null;
        }
        return appliancedList;

    }

           private  ArrayList<Appliance> createTabletPCList(ArrayList<Appliance> appliancedList, NodeList tabletPCNode) {
        TabletPC fillTabletPC;

        for(int i=0;i< tabletPCNode.getLength();i++){

            fillTabletPC=new TabletPC();
            Element tabletPCElement=(Element)tabletPCNode.item(i);

            fillTabletPC.setType(getSingleChild(tabletPCElement,"type").getTextContent().trim());
            fillTabletPC.setId(tabletPCElement.getAttribute("id"));
            fillTabletPC.setBatteryCapacity(Double.parseDouble(getSingleChild(tabletPCElement,"battery-capacity").getTextContent().trim()));
            fillTabletPC.setDisplayInch(Double.parseDouble(getSingleChild(tabletPCElement,"display-inch").getTextContent().trim()));
            fillTabletPC.setMemoryRom(Integer.parseInt(getSingleChild(tabletPCElement,"memory-rom").getTextContent().trim()));
            fillTabletPC.setMemoryCapacity(Integer.parseInt(getSingleChild(tabletPCElement,"memory-rom").getTextContent().trim()));
            fillTabletPC.setColor(getSingleChild(tabletPCElement,"memory-rom").getTextContent().trim());
            appliancedList.add(fillTabletPC);
            fillTabletPC=null;

        }
        return appliancedList;
    }

           private  ArrayList<Appliance> createSpeakerList(ArrayList<Appliance> appliancedList, NodeList speakersNode) {
            Speakers fillSpeakers=new Speakers();

            for(int i=0; i<speakersNode.getLength();i++){

                fillSpeakers=new Speakers();
                ArrayList<Appliance> vacuumClList = new ArrayList<>();

                Element speakersElement=(Element)speakersNode.item(i);

                fillSpeakers.setId(speakersElement.getAttribute("id"));
                fillSpeakers.setPowerConsumption(Integer.parseInt(getSingleChild(speakersElement,"power-consumtion").getTextContent().trim()));
                fillSpeakers.setNumberOfSpeakers(Integer.parseInt(getSingleChild(speakersElement,"number-of-speakers").getTextContent().trim()));
                fillSpeakers.setFrequanseRange(getSingleChild(speakersElement,"frequence-range").getTextContent().trim());
                appliancedList.add(fillSpeakers);
                fillSpeakers=null;
            }
            return appliancedList;

    }

           private static Element getSingleChild(Element element, String childName) {
            NodeList nlist = element.getElementsByTagName(childName);
            Element child = (Element) nlist.item(0);
            return child;
        }


    }


