import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.grocery.sainsburys.pojo.Product;
import com.grocery.sainsburys.utils.DocumentContent;
import com.grocery.sainsburys.utils.GetProductFromURL;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by RHussain
 */
public class GetProductFromURLTest {

    private String url="http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html";
    @Test
    public void getProductDetailsTest(){
        GetProductFromURL getProductFromURL = new GetProductFromURL();
        Product product = getProductFromURL.getProductWithDetails(url);
        assertNotNull(product);
    }

    @Test
    public void getProductTitleTest(){
        DocumentContent documentContent = new DocumentContent();
        HtmlPage page =  documentContent.getHtml(url);

        HtmlElement title = page.getFirstByXPath("//div[@class='productTitleDescriptionContainer']/h1");
        assertTrue(title.getNodeName().equals("h1"));
    }


    @Test
    public void getUnitPriceTest(){
        DocumentContent documentContent = new DocumentContent();
        HtmlPage page =  documentContent.getHtml(url);

        HtmlElement unitPrice = page.getFirstByXPath("//div[@class='pricing']/p[@class='pricePerUnit']");
        assertTrue(unitPrice.getNodeName().equals("p"));
    }

    @Test
    public void getDescriptionTest(){
        DocumentContent documentContent = new DocumentContent();
        HtmlPage page =  documentContent.getHtml(url);
        HtmlParagraph descriptionParagraph = page.getFirstByXPath("//div[@class='productText']/p");

        assertTrue(descriptionParagraph.getNodeName().equals("p"));
    }

}
