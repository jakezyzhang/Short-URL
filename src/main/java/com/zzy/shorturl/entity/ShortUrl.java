package com.zzy.shorturl.entity;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class ShortUrl {
    //自增主键id
    @Id
    private Integer id;
    //短链接
    private String shortUrl;
    //长链接
    private String url;
    //访问计数
    private int count;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
