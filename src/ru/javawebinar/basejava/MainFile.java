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
        File dir = new File("C:\\my_java\\Basejava\\basejava");
        System.out.println(dir.isDirectory());
        recur(dir);

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
    public static void recur (File file) {
        for (File name : Objects.requireNonNull(file.listFiles())) {
            if (name.isDirectory()) {
                recur(name);
            } else {
                System.out.println(name);
            }
        }
    }



}
