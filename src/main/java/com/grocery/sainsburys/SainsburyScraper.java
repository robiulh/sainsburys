package com.grocery.sainsburys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.html.*;
import com.grocery.sainsburys.pojo.JsonOutput;
import com.grocery.sainsburys.pojo.Product;
import com.grocery.sainsburys.utils.DocumentContent;
import com.grocery.sainsburys.utils.GetProductFromURL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RHussain
 */
public class SainsburyScraper {

    public static void main(String args[]){

        try {
            
            if(args.length == 0){
                System.out.println("[ERROR]: No URL specified");
                System.exit(-1);
            }
            
            String searchUrl = args[0];
            DocumentContent documentContent = new DocumentContent();
            JsonOutput listOfItems = new JsonOutput();
            List<Product> products = new ArrayList<Product>();
            GetProductFromURL getProductFromURL = new GetProductFromURL();


            HtmlPage page =  documentContent.getHtml(searchUrl);

            //getting the rows of product using xpath and the div class=product
            List<HtmlDivision> items =  (List<HtmlDivision>)  page.getByXPath("//div[contains(concat(\" \", normalize-space(@class), \" \"), \" product \")]");

            if(items.isEmpty()){
                System.out.println("No items found !");
            }else{
                for(HtmlDivision item : items){

                   //get the url using xpath
                    HtmlAnchor itemAnchor =  item.getFirstByXPath(".//div[@class='productInfo']/h3/a");
                    String itemUrl = itemAnchor.getHrefAttribute() ;

                    //using the url get the product page and populate a Product POJO
                    Product product = getProductFromURL.getProductWithDetails(itemUrl);
                    //now populating the collection
                    //which will later be used in the output json
                    products.add(product);

                }

                listOfItems.setResults(products);
                //looping through the items we created above and adding the unit prices
                //to give a total for the JSON
                Iterator<Product> productIterator = products.iterator();
                BigDecimal total = new BigDecimal(0.00);
                while (productIterator.hasNext()) {
                  total =   total.add(productIterator.next().getUnitPrice());
                }
                listOfItems.setTotalOfAllItems(total);
                //using jackson to create json string out of the results
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(listOfItems) ;
                System.out.println(jsonString);
            }

        }catch(Exception e){
            //would usually add a log to a log file, but did not do for this test
            e.printStackTrace();
        }
    }

    }

