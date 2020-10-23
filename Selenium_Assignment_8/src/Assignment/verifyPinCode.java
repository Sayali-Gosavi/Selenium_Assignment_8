package Assignment;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class verifyPinCode {
	
	WebDriver d;
	String url="https://chennaiiq.com/chennai/pincode-by-name.php";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\chromedriver.exe");
		d=new ChromeDriver();
		d.get(url);
		d.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		d.quit();
	}

	@Test
	public void test() {
		WebElement table=d.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]/table[1]"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		for(int i=3;i<rows.size();i++){
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String pinCode1 = cols.get(2).getText(); 
			for(int j=i;j<rows.size();j++) {
				List<WebElement> cols2=rows.get(j).findElements(By.tagName("td"));
				String pinCode2 = cols.get(2).getText();
				Assert.assertFalse(pinCode1==pinCode2);  //accertion logic
			}
			if(i==12) {
				break;   //I am checking only first 10 pincodes here
			}
		}
	}

}
