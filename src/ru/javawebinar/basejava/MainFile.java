package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".\\.gitignore");
        System.out.println(file.canWrite());
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e); // оборачиваем checked exception в runtime exception
        }
        File dir = new File(".\\src\\ru\\javawebinar");
        System.out.println(dir.isDirectory());
        printDirectories(dir);

        //Input Output Stream API, довольно низкоуровневая работа чтения байтов. пример
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public static void printDirectories(File dir) {
        File[] files = dir.listFiles();
        if(files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("File: " + file.getName());
                    ;
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    printDirectories(file);
                }
            }
        }
    }
}
