package com.abscript.productservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.abscript.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product,String>{

   
    
}
