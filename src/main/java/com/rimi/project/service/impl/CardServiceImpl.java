package com.rimi.project.service.impl;

import com.rimi.project.dao.CardDao;
import com.rimi.project.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    CardDao cardDao;

//    @Autowired
//    TransactionTemplate transactionTemplate;

    @Override

    public void snedMoney(String from, String to, double money) {

//        transactionTemplate.execute(new TransactionCallback<Object>() {
//
//            @Override
//            public Object doInTransaction(TransactionStatus transactionStatus) {
//                cardDao.in(to,money);
//                int c = 1/0;
//                cardDao.out(from,money);
//                return null;
//            }
//        });
            cardDao.in(to,money);
//            int c = 1/0;
            cardDao.out(from,money);
    }
}
