package com.webscraping.data;

import java.util.Date;
import java.util.List;

public record ProductHistory(
        String name, String url, String image, String source, Date createdAt, List<PriceHistory> priceHistory
        ) {
}
