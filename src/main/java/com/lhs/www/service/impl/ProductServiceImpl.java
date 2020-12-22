package com.lhs.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.www.dao.ProductDao;
import com.lhs.www.entity.Product;
import com.lhs.www.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Override
	public int insert(Product record) {
		// TODO Auto-generated method stub
		return productDao.insert(record);
	}
	@Override
	public int selectNumById(Integer id) {
		return productDao.selectNumById(id);
	}
	@Override
	public int updateTotalNumByKey(Product record) {
		// TODO Auto-generated method stub
		return productDao.updateTotalNumByKey(record);
	}

}
