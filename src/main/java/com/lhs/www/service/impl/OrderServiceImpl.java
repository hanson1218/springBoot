package com.lhs.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.www.dao.OrderDao;
import com.lhs.www.entity.Order;
import com.lhs.www.entity.Product;
import com.lhs.www.service.OrderService;
import com.lhs.www.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductService productService;
	
	@Override
	@Transactional
	public int insert(Order order) {
		int flag =  orderDao.insertSelective(order);
		System.out.println("add order "+ flag);
		Integer productNum = productService.selectNumById(order.getProductid());
		if (productNum<order.getNum()) {
			throw new RuntimeException();
		}
		Product product = new Product();
		product.setId(order.getId());
		product.setTotalnum(order.getNum());
		productService.updateTotalNumByKey(product);
		return 1;
	}

}
