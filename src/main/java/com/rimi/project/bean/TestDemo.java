package com.rimi.project.bean;

import com.rimi.project.service.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 以单元测试方式运行
@RunWith(SpringJUnit4ClassRunner.class)
// 指定spring的配置
@ContextConfiguration("classpath:Spring.xml")
public class TestDemo {

    // xml中配置的bean的id
    @Resource(name = "jdbc")
    JdbcTemplate jdbc;

    @Autowired
    CardService cardService;


    @Test
    public void test2() {


        cardService.snedMoney("1","2",500);
    }

    @Test
    public void test1() {
        jdbc.update("insert into user(username,password) values(?,?)","luc","123");
    }

    @Test
    public void test() {
        // 创建JDBC驱动
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/class1?serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        // 建立JDBC模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 插入数据
        jdbcTemplate.update("insert into user(username,password) values(?,?)","luc","123");

        // 查询数据
        List<User> users = jdbcTemplate.query("select * from user",new RowMapper<User>(){
            // 指定查询数据的映射
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));

                user.setUsername(resultSet.getString("username"));

                user.setPassword(resultSet.getString("password"));

                return user;
            }
        });
        System.out.println(users);
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:Spring.xml");

        User user = (User)context.getBean("user");
//
//        System.out.println(user);
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbc");
//        jdbcTemplate.update("insert into user(username,password) values(?,?)","luc","123");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
