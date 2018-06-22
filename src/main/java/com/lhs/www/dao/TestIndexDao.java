package com.lhs.www.dao;

import com.lhs.www.entity.TestIndex;

public interface TestIndexDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestIndex record);

    int insertSelective(TestIndex record);

    TestIndex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestIndex record);

    int updateByPrimaryKey(TestIndex record);
}