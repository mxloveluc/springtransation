package com.rimi.project.dao.impl;

import com.rimi.project.dao.CardDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CardDaoImpl implements CardDao {


    @Resource(name = "jdbc")
    JdbcTemplate jdbc;


    @Override
    public void in(String to, double money) {

        jdbc.update("update card set money = money + ? where name = ?", money,to);
    }

    @Override
    public void out(String from, double money) {
        jdbc.update("update card set money = money - ? where name = ?", money,from);

    }
}
