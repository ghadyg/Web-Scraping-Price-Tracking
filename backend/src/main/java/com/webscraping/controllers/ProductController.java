package com.webscraping.controllers;

import com.webscraping.data.Product;
import com.webscraping.data.ProductHistory;
import com.webscraping.data.TrackProductRequest;
import com.webscraping.data.TrackedProductSource;
import com.webscraping.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/unique_search_texts")
    private List<String> get_unique_search_texts()
    {
        return productService.get_unique_search_texts();
    }

    @GetMapping("/results")
    private List<ProductHistory> get_product_results(@RequestParam(required = false,defaultValue = "All") String searchText)
    {
        return productService.get_product_results(searchText);
    }

    @GetMapping("/all-results")
    private List<Product> get_results()
    {
        return productService.get_results();
    }

    @PostMapping("/add-tracked-product")
    private void add_tracked_product(@RequestBody TrackProductRequest trackProduct)
    {
        productService.add_tracked_product(trackProduct);
    }

    @PutMapping("/tracked-product/{id}")
    private void toggle_tracked_product(@PathVariable("id") Integer id)
    {
        productService.toggle_tracked_product(id);
    }

    @GetMapping("/tracked-products")
    private List<Product> get_tracked_products()
    {
        return productService.get_tracked_products();
    }


}
