package com.webscraping.services;

import com.webscraping.data.*;
import com.webscraping.repository.ProductRepository;
import com.webscraping.scraper.Scraping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> get_unique_search_texts() {
        return productRepository.findDistinctSearchText();
    }

    public List<ProductHistory> get_product_results(String searchText) {
        List<Product> allProducts;
        if(searchText.equals("All"))
            allProducts = productRepository.findAll();
        else
            allProducts = productRepository.findBySearchTextLike(searchText);

        HashMap<String,ProductHistory> productDict = new HashMap<>();
        for(Product product : allProducts)
        {
            String url = product.getUrl();
            if(!productDict.containsKey(url))
                productDict.put(url,new ProductHistory(product.getName(),product.getUrl(),product.getImage(),product.getSource(), product.getCreatedAt(),
                        new ArrayList<>(List.of(new PriceHistory(product.getPrice(),product.getCreatedAt())))));
            else {
                productDict.get(url).priceHistory().add(new PriceHistory(product.getPrice(),product.getCreatedAt()));
            }
        }

        return productDict.values().stream().toList();
    }

    public List<Product> get_results() {
        return productRepository.findAll();
    }

    public void add_tracked_product(TrackProductRequest trackProduct) {

        Thread scrapingThread = new Thread(() -> {
            List<Product> productList = Scraping.search(trackProduct.url(), trackProduct.searchText());
            productRepository.saveAll(productList);
        });

        scrapingThread.start();

    }

    public void toggle_tracked_product(Integer id) {
        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty())
            throw new RuntimeException("Item not found");
        Product product = optProduct.get();
        product.setTracked(!product.getTracked());
        productRepository.save(product);
    }

    public List<Product> get_tracked_products() {
        return productRepository.findByTrackedTrue();
    }
}
