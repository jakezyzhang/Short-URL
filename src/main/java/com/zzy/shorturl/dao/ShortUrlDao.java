package com.zzy.shorturl.dao;

import com.zzy.shorturl.entity.ShortUrl;

public interface ShortUrlDao {
    /**
     * 插入长链接对应的短链接到数据库中
     * @param shortUrl
     * @return
     */
    int insertShortUrl(ShortUrl shortUrl);

    /**
     * 更新短链接信息
     * @param shortUrl
     * @return
     */
    int updateShortUrl(ShortUrl shortUrl);

    /**
     * 根据长链接URL查询出短链接
     * @param url
     * @return
     */
    ShortUrl queryShortUrlByUrl(String url);

    /**
     * 根据短链接URL查询出长链接并跳转
     * @param shortUrl
     * @return
     */
    ShortUrl queryUrlByShortUrl(String shortUrl);

    /**
     * 根据短链接URL得到短链接访问次数
     * @param shortUrl
     * @return
     */
    int getCountByShortUrl(String shortUrl);
}
