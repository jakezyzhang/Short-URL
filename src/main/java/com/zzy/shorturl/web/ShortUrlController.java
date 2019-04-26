package com.zzy.shorturl.web;

import com.zzy.shorturl.api.StringGeneratorRandom;
import com.zzy.shorturl.entity.ShortUrl;
import com.zzy.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/s")
public class ShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * 通过输入的长链接URL获得一个新的短链接URL并存入数据库
     * @param shortUrl
     * @return
     */
    @RequestMapping(value = "/getshorturl", method = RequestMethod.GET)
    private Map<String, Object> getShortUrl(ShortUrl shortUrl){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (shortUrlService.getShortUrlByUrl(shortUrl.getUrl()) != null){
            String result = shortUrlService.getShortUrlByUrl(shortUrl.getUrl()).getShortUrl();
            modelMap.put("shortUrl", result);
            return modelMap;
        }
        shortUrlService.addUrl(shortUrl);
        int urlId = shortUrl.getId();
        StringGeneratorRandom stringGeneratorRandom = StringGeneratorRandom.getInstance();
        stringGeneratorRandom.setLength(4);
        String sUrl = stringGeneratorRandom.generate(String.valueOf(urlId));
        shortUrl.setShortUrl(sUrl);
        shortUrl.setCount(0);
        boolean flag = shortUrlService.modifyShortUrl(shortUrl);
        if (flag == true){
            String newResult = shortUrlService.getShortUrlByUrl(shortUrl.getUrl()).getShortUrl();
            modelMap.put("shortUrl", newResult);
            return modelMap;
        }
        modelMap.put("result", false);
        return modelMap;
    }

    /**
     * 支持自定义短链，可以指定字符串作为短链的key
     * @param shortUrl
     * @return
     */
    @RequestMapping(value = "/appointshorturl", method = RequestMethod.GET)
    public Map<String, Object>appointShortUrl(ShortUrl shortUrl){
        Map<String, Object>modelMap = new HashMap<String, Object>();
        boolean result = shortUrlService.addUrl(shortUrl);
        if (result == true){
             modelMap.put("shortUrl", shortUrl.getShortUrl());
        }
        modelMap.put("result", false);
        return modelMap;
    }

    @RequestMapping(value = {"/{shorturl}"}, method = RequestMethod.GET)
    public ModelAndView jumpUrlByShortUrl(@PathVariable("shorturl") String shortUrl){
        ModelAndView mv = new ModelAndView();
        int sum = 0;
        ShortUrl url = shortUrlService.getUrlbyShortUrl(shortUrl);
        if (url == null) {
            mv.setViewName("redirect:http://www.zyaiyy.cn");
            return mv;
        }else {
            sum = url.getCount() + 1;
            url.setCount(sum);
            shortUrlService.modifyShortUrl(url);
            mv.setViewName("redirect:http://" + url.getUrl());
            return mv;
        }
    }

    @RequestMapping(value = "/getcount", method = RequestMethod.GET)
    public Map<String, Object>getCountByShortUrl(String shortUrl){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int count = shortUrlService.getCountByShortUrl(shortUrl);
        modelMap.put("count", count);
        return modelMap;
    }
}
