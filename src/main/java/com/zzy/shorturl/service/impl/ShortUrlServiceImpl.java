package com.zzy.shorturl.service.impl;

import com.zzy.shorturl.dao.ShortUrlDao;
import com.zzy.shorturl.entity.ShortUrl;
import com.zzy.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlDao shortUrlDao;
    @Override
    public boolean addUrl(ShortUrl shortUrl) {
        if (shortUrl.getUrl() != null && !"".equals(shortUrl.getUrl())){
            shortUrl.setCreateTime(new Date());
            shortUrl.setLastEditTime(new Date());
            shortUrl.setCount(0);
            try {
                int effectedNum = shortUrlDao.insertShortUrl(shortUrl);
                if (effectedNum > 0){
                    return true;
                }else {
                    throw new RuntimeException("插入链接信息失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入链接信息失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("长链接不能为空！");
        }

    }

    @Override
    public boolean modifyShortUrl(ShortUrl shortUrl) {
        if (shortUrl.getUrl() != null && !"".equals((shortUrl.getUrl()))){
            shortUrl.setLastEditTime(new Date());
            try {
                int effectedNum = shortUrlDao.updateShortUrl(shortUrl);
                if(effectedNum > 0){
                    return true;
                }else {
                    throw new RuntimeException("更新链接信息失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新链接信息失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("长链接不能为空！");
        }

    }

    @Override
    public ShortUrl getShortUrlByUrl(String url) {
        if (url == null){
            return null;
        }
        return shortUrlDao.queryShortUrlByUrl(url);
    }

    @Override
    public ShortUrl getUrlbyShortUrl(String shortUrl) {
        if (shortUrl == null){
            return null;
        }
        return shortUrlDao.queryUrlByShortUrl(shortUrl);
    }

    @Override
    public ShortUrl getCountByShortUrl(String shortUrl) {
        if (shortUrl == null){
            return null;
        }
        int n = shortUrl.lastIndexOf("/s/");
        if (n == -1){
            return null;
        }
        String sub = shortUrl.substring(shortUrl.lastIndexOf("/s/")+3, shortUrl.length());
        System.out.println(sub);
        return shortUrlDao.getCountByShortUrl(sub);
    }
}
