package com.zzy.shorturl.api;

public class Main {
    public static void main(String[] args) {
        StringGeneratorRandom stringGeneratorRandom = StringGeneratorRandom.getInstance();
        stringGeneratorRandom.setLength(4);
        String str = stringGeneratorRandom.generate("2");
        System.out.println(str);
    }
}
