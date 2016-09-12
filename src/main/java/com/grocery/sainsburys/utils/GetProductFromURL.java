package com.grocery.sainsburys.utils;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.grocery.sainsburys.pojo.Product;

import java.math.BigDecimal;

/**
 * Created by RHussain
 */
public class GetProductFromURL {

    //This method is populating the Product POJO
    //by scraping the product page using the url we previously retrieved and usingn xpath to set the details
    public Product getProductWithDetails(String url){

        Product item = new Product();
        try {
            DocumentContent documentContent = new DocumentContent();
            HtmlPage page =  documentContent.getHtml(url);

            HtmlElement title = page.getFirstByXPath("//div[@class='productTitleDescriptionContainer']/h1");
            item.setTitle(title.asText());

            HtmlElement unitPrice = page.getFirstByXPath("//div[@class='pricing']/p[@class='pricePerUnit']");
            //creating substring of the unitprice by removing the initial pound sign
            //and the trailing /unit portion
            item.setUnitPrice(new BigDecimal(unitPrice.asText().substring(1,5)));

            //used xpath to get the product text class and the paragraph tag to get the
            //description of the item
            HtmlParagraph descriptionParagraph = page.getFirstByXPath("//div[@class='productText']/p");
            item.setDescription(descriptionParagraph.asText());

            //set the size of the page in kb for the product
            item.setSize(documentContent.getSizeOfPageInKb());

        } catch (Exception e) {
            //would usually add a log to a log file, but did not do for this test
            e.printStackTrace();
        }

        return item;
    }
}
