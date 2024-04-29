package com.infob.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infob.productservice.entity.Product;

@Service
public interface ProductService {

	List<Product> getAllProduct();
	Product addProduct(Product p);
	void deleteProduct(int id);
	Product findByProductId(int id) throws Exception;
	Product updateProduct(Product p) throws Exception;
}
