package com.zzy.shorturl.web;

import com.zzy.shorturl.api.StringGeneratorRandom;
import com.zzy.shorturl.entity.ShortUrl;
import com.zzy.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/s")
@CrossOrigin
public class ShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * 通过输入的长链接URL获得一个新的短链接URL并存入数据库
     * @param shortUrl
     * @return
     */
    @RequestMapping(value = "/getshorturl", method = RequestMethod.POST)
    @ResponseBody
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
        System.out.println(shortUrl.getUrl());
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
    @RequestMapping(value = "/appointshorturl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>appointShortUrl(ShortUrl shortUrl){
        Map<String, Object>modelMap = new HashMap<String, Object>();
        ShortUrl existenceShort =  shortUrlService.getUrlbyShortUrl(shortUrl.getShortUrl());
        if(existenceShort != null){
            modelMap.put("result", "existence");
            return modelMap;
        }
        boolean result = shortUrlService.addUrl(shortUrl);
        if (result == true){
             modelMap.put("shortUrl", shortUrl.getShortUrl());
        }else {
            modelMap.put("result", false);
        }

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
            mv.setViewName("redirect:" + url.getUrl());
            return mv;
        }
    }

    @RequestMapping(value = "/getcount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>getCountByShortUrl(ShortUrl shortUrl){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String sUrl = shortUrl.getShortUrl();
        ShortUrl countByShortUrl = shortUrlService.getCountByShortUrl(sUrl);
        if (countByShortUrl == null){
            modelMap.put("result", "未生成此短链接");
        }else {
            modelMap.put("result", countByShortUrl);
        }
        return modelMap;
    }
}
