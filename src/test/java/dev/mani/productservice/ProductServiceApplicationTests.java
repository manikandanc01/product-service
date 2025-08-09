package dev.mani.productservice;

import dev.mani.productservice.models.Product;
import dev.mani.productservice.repositories.ProductRepository;
import dev.mani.productservice.repositories.projections.ProductTitleAndDesc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;


	@Test
	void contextLoads() {

	}

	@Test
	public void commonTestMethod(){
//		test2();
//		test3();
		test4();
	}



	@Test
	public void test(){
		Optional<Product>  productOptional = productRepository.findByTitleAndCategory_name("Jeans","clothes");
		if(productOptional.isPresent()){
			Product product = productOptional.get();
			System.out.println(product.getDescription());
		}else{
			System.out.println("Product not found");
		}
	}

	@Test
	public void test2(){
		List<Product> products= productRepository.getProductDetails2("stationaries");
		System.out.println(products.get(0).getTitle() + " "+products.get(0).getDescription());
	}

	@Test
	public void test3(){
		Product product = productRepository.getProductDetails3(252L);
		System.out.println(product.getTitle() + " " + product.getDescription());
	}

	@Test
	public void test4(){
		ProductTitleAndDesc product = productRepository.getProductTitleAndDescById(1L);
		System.out.println(product.getTitle() + " " + product.getDescription());
	}




}
