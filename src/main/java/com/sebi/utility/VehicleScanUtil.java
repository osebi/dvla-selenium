package com.sebi.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VehicleScanUtil {

    public static List<String> getRegistrationNumbers(String filePath) {

        BufferedReader bufferedReader;
        String line;
        Scanner scanner;
        List<String> regNumbers = new ArrayList<>();

        try {

            bufferedReader = new BufferedReader(new FileReader(filePath));

            while ((line = bufferedReader.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String regNo = scanner.next();
                    regNumbers.add(regNo);
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return regNumbers;
    }


}
