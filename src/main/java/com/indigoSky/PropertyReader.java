package com.indigoSky;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    public static Properties properties;

    public static String appiumIP = "";
    public static String appiumPort = "";
    public static String androidDeviceName = "";
    public static String deviceOrientation = "";
    public static String chromedriverExecutable = "";
    public static String platformName = "";
    public static String platformVersion = "";
    public static String tenantID = "";
    public static String app = "";
    public static String appPackage = "";
    public static String appActivity = "";

    public String propFile = "src/test/resources/application.properties";
    private String propFileOnJenkins ="../application.properties";

    public PropertyReader() throws Exception {
        InputStream inputStream;
        try {
            File file = FileUtils.getFile(propFileOnJenkins);
            if (file.exists()) {
                inputStream = new FileInputStream(propFileOnJenkins);
            } else {
                inputStream = new FileInputStream(propFile);
            }
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            properties = new Properties();
            properties.load(reader);
            for (String name : properties.stringPropertyNames()) {
                System.setProperty(name, System.getProperty(name, properties.getProperty(name)));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        setAllProperties();
    }

    public void setAllProperties() {
        appiumIP = System.getProperty("appiumIP");
        appiumPort = System.getProperty("appiumPort");
        androidDeviceName = System.getProperty("deviceName");
        // deviceOrientation = System.getProperty("mob.deviceOrientation");
        chromedriverExecutable = System.getProperty("chromedriverExecutable");
        platformName = System.getProperty("platformName");
        platformVersion = System.getProperty("platformVersion");
        app = System.getProperty("app");
        appPackage = System.getProperty("appPackage");
        appActivity = System.getProperty("appActivity");
    }
}
