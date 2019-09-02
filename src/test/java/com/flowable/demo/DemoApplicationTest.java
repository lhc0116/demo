package com.flowable.demo;

import com.flowable.demo.entity.PassUser;
import com.flowable.demo.entity.UserEntity;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static StopWatch stopWatch = new StopWatch();

    @Test
    public void queryForObject() {
        this.queryForCount1();
        this.queryForObject1();
        this.queryForObject2();
        this.queryForObject3();
        this.queryForList1();
        this.queryForList2();
    }

    /**
     * 只能接受String,Integer这种单列类型的实体,否则汇报异常
     */
    @Test
    public void queryForCount1() {
        String sql = "select count(1) from pass_user where id > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, 300000);
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", count);
    }

    /**
     * 只能接受String,Integer这种单列类型的实体,否则汇报异常
     */
    @Test
    public void queryForObject1() {
        String sql = "select NAME from pass_user where id = ?";
        String name = jdbcTemplate.queryForObject(sql, String.class, 30);//查询结果空集时会报EmptyResultDataAccessException异常
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", name);
    }

    /**
     * 可以自动进行驼峰匹配
     */
    @Test
    public void queryForObject2() {
        String sql = "select * from pass_user where id = ?";
        RowMapper<PassUser> rowMapper = new BeanPropertyRowMapper<>(PassUser.class);
        PassUser passUser = jdbcTemplate.queryForObject(sql, rowMapper, 180);//查询结果空集时会报EmptyResultDataAccessException异常
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", passUser);
    }

    /**
     * 实体类需要实现接口,覆写方法
     */
    @Test
    public void queryForObject3() {
        String sql = "select * from pass_user where id = ?";
        UserEntity userEntity = jdbcTemplate.queryForObject(sql, new UserEntity(), 180);//查询结果空集时会报EmptyResultDataAccessException异常
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", userEntity);
    }


    /**
     * 实体类需要实现接口,覆写方法
     */
    @Test
    public void queryForList1() {
        String sql = "select name from pass_user where id < ?";
        List<String> names = jdbcTemplate.queryForList(sql, String.class, 50);//只能查询单列属性集合
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", names);
    }

    /**
     * 实体类需要实现接口,覆写方法
     */
    @Test
    public void queryForList2() {
        String sql = "select * from pass_user where id < ?";
        List<UserEntity> userEntityList = jdbcTemplate.query(sql, new UserEntity(), 0);
        LOGGER.info("[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] {}", userEntityList);
    }

    @Test
    public void insert() {
        String sql = "insert into pass_user (name,age,gender,birthday,create_time,update_time) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, "\uD854\uDE8D", 18, "male", Instant.now().toEpochMilli(), Instant.now().toEpochMilli(), Instant.now().toEpochMilli());
    }

    @Test
    public void delete() {
        String sql = "delete from pass_user where id = ?";
        jdbcTemplate.update(sql, 8254);
    }

    @Test
    public void batchInsert() {
        stopWatch.start();
        String sql = "insert into pass_user (name,age,gender,birthday,create_time,update_time) values (?,?,?,?,?,?)";
        List<Object[]> args = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            args.add(new Object[]{"陆小凤" + i, 18 + i & 3, i % 2 == 0 ? "female" : "male", Instant.now().toEpochMilli(), Instant.now().toEpochMilli(), Instant.now().toEpochMilli()});
        }
        jdbcTemplate.batchUpdate(sql, args);
        stopWatch.split();
        System.out.println(stopWatch.getSplitTime());
    }

    @Test
    public void batchUpdate() {
        stopWatch.start();
        String sql = "update pass_user set age=80,gender='female' where id = ?";
        List<Object[]> args = new ArrayList<>();
        for (int i = 72500; i < 82500; i++) {
            args.add(new Object[]{i});
        }
        jdbcTemplate.batchUpdate(sql, args);
        stopWatch.split();
        System.out.println(stopWatch.getSplitTime());
    }

    @Test
    public void batchDelete() {
        stopWatch.start();
        String sql = "DELETE  FROM pass_user  where id = ?";
        List<Object[]> args = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            args.add(new Object[]{i});
        }
        jdbcTemplate.batchUpdate(sql, args);
        stopWatch.split();
        System.out.println(stopWatch.getSplitTime());
    }
}