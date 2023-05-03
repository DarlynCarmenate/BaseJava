package ru.javawebinar.basejava;

public class Solution {
    public static String material= "материальный";
    public static String immaterial= "нематериальный";

    public static void main(String[] args) {
        System.out.println("Из болота вылез нематериальный монстр...");

        switchSword(immaterial);
    }

    public static void switchSword(String s) {
        System.out.println("Меч переключен в" + s + "режим.");
    }
}