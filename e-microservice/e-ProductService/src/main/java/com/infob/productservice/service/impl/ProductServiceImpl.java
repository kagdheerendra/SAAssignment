package com.infob.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infob.productservice.entity.Product;
import com.infob.productservice.repo.ProductRepository;
import com.infob.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repository;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Product addProduct(Product p) {
		// TODO Auto-generated method stub
		return repository.save(p);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		try {
			Product p = findByProductId(id);
			repository.delete(p);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Product findByProductId(int id) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(id);
		Product p = repository.findById(id).orElseThrow(()->new Exception("Resource not found"));
		return p;
	}

	@Override
	public Product updateProduct(Product p) throws Exception {
		Product product = findByProductId(p.getProductId());
		System.out.println(product);
		product.setDescription(p.getDescription());
		product.setPrice(p.getPrice());
		product.setProductimage(p.getProductimage());
		product.setProductName(p.getProductName());
		product.setQuantity(p.getQuantity());
		// TODO Auto-generated method stub
		return repository.save(product);
	}

}
