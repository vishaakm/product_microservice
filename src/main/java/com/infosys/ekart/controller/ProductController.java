package com.infosys.ekart.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.dto.ProductDTO;
import com.infosys.ekart.entity.Product;
import com.infosys.ekart.model.ProductId;
import com.infosys.ekart.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public Product addProdcuct(@RequestBody ProductDTO product) {
		LOGGER.info("Adding Single Product");
		return productService.saveProduct(product.convertToEntity());
	}

	@PostMapping("/add/many")
	public Iterable<Product> addProdcuct(@RequestBody Iterable<ProductDTO> products) {
		List<Product> productEntities = new ArrayList<>();
		for (ProductDTO product : products) {
			productEntities.add(product.convertToEntity());
		}
		LOGGER.info("Adding " +  productEntities.size() + " Product");
		return productService.saveProducts(productEntities);
	}

	@GetMapping()
	public Iterable<Product> getProducts() {
		LOGGER.info("Retriveing all Products");


		return productService.getProducts();
	}

	@PostMapping()
	public Iterable<Product> getProductsByIds(@RequestBody ProductId productIds) {
		LOGGER.info("Retriveing Selected Products");

		return productService.getProductsByIds(productIds);
	}

	@GetMapping("/seller/{sellerId}")
	public Iterable<Product> getProductsOfseller(@PathVariable(name = "sellerId") Integer sellerId) {
		LOGGER.info("Retriveing Products based on sellerId " + sellerId);
		return productService.getProductsBySellerId(sellerId);
	}

	@GetMapping("/{productId}")
	public Product findProductById(@PathVariable(name = "productId") Integer productId) {
		LOGGER.info("Retriveing Products based on productId " + productId);
		return productService.getProductById(productId);
	}

	@GetMapping("/search/{keyword}")
	public List<Product> searchProducts(@PathVariable(name = "keyword") String keyword) {
		LOGGER.info("Retriveing Products based on keyword " + keyword);

		List<Product> result = productService.getProductsByBrand(keyword);
		result.addAll(productService.getProductsByCategory(keyword));
		result.addAll(productService.getProductsByName(keyword));

		return result;
	}

	@DeleteMapping("/remove/{productId}")
	public String removeProduct(@PathVariable(name = "productId") @NonNull Integer productId) {
		productService.deleteProduct(productId);

		if (productService.getProductById(productId) == null) {
			LOGGER.info("Removing Products based on productId " + productId);
			return "Success";

		}
		return "Failed";
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody ProductDTO product) {
		Product productEntity = product.convertToEntity();
		LOGGER.info("Updating Product ", product.getProdId());

		return productService.updateProduct(productEntity);
	}

	@PutMapping("/update/stock")
	public Product updateStock(@RequestBody ProductDTO product) {
		Product productEntity = product.convertToEntity();
		LOGGER.info("Updating Product Stock", product.getProdId());

		return productService.updateStock(productEntity.getProdId(), product.getStock());
	}
}
