package com.infosys.ekart.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infosys.ekart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByBrand(String brand);

	List<Product> findByProductname(String productname);

	List<Product> findByCategory(String category);

	Iterable<Product> findBySellerid(Integer sellerid);
	
}
