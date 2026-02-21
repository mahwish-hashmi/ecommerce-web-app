package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        return product != null
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product saved = service.addProduct(product, imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save product image: " + e.getMessage());
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        if (product == null || product.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        try {
            service.updateProduct(id, product, imageFile);
            return ResponseEntity.ok("Product updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update product image: " + e.getMessage());
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        service.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchProducts(keyword));
    }
}