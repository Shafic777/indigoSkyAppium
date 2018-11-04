<h1 align="center">
  Mobile Test Automation - IndigoSky 
</h1>
<p align="center" style="font-size: 1.2rem;">Mobile automation for Indigo sky app - Android.</p>

<hr />

## Requirements

1. Andriod SDK - For emulators 
2. Appium - For communication with emulators/devices
3. Selenium - For Web application testing


### Cloning the repo

Run the following command to configure the boilerplate on your machine:

```bash
https://github.com/Shafic777/indigoSkyAppium.git
```

## Running Example Tests

> **Note:** For running example tests you need to have installed and configured the android emulator or iOS simulator on your machine.

Before running the mobile tests we need to start the appium server.Start with default host and port (If chnaged need to be updated in application.prop file)

You can find the android and ios example tests inside `/tests` directory. Sample indigo app apk for android inside `/test/resources/apps` directory.

## Android Tests

For running android emulator we are using sdk manager. Follow this [guide](http://www.ntu.edu.sg/home/ehchua/programming/android/android_howto.html) 
if you do not know how to install and run android emulator. You also need to install the application that you want to test inside android emulator.

### Update capabilities

Now you need to update the `application.properties` you can find this file inside `resources` directory. And also update the `deviceName` to your android emulator device name. If you have genymotion installed you can find the device name from the devices list.


### Running Tests

You can run the test file directly or by using testng file
```

## Reports

Allure reports will be genrated under target/allure-reports

To genrate HTML file use this : allure  generate < path where allure xml files are being genrated> --clean


> **Note:** Make sure both emulator and appium server is running before running the android tests..

