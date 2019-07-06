package com.studentAdmin.controller;

import com.studentAdmin.domain.Dto.RedisDto;
import com.studentAdmin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author:liu.yucheng
 * @Data:2019-3-4 9:34
 * @version:1.0
 */
@RequestMapping("/redis")
@Controller
public class RedisTestController {
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/redisTest.do")
    @ResponseBody
    public Integer redisTest() {
        String str = "string";//1.字符串
        List<String> list = new ArrayList<String>();//list
        list.add("0");
        list.add("中国");
        list.add("2");
        Set<String> set = new HashSet<String>();//set
        set.add("0");
        set.add("中国");
        set.add("2");
        Map<String, Object> map = new HashMap();//map
        map.put("key1", "str1");
        map.put("key2", "中国");
        map.put("key3", "str3");


        redisUtil.del("myStr", "str");//删除数据


        //1.字符串操作
        redisUtil.set("str", str);
        redisUtil.expire("str", 120);//指定失效时间为2分钟
        String str1 = (String) redisUtil.get("str");
        System.out.println(str1);

        //2.list操作
        redisUtil.lSet("list", list);
        redisUtil.expire("list", 120);//指定失效时间为2分钟
        List<Object> list1 = redisUtil.lGet("list", 0, -1);
        System.out.println(list1);

        //3.set操作
        redisUtil.sSet("set", set);
        redisUtil.expire("set", 120);//指定失效时间为2分钟
        Set<Object> set1 = redisUtil.sGet("set");
        System.out.println(set1);


        //3.map操作
        redisUtil.hmset("map", map);
        redisUtil.expire("map", 120);//指定失效时间为2分钟
        Map<Object, Object> map1 = redisUtil.hmget("map");
        System.out.println(map1);
        return 1;
    }
        @RequestMapping("/redisTest2.do")
        @ResponseBody
       public Integer redisDto(){
            List<RedisDto> redisDtos = new ArrayList<>();
            redisDtos.add(new RedisDto("a","b"));
            redisDtos.add(new RedisDto("a","b"));
            redisDtos.add(new RedisDto("a","b"));
            redisUtil.lSet("list2",redisDtos,900000);
          return 2;
        }


    }

