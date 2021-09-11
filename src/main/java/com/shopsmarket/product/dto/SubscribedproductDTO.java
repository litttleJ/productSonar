package com.shopsmarket.product.dto;

import javax.validation.constraints.Min;

import com.shopsmarket.product.entity.Subscribedproduct;

public class SubscribedproductDTO {
	@Min(value = 1,message="Cannot be 0 or left empty")
	int buyerid;
	@Min(value = 1,message="Cannot be 0 or left empty")
	int prodid;
	@Min(value = 1,message="Cannot be 0 or left empty")
	int quantity;
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public SubscribedproductDTO(int buyerid, int prodid, int quantity) {
		super();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}

	
	public SubscribedproductDTO() {
		super();
	}
	
	// Converts Entity into DTO
		public static SubscribedproductDTO valueOf(Subscribedproduct subproduct) {
			SubscribedproductDTO subscribedprodDTO = new SubscribedproductDTO();
			subscribedprodDTO.setBuyerid(subproduct.getBuyerid());
			subscribedprodDTO.setProdid(subproduct.getProdid());
			subscribedprodDTO.setQuantity(subproduct.getQuantity());
			return subscribedprodDTO;
		}
		
		//Converts DTO into Entitiy
		public Subscribedproduct createEntity() {
			Subscribedproduct sprod= new Subscribedproduct();
			sprod.setBuyerid(this.getBuyerid());
			sprod.setProdid(this.getProdid());
			sprod.setQuantity(this.getQuantity());
			return sprod;
		}

		@Override
		public String toString() {
			return "subscribedprodDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
		}

}
