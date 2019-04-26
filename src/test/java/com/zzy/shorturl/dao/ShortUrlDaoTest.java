package com.zzy.shorturl.dao;

import com.zzy.shorturl.entity.ShortUrl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortUrlDaoTest {
    @Autowired
    private ShortUrlDao shortUrlDao;
    @Ignore
    @Test
    public void insertShortUrl() {
    }

    @Test
    public void updateShortUrl() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(4);
        shortUrl.setCount(1);
        int effectedNum = shortUrlDao.updateShortUrl(shortUrl);
        assertEquals(1, effectedNum);
    }
    @Ignore
    @Test
    public void queryShortUrlByUrl() {
    }
    @Ignore
    @Test
    public void queryUrlByShortUrl() {
    }
}