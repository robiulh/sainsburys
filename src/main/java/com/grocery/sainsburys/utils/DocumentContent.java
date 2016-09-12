package com.grocery.sainsburys.utils;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


import java.io.IOException;

/**
 * Created by RHussain
 *  A wrapper to get an HTML page to scrape the details off
 */
public class DocumentContent {
    private int sizeOfPage;

    //returning a string respresentaion of the size of the page
    public String getSizeOfPageInKb() {
        return (this.sizeOfPage)/1000 + "kb";
    }


    public HtmlPage getHtml(String url){
        //creating a headless browser
        WebClient client = new WebClient();

        //disabling Javascript since it's not required
        // and disabling Javascript makes the page load faster
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage currentPage=null;

        try {
            currentPage = client.getPage(url);
            //htmlunit does not load associated assets to save on download time.
            this.sizeOfPage = currentPage.getWebResponse().getContentAsString().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return currentPage;
    }
}
