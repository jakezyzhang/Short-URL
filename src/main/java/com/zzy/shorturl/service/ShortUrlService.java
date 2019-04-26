package com.zzy.shorturl.service;

import com.zzy.shorturl.entity.ShortUrl;

public interface ShortUrlService {
    /**
     * 插入长链接URL和自增id
     * @param shortUrl
     * @return
     */
    boolean addUrl(ShortUrl shortUrl);

    /**
     * 更新短链接信息
     * @param shortUrl
     * @return
     */
    boolean modifyShortUrl(ShortUrl shortUrl);

    /**
     *根据长链接URL查询出短链接
     * @param url
     * @return
     */
    ShortUrl getShortUrlByUrl(String url);

    /**
     * 根据短链接URL查询出长链接并跳转
     * @param shortUrl
     * @return
     */
    ShortUrl getUrlbyShortUrl(String shortUrl);

    /**
     * 根据短链接URL得到短链接访问次数
     * @param shortUrl
     * @return
     */
    int getCountByShortUrl(String shortUrl);
}
