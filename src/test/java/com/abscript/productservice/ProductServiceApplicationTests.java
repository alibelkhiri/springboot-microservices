package com.abscript.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.abscript.productservice.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;



import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Container
    static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");
    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

	}
	@Test
	void shouldCreateProduct () throws Exception{
	ProductRequest productRequest=getProductReuest();
	String productReuestString =objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON)
		.content(productReuestString)).andExpect(status().isCreated())
		
		;
	}
	private ProductRequest getProductReuest() {
		return ProductRequest.builder()
		.name("Iphone 13")
		.description("i phonnne 3")
		.price(BigDecimal.valueOf(1220)).build()
		;
	}

}
