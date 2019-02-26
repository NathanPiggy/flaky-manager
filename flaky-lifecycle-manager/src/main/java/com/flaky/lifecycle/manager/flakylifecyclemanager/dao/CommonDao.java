package com.flaky.lifecycle.manager.flakylifecyclemanager.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;

public class CommonDao extends SqlSessionDaoSupport {
     @Resource
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
         super.setSqlSessionFactory(sqlSessionFactory);
     }

    @Override
    protected void checkDaoConfig() throws IllegalArgumentException {

    }
}