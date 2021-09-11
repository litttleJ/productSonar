package com.shopsmarket.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopsmarket.product.dto.ProductDTO;
import com.shopsmarket.product.dto.SubscribedproductDTO;
import com.shopsmarket.product.entity.Product;
import com.shopsmarket.product.entity.Subscribedproduct;
import com.shopsmarket.product.entity.SubscribedproductId;
import com.shopsmarket.product.repository.ProductRepository;
import com.shopsmarket.product.repository.SubscribedproductRepository;

@Service
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	SubscribedproductRepository subscribedRepo;
	
	

//	// Fetches call details of a specific customer
//	public List<ProductDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
//
//		logger.info("Calldetails request for customer {}", phoneNo);
//
//		List<Product> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
//		List<ProductDTO> callsDTO = new ArrayList<ProductDTO>();
//
//		for (Product call : callDetails) {
//			callsDTO.add(ProductDTO.valueOf(call));
//		}
//		logger.info("Calldetails for customer : {}", callDetails);
//
//		return callsDTO;
//	}
	
	//Get product by name or category
	public List<ProductDTO> getProductByNameOrCategory(@PathVariable String ProductnameOrCategory) {

		logger.info(" search based on product name or category: {}", ProductnameOrCategory);

		List<Product> product =productRepo.findByProductnameOrCategory(ProductnameOrCategory, ProductnameOrCategory);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		for (Product pr : product) {
			productDTO.add(ProductDTO.valueOf(pr));
		}
		logger.info("Product according to the name : {}", product);

		return productDTO;
	}
	//Getting Product By id
	public ProductDTO getProductById(int prodid) {
		ProductDTO pdto= new ProductDTO();
		Product prod=productRepo.findById(prodid).orElse(null);
		if(prod==null)
			return null;
		pdto =ProductDTO.valueOf(prod);
		return pdto;
	}
	
	//Get All Products
	public List<ProductDTO> getAllProducts() {
		logger.info("Getting All Products");
		List<Product> product =productRepo.findAll();
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		for(Product pr : product) {
			productList.add(ProductDTO.valueOf(pr));
		}
		return productList;
	}
	
 
	 //Create product 
	public String createProduct(ProductDTO productDTO) throws Exception {
		try {
		Product product = productDTO.createEntity();
		Product psaved=productRepo.save(product);
		logger.info("Product saved: {}",psaved);
		
		String Success="Product:"+psaved.getProductname()+" with product Id:"+psaved.getProdid()+" and sellerid :"+psaved.getSellerid()+" is saved.";
		return Success;
		}
		catch(Exception e) {
			return "Product is already registered under the seller";
		}
		
	}
	
	//delete product
	public String deleteProductId(int prodid) {
		logger.info("Request for deletetion of product with prodid: {}", prodid);	
		 Optional<Product> prod=productRepo.findById(prodid);
		 if(prod.isPresent()!=true) {
			 return "Product not available";
		 }
		 
		 productRepo.deleteById(prodid);
		 return "Deletion SuccessFul";
		
	}
	
	public String deleteProductSellerId(int sellerid) {
		logger.info("Request for deletetion of products with sellerid: {}", sellerid);
		productRepo.deleteAllBySellerid(sellerid);
		return "Deletion Done if element is there";
	}
	//update the stock
	public ProductDTO updateProductStock(int prodid,int stock)  {
		
		logger.info("Request for updation of stocks for product id :{} ", prodid);	
		 Product existingProduct = productRepo.findById(prodid).orElse(null);
		 if(existingProduct != null) {
	            existingProduct.setStock(stock);
	            Product prod= productRepo.save(existingProduct); 
	            return ProductDTO.valueOf(prod);
	        }
	        return null;
	}
	
	//Get product based on sellerid
	public List<ProductDTO> getProductBasedOnSellerId(int sellerid){
		List<Product> product =productRepo.findBySellerid(sellerid);
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		for(Product pr : product) {
			productList.add(ProductDTO.valueOf(pr));
		}
		return productList;
		
	}
	
	
//	public List<SubscribedproductDTO> getAllSubscribedProducts(){
//		
//		
//		Iterable<Subscribedproduct> subscribed = subscribedRepo.findAll();
//		List<SubscribedproductDTO> subscribedDTOs = new ArrayList<>();
//		
//		subscribed.forEach(sub->{
//			SubscribedproductDTO sdto= new SubscribedproductDTO();
//			sdto.setBuyerid(sub.getBuyerid());
//			sdto.setProdid(sub.getProdid());
//			sdto.setQuantity(sub.getQuantity());
//			subscribedDTOs.add(sdto);
//		});
//		
//		return subscribedDTOs;
//	}
	
	
//Create Subscription	
public String createSubscription( SubscribedproductDTO sdto) {
	   	
       SubscribedproductId ob= new SubscribedproductId(sdto.getBuyerid(),sdto.getProdid());
 	   Optional<Subscribedproduct> sprod=subscribedRepo.findById(ob);
// 	  SubscribedproductDTO spdto;
// 	  if(sprod.isPresent()) {
// 		 Subscribedproduct c = sprod.get();
//			spdto = SubscribedproductDTO.valueOf(c);
//			System.out.println("Through getID:"+spdto);
//		}
 	   
	   // spdto=subscribedRepo.findByBuyeridAndProdid(sdto.getBuyerid(), sdto.getProdid());
//	    System.out.println(spdto);
//	    if(spdto!=null) {
//	    	return "Cannot insert data as already exists";
//	    }
 	   if(sprod.isPresent()) {
 		  return "Cannot insert data as following pair of buyerid and prodid already exists";
 	   }
		logger.info("Creation request for subscription {}", sdto);
		
		Subscribedproduct sp = sdto.createEntity();
		Subscribedproduct spsaved=subscribedRepo.save(sp);
		String Success="ProductId:"+spsaved.getProdid()+" BuyerId"+spsaved.getBuyerid()+" Quantity :"+spsaved.getQuantity()+" is saved.";
		return Success;
	}

public String deleteSubscription(int buyerid, int prodid) {
	 SubscribedproductId spid= new SubscribedproductId(buyerid,prodid);
	 Subscribedproduct sp=subscribedRepo.findById(spid).orElse(null);
	 if(sp==null)
		 return "This combn of buyerid and prodid does not exixts";
	 
	 subscribedRepo.deleteById(spid);
	return "Deletion Successfull";
}
public List<SubscribedproductDTO> getAllSubProducts() {
	
	List<Subscribedproduct> sp =subscribedRepo.findAll();
	List<SubscribedproductDTO> spList = new ArrayList<SubscribedproductDTO>();
	for(Subscribedproduct spr :sp) {
		spList.add(SubscribedproductDTO.valueOf(spr));
	}
	return spList;
}

   
	
	
	
}
