package com.webscraping.schedule;

import com.webscraping.data.Product;
import com.webscraping.data.TrackedProductSource;
import com.webscraping.repository.ProductRepository;
import com.webscraping.scraper.Scraping;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class RepeatedSchedule {

    private final ProductRepository productRepository;

    public RepeatedSchedule(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(fixedRate = 1,timeUnit = TimeUnit.HOURS,initialDelay = 1)
    @Async
    public void repeatedScraping()
    {
        List<TrackedProductSource> distinctSearchText = productRepository.findDistinctTrackProduct();
        for (TrackedProductSource productSource : distinctSearchText)
        {
            List<Product> productList = Scraping.search(productSource.source(), productSource.searchText());
            productRepository.saveAll(productList);
        }

    }
}
