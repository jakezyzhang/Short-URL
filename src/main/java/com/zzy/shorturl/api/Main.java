package com.zzy.shorturl.api;

public class Main {
    public static void main(String[] args) {
        StringGeneratorRandom stringGeneratorRandom = StringGeneratorRandom.getInstance();
        stringGeneratorRandom.setLength(6);
        String str = stringGeneratorRandom.generate("1");
        System.out.println(str);
    }
}
