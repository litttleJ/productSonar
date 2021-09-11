package com.shopsmarket.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopsmarket.product.dto.ProductDTO;
import com.shopsmarket.product.dto.SubscribedproductDTO;
import com.shopsmarket.product.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productservice;
	
	//Adding a product
	@PostMapping(value = "/product",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createProduct(@Valid@RequestBody ProductDTO productDTO) throws Exception {
		logger.info("Creation request for product{}",productDTO);
		String success=productservice.createProduct(productDTO);
		return success;
	}
	
	//Getting all products
	@GetMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() {

		logger.info(" Request for All products ");
		return productservice.getAllProducts();
	}
	
	//Getting all productsubscribed
		@GetMapping(value = "/subscribedproduct",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<SubscribedproductDTO> getAllSubProducts() {

			logger.info(" Request for All subscribed products ");
			return productservice.getAllSubProducts();
		}
		
	//Getting products based on Seller Id
	@GetMapping(value = "/product/seller/{sellerid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductBasedOnSellerId(@PathVariable Integer sellerid) {

		logger.info("Request for products based on seller id {}",sellerid);

		return productservice.getProductBasedOnSellerId(sellerid);
	}
	
	
	
//@RequestMapping(value = "/product/{productname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	
	//Getting List of products based on name or category
	@GetMapping(value = "/product/search/{productname}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductByNameorCategory(@PathVariable String productname) {

		logger.info("Search for product : {}", productname);
		return productservice.getProductByNameOrCategory(productname);
	}
	
	//Getting Product based on Product Id
	@GetMapping(value = "/product/{prodid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductById(@PathVariable int prodid) {

		logger.info("Get product based on id :  {}", prodid);
		return productservice.getProductById(prodid);
	}
	
	
    //Adding new subscription Product to buyer
	@PostMapping(value = "/subscriptions/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createSubscription(@Valid@RequestBody SubscribedproductDTO sdto) {
		logger.info("Creation request for subscribed product {}",sdto);
		String success=productservice.createSubscription(sdto);
		return success;
	}
	
	@DeleteMapping(value = "/subscriptions/delete/{buyerid}/{prodid}")
	public String deleteubscription(@PathVariable int buyerid,@PathVariable int prodid) {
		logger.info("Deletion request for subscribed product ");
		String success=productservice.deleteSubscription(buyerid,prodid);
		return success;
	}
	
	
	
//	@GetMapping(value = "/subscribedproduct",produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<SubscribedproductDTO> getSubscribedProduct() {
//
//		logger.info("All Subscribed Products  {}");
//
//		return productservice.getAllSubscribedProducts();
//	}
	
	//Delete a product based on product id
	@DeleteMapping(value = "/product/{prodid}")
	public String deleteProductBasedOnProdId(@PathVariable Integer prodid){
		
		return productservice.deleteProductId(prodid);
	}
		
	//Delete products based on Seller Id
		@DeleteMapping(value = "/product/seller/{sellerid}")
		public String deleteProductsBasedOnSellerId(@PathVariable Integer sellerid){
			
			return productservice.deleteProductSellerId(sellerid);
		}
		
	
	
	//Update a product
//	@PatchMapping(value="/product/update/{prodid}")
//    public ProductDTO updateProduct(@PathVariable Integer prodid,@RequestBody ProductDTO pdto) throws Exception{
//		if(pdto.getStock()<10) {
//			throw new Exception("Stock value must be greater than or equal to 10");
//		}
//		return productservice.updateProductStock(prodid,pdto.getStock());
//	}
	
	@PostMapping(value="/product/seller/{prodid}")
    public ProductDTO updateProductthroughPostSeller(@PathVariable Integer prodid,@RequestBody ProductDTO pdto) throws Exception{
		if(pdto.getStock()<10) {
			throw new Exception("Stock value must be greater than or equal to 10");
		}
		return productservice.updateProductStock(prodid,pdto.getStock());
	}
	

	@PostMapping(value="/product/buyer/{prodid}")
    public ProductDTO updateProductthroughPostBuyer(@PathVariable Integer prodid,@RequestBody ProductDTO pdto) throws Exception{
		
		return productservice.updateProductStock(prodid,pdto.getStock());
	}
	
	@PatchMapping(value="/product/{prodid}")
    public ProductDTO updateProduct(@PathVariable Integer prodid,@RequestBody ProductDTO pdto) throws Exception{
		if(pdto.getStock()<10) {
			throw new Exception("Stock value must be greater than or equal to 10");
		}
		return productservice.updateProductStock(prodid,pdto.getStock());
	}
	
	
}
