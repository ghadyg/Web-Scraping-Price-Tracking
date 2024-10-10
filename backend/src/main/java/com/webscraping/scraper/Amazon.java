package com.webscraping.scraper;

import com.microsoft.playwright.ElementHandle;
import com.webscraping.data.Product;

public class Amazon {
    public static Product getProductSync(ElementHandle productDiv) {
        Product product = new Product();

        try {
            // Use waitFor to ensure elements are available
            ElementHandle image_element = productDiv.querySelector("img.s-image");
            ElementHandle name_element = productDiv.querySelector("h2 a span");
            ElementHandle price_element = productDiv.querySelector("span.a-offscreen");
            ElementHandle url_element = productDiv.querySelector("a.a-link-normal.s-no-hover.s-underline-text.s-underline-link-text.s-link-style.a-text-normal");

            // Get attributes synchronously
            if (image_element != null) {
                String src = image_element.getAttribute("src");
                product.setImage(src != null ? src : "");
            }

            if (name_element != null) {
                String text = name_element.innerText();
                product.setName(text != null ? text : "");
            }

            if (price_element != null) {
                String priceText = price_element.innerText();
                if (priceText != null && !priceText.isEmpty()) {
                    String price = priceText.replace("$", "").replace(",", "").trim();
                    product.setPrice(Double.parseDouble(price));
                }
            }

            if (url_element != null) {
                String href = url_element.getAttribute("href");
                if (href != null) {
                    String[] parts = href.split("/");
                    if (parts.length >= 4) {
                        String url = String.join("/", parts[0], parts[1], parts[2], parts[3]);
                        product.setUrl("https://www.amazon.com"+url);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}
