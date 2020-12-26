package com.lhs.www.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class CaffeineController {

    @CachePut(value = "people", key = "#id")
    public void testCache(String id) {
        log.info("为id、key为:" + id + "数据做了缓存");
    }


    @CachePut(key = "#id")
    public void remove(String id) {
        log.info("删除了id、key为" + id + "的数据缓存");
    }


    /**
     * Cacheable
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
     */
    @Cacheable(value = "people", key = "#id", sync = true)
    public String findOne(String id, String a, String[] b, List<Long> c) {
        log.info("为id、key为:" + id + "数据做了缓存");
        return id;
    }
}
