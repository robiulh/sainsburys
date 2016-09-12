import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.grocery.sainsburys.utils.DocumentContent;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;

/**
 * Created by RHussain.
 */
public class DocumentContentTest {

    @Test
    public void whenMakingCallToURL(){
        DocumentContent documentContent = new DocumentContent();
        HtmlPage page =  documentContent.getHtml("http://www.sainsburys.com");
        assertNotNull("The page is not null",page);
    }


}
