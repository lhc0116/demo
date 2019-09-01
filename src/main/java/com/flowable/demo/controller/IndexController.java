package com.flowable.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author li huaichuan
 * @date 19-8-4
 */
@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/index")

    public ResponseEntity<HttpStatus> index() {
        String sql = "select count(1) from pass_user where id > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, 300);
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", count);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
