package com.indigoSky.app;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

import static com.indigoSky.app.core.PropertyReader.appiumIP;
import static com.indigoSky.app.core.PropertyReader.appiumPort;

public class AppiumController {

    public static Logger LOG = LoggerFactory.getLogger(AppiumController.class);
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities capabilities;

    public void startServer() {

        //Build the Appium service
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", "false");
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(appiumIP);
        builder.usingPort(Integer.parseInt(appiumPort));
        builder.withCapabilities(capabilities);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        if(service!=null)
            service.stop();
    }

    public boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void startAppiumServer( ) {
        AppiumController appiumServer = new AppiumController();
        int port = Integer.parseInt(appiumPort);
        if(!appiumServer.checkIfServerIsRunnning(port)) {
            appiumServer.startServer();
            LOG.info("Appium server has been started on Port - "+port);
        } else {
            LOG.info("Appium Server already running on Port - " + port);
        }
    }

    public static void stopAppiumServer( ) {
        AppiumController appiumServer = new AppiumController();
        int port = Integer.parseInt(appiumPort);
        if(appiumServer.checkIfServerIsRunnning(port)) {
            appiumServer.stopServer();
            LOG.info("Appium server has been stopped at server - " + port);
        } else {
            LOG.info("There is no Appium Server is running on Port - " + port);
        }
    }
}
