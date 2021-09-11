package com.shopsmarket.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsmarket.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer > {

	List<Product> findByProductnameOrCategory(String productname,String category);
	List<Product> findBySellerid(Integer sellerid);
	
	void deleteAllBySellerid(Integer sellerid);

}
