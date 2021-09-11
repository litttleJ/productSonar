package com.shopsmarket.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsmarket.product.dto.SubscribedproductDTO;
import com.shopsmarket.product.entity.Subscribedproduct;
import com.shopsmarket.product.entity.SubscribedproductId;

public interface SubscribedproductRepository extends JpaRepository<Subscribedproduct,SubscribedproductId> {
	
	public SubscribedproductDTO findByBuyeridAndProdid(Integer buyerid,Integer prodid);

}
