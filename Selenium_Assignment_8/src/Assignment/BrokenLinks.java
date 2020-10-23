package Assignment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BrokenLinks {

	WebDriver d;
	String url = "http://www.zlti.com";
	String newURL = "";
	HttpURLConnection httpURLConnect = null;
	int responseCode = 200;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\chromedriver.exe");
		d = new ChromeDriver();
		d.get(url);
		d.manage().window().maximize();
	}

	@Test
	public void test() {
		List<WebElement> Links = d.findElements(By.tagName("a"));

		Iterator<WebElement> LI = Links.iterator();

		while (LI.hasNext()) {

			newURL = LI.next().getAttribute("href");

			System.out.println(newURL);

			if (newURL == null || newURL.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!newURL.startsWith(url)) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				httpURLConnect = (HttpURLConnection) (new URL(newURL).openConnection());

				httpURLConnect.setRequestMethod("HEAD");

				httpURLConnect.connect();

				responseCode = httpURLConnect.getResponseCode();

				if (responseCode >= 400) {
					System.out.println(newURL + " is a broken link");
				} else {
					System.out.println(newURL + " is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		d.quit();
	}
}