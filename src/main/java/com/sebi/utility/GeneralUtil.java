package com.sebi.utility;

import com.sebi.model.Vehicle;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtil {

    private final static Logger LOG = Logger.getLogger(FileReaderUtil.class);

    public static void writeToCSV(String regNo, String make, String color, String fileName, File folder, boolean isMultipleRegNum) {

        File file = new File(folder, fileName + ".csv");
        FileWriter writer;

        try {
            Vehicle vehicleEntry = new Vehicle(regNo, make, color);
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            if (isMultipleRegNum) {
                if (file.createNewFile()) {
                    LOG.info(file.getName() + " has been created in " + file.getPath());
                }
                writer = new FileWriter(file, true);
                vehicles.add(vehicleEntry);
                writeDataToList(vehicles, writer);
                writer.flush();
                writer.close();
            } else {
                if (file.createNewFile()) {
                    LOG.info(file.getName() + " has been created in " + file.getPath());
                }
                writer = new FileWriter(file);
                vehicles.add(vehicleEntry);
                writeDataToList(vehicles, writer);
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeDataToList(List<Vehicle> vehicles, Writer writer) throws IOException {
        for (Vehicle vehicle : vehicles) {
            List<String> list = new ArrayList<>();
            list.add(vehicle.getRegNo());
            list.add(vehicle.getMake());
            list.add(vehicle.getColour());
            writeLine(writer, list);
        }
    }

    private static void writeLine(Writer writer, List<String> values) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value: values) {
            stringBuilder.append(value).append(",");
        }
        stringBuilder.append("\n");
        writer.append(stringBuilder.toString());
    }

    public static void takeScreenShot(WebDriver driver, String directory) {
        driver.getCurrentUrl();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(directory));
            LOG.info("Screenshot was taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
