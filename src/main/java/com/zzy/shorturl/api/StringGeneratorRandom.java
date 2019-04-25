package com.zzy.shorturl.api;

import java.util.Random;

/**
 * 随机字符串发生器的默认实现类
 */
public class StringGeneratorRandom {
    public static char[] VALID_CHARS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static Random random = new Random(System.currentTimeMillis());
    private int length = 4;

    private static StringGeneratorRandom stringGeneratorRandom;

    private StringGeneratorRandom(){
    }

    public static StringGeneratorRandom getInstance(){
        if (stringGeneratorRandom == null){
            stringGeneratorRandom = new StringGeneratorRandom();
        }
        return stringGeneratorRandom;
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    String generate(int seed) {

        char[] sortUrl = new char[length];
        for (int i = 0; i < length; i++) {
            System.out.println(i + " == " + seed);
            sortUrl[i] = VALID_CHARS[seed % VALID_CHARS.length];
            seed = random.nextInt(Integer.MAX_VALUE) % VALID_CHARS.length;
            System.out.println("sortUrl == " + sortUrl[i]);
        }
        return new String(sortUrl);
    }

    /**
     * 这里的实现不考虑url，直接生成随机字符串即可，这个算法如果容量比较大的时候，性能会变低，因此要根据使用情况选择合适的长度
     *
     * @param url
     * @return
     */
    public String generate(String url) {
        String shortUrl;
        shortUrl = generate(random.nextInt(Integer.MAX_VALUE));
        return shortUrl;
    }
}
