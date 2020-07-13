package com.ayan.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ayan.model.Product;
import com.ayan.service.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// Configuration for MultiPartResolver
	// Multipart resolver is for uploading images and other media
	// maxupload size is for image size should not be maximum than 10240000
	
//	@Bean
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(10240000);
//		return multipartResolver();
//	}
	
	// RequestMappings
	
	@RequestMapping("/getAllProducts")
	public ModelAndView getAllProducts() {
		List<Product> products  = productService.getAllProducts();
		return new ModelAndView("productList", "products", products);
	}
	
	@RequestMapping("/getProductById/{productId}")
	public ModelAndView getProductById(@PathVariable(value = "productId") String productId) {
		Product product = productService.getProductById(productId);
		return new ModelAndView("productPage", "productObj", product);
	}
	
	@RequestMapping("/admin/product/delete/{productId}")
	public String deleteProduct(@PathVariable(value = "productId") String productId) {
		
		// Path to the product image if it exists
		Path path = Paths.get("C:/Users/Ayan.LAPTOP-6C0932CV/eclipse-workspace/ShoppingCart/src/main/webapp/WEB-INF/resource/images/products/" + productId + ".jpg");
		
		if(Files.exists(path)) {
			try {
					Files.delete(path);
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		
		productService.deleteProduct(productId);
		
		return "redirect:/getAllProducts";
	}
	
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.GET)
	public String getProductForm(Model model) {
		Product product = new Product();
		
		// notSure
		product.setProductCategory("Android");
		
		model.addAttribute("productFormObj", product);
		
		return "addProduct";		
	}
	
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute(value = "productFormObj") Product product, BindingResult result) {
		// Binding Result is used if the form that has any error then it will
		// redirect to the same page without performing any functions
		if(result.hasErrors()) {
			return "addProducts";
		}
		
		productService.addProduct(product);
		MultipartFile image = product.getProductImage();
		if(image!=null && !image.isEmpty()) {
			Path path = Paths.get("C:/Users/Ayan.LAPTOP-6C0932CV/eclipse-workspace/ShoppingCart/src/main/webapp/WEB-INF/resource/images/products/" + product.getProductId() + ".jpg");
			
			try {
					image.transferTo(new File(path.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/getAllProducts";
	}
	
	@RequestMapping(value = "/admin/product/editProduct/{productId}")
	public ModelAndView getEditForm(@PathVariable(name = "productId") String productId) {
		Product product = productService.getProductById(productId);
		return new ModelAndView("editProduct", "editProductObj", product);
	}
	
	@RequestMapping(value = "/admin/product/editProduct", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute(value = "editProductObj") Product product) {
		productService.editProduct(product);
		return "redirect:/getAllProducts";
	}
	
	// For returning ProductList in JSON format
	@RequestMapping("/getProductsList")
	public @ResponseBody List<Product> getProductsListJson() {
		return productService.getAllProducts();
	}
	
	@RequestMapping("/productsListAngular")
	public String getProducts() {
		return "productListAngular";
	}
	
}
