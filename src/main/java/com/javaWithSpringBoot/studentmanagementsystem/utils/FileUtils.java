package com.javaWithSpringBoot.studentmanagementsystem.utils;

import java.io.*;

/**
 * Created by sailesh on 11/23/21.
 */
public class FileUtils {

    public static final String BASE_PATH = "./src/files/";

    public static File getFile(String fileName) {
        //if not exists create file
        //else send the reference of existing file

        if(fileName == null || fileName.isEmpty()) {
            System.out.println("Filename is Empty!");
            return  null;
        }

        String path = BASE_PATH + fileName;

       try {
           File file = new File(path);
           if( file.createNewFile()) {
               System.out.println(fileName + "created successfully");
           }
           return file;
       } catch (IOException e) {
           System.out.println("An error is occured while getting the file.");
           e.printStackTrace();
       }
        return null;
    }

    public static Boolean appendDateToFile(File file, String record) {
        return writeDataToFile(file, record, true);
    }

    public static  Boolean writeDataToFile(File file, String record) {
        return writeDataToFile(file, record, false);
    }

    private static  Boolean writeDataToFile(File file, String record, Boolean isAppend) {
        try{
            FileWriter fileWriter = new FileWriter(file,isAppend);
            String newRecord = record+"\n";
            fileWriter.write(newRecord);

            fileWriter.close();
            return true;

        } catch (IOException e) {
            System.out.println("An error is occured while writing to the file" );
            e.printStackTrace();
        }
        return  false;

    }

    public static Integer getLastIndexOfFile(File file) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lastLine = null;
            while(reader.ready()) {
                lastLine =reader.readLine();
            }

            if(lastLine != null && !lastLine.isEmpty()) {
                String[] fields = lastLine.split(",");
                return Integer.parseInt(fields[0].trim());
            }
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return  0;
    }

    public static String readDataFromFile(File file) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while(reader.ready()) {
                String line = reader.readLine();
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return null;
    }

    public static String readDataFromFileBasedOnIndex(File file, Integer index) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                String line = reader.readLine();
                String[] fields = line.split(",");
                Integer indexFromFile = Integer.parseInt(fields[0].trim());
                if(indexFromFile.equals(index)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return null;
    }

    public static String readDateFromFileBasedOnIndexAndValue(File file, Integer index, Integer value) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while(reader.ready()) {
                String line = reader.readLine();
                String[] fields = line.split(",");
                Integer indexFromFile = Integer.parseInt(fields[index].trim());

                if(indexFromFile.equals(value)) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return null;
    }
}
