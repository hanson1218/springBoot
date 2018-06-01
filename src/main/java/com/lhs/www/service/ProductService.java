package com.lhs.www.service;

import com.lhs.www.entity.Product;

public interface ProductService {
	 int insert(Product record);
	 int selectNumById(Integer id);
}
