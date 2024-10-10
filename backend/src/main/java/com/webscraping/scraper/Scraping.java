package com.webscraping.scraper;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.webscraping.data.Product;
import com.webscraping.data.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scraping {

    private static Map<String, URL> availableURL = Map.of(
            "AMAZON",new URL("https://www.amazon.com/",
                    "input[name=\"field-keywords\"]",
                    "input[value=\"Go\"]",
                    "div.s-card-container")
            );

    public static List<Product> search(String url,String searchText)
    {
        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        Browser browser = chromium.launch();
        Page page = browser.newPage();
        System.out.println("Connected");
        if(!availableURL.containsKey(url))
            throw new RuntimeException("Invalid URL");
        URL site = availableURL.get(url);
        page.navigate(site.url(), new Page.NavigateOptions().setTimeout(12000));
        System.out.println("Loaded initial page.");

        System.out.println("Filling input field");
        Locator searchField = page.locator(site.getSearchFieldQuery());
        searchField.fill(searchText);

        Locator searchBtn = page.locator(site.getSearchButtonQuery());
        searchBtn.click();

        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        List<ElementHandle> productDivs = page.querySelectorAll(site.getProductSelector());

        System.out.println("Found " + productDivs.size() + " products on the page.");
        List<Product> validProducts = new ArrayList<>();


        for (var productDiv : productDivs) {
            Product product = Amazon.getProductSync(productDiv);
            if (product.getPrice() == null || product.getUrl() == null) {
                continue;
            }

            if (product.getName() == null || !product.getName().replace(" ","").toLowerCase().contains(searchText.replace(" ","").toLowerCase())) {
                continue;
            }

            product.setSearchText(searchText);
            product.setSource(url);
            validProducts.add(product);

        }

        // Print the valid products
        for (Product product : validProducts) {
            System.out.println("Valid Product: " + product.getName() + " - " + product.getPrice() + " - " + product.getUrl());
        }
        System.out.println("Finished");


        browser.close();
        playwright.close();

        return validProducts;
    }
}
