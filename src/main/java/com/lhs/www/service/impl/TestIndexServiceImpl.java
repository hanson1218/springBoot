package com.lhs.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.www.dao.TestIndexDao;
import com.lhs.www.entity.TestIndex;
import com.lhs.www.service.TestIndexService;

@Service
public class TestIndexServiceImpl implements TestIndexService {

	
	@Autowired
	private TestIndexDao testIndexDao;
	@Override
	public int insert(TestIndex record) {
		record.setProductid(record.getProductid()+1);
		for(int i = 0;i<100000;i++) {
			record.setProductid(i+1);
			record.setUserid(i+1);
			testIndexDao.insert(record);
		}
		return 1;
	}

}
