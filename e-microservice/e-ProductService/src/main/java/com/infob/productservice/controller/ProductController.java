package com.infob.productservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infob.productservice.entity.Product;
import com.infob.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public String getTokenDetails(@RequestHeader HttpHeaders headers) {
		return headers.toString();
	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		return new ResponseEntity<List<Product>>(service.getAllProduct(), HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(
			@RequestParam(name = "file", required = false) MultipartFile prodImage,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "price") String price,
			@RequestParam(name = "productname") String productName,
			@RequestParam(name = "quantity") String quantity) throws IOException {
		Product p = new Product();
		p.setProductimage(prodImage.getBytes());
		p.setDescription(description);
		p.setPrice(Double.valueOf(price));
		p.setProductName(productName);
		p.setQuantity(Integer.parseInt(quantity));
		return new ResponseEntity<Product>(service.addProduct(p), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		service.deleteProduct(id);
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(
			@RequestParam(name = "productid", required = false) int productid,
			@RequestParam(name = "file", required = false) MultipartFile prodImage,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "price") String price,
			@RequestParam(name = "productname") String productName,
			@RequestParam(name = "quantity") String quantity) throws Exception {
		System.out.println(productid);
		Product p = new Product();
		p.setProductId(productid);
		p.setProductimage(prodImage.getBytes());
		p.setDescription(description);
		p.setPrice(Double.valueOf(price));
		p.setProductName(productName);
		p.setQuantity(Integer.parseInt(quantity));
		System.out.println(p);
		return new ResponseEntity<Product>(service.updateProduct(p), HttpStatus.OK);
	}
}
