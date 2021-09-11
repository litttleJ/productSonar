package com.shopsmarket.product.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.shopsmarket.product.entity.Product;

public class ProductDTO {

	private Integer prodid;
	
	@NotNull(message = "{Name cannot Be Null}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="Name should contain only alphabets and space")
	private String productname;
	
	@Min(value = 200, message = "Price should not be less than 200")
   // @Max(value = 150, message = "Age should not be greater than 150")
	private double price;
	
	@Min(value = 10, message = "Stock should not be less than 10")
	private Integer stock;
	@NotBlank(message="Cannot be blank")
	 @Size(max = 500, message 
     = "Must be within 500 characters")
	private String description;
	
	@NotBlank
	//@Pattern(regexp="[^\\s]+(\\.(jpg,png))$", message="Should be jpg or png format")
	private String image;
	@Min(value=1)
	private Integer sellerid;
	@NotBlank(message="Category be blank")
	private String category;
	@NotBlank(message="Sub-Category be blank")
	private String subcategory;
	@Min(value=1)
	private Integer productrating;
	
	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Integer getProductrating() {
		return productrating;
	}

	public void setProductrating(Integer productrating) {
		this.productrating = productrating;
	}

	public ProductDTO() {
		super();
	}
		
		public ProductDTO(Integer prodid, String productname, Integer price, Integer stock, String description,
				String image, Integer sellerid, String category, String subcategory, Integer productrating) {
			this();
			this.prodid = prodid;
			this.productname = productname;
			this.price = price;
			this.stock = stock;
			this.description = description;
			this.image = image;
			this.sellerid = sellerid;
			this.category = category;
			this.subcategory = subcategory;
			this.productrating = productrating;
		}
		
	
	

	// Converts Entity into DTO
	public static ProductDTO valueOf(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProdid(product.getProdid());
		productDTO.setProductname(product.getProductname());
		productDTO.setPrice(product.getPrice());
		productDTO.setStock(product.getStock());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setSellerid(product.getSellerid());
		productDTO.setCategory(product.getCategory());
		productDTO.setSubcategory(product.getSubcategory());
		productDTO.setProductrating(product.getProductrating());
		return productDTO;
	}
	
	// Converts DTO into Entity
		public Product createEntity() {
				Product prod = new Product();
             //	prod.setProdid(this.getProdid());
				prod.setProductname(this.getProductname());
				prod.setPrice(this.getPrice());
				prod.setStock(this.getStock());
				prod.setDescription(this.getDescription());
				prod.setImage(this.getImage());
			    prod.setSellerid(this.getSellerid());
			    prod.setCategory(this.getCategory());
			    prod.setSubcategory(this.getSubcategory());
			    prod.setProductrating(this.getProductrating());
				return prod;
			}


	@Override
	public String toString() {
		return "ProductDTO [prodid=" + prodid + ", productname=" + productname + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", sellerid=" + sellerid + ", category="
				+ category + ", subcategory=" + subcategory + ", productrating=" + productrating + "]";
	}
}
