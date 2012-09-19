import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.*;
import org.openqa.selenium.chrome.*;

public class EasyXBrowserTesting {

	private List<Browser> browsers = new ArrayList<Browser>();

	public EasyXBrowserTesting() {
		// Open Chrome
		this.browsers.add(new Browser("Chrome", new ChromeDriver()));
		// Open Internet Explorer
		this.browsers.add(new Browser("Internet Explorer 9",
				new InternetExplorerDriver()));

		// if you want to use these browsers below you have to install them,
		// add proper selenium dependencies and uncomment lines below

		// this.browsers.add(new Browser(
		// new OperaDriver()));
		// this.browsers.add(new Browser(
		// new FirefoxDriver()));
		// this.browsers.add(new Browser(
		// new SafariDriver()));
	}

	public void loadUrl(String url) {
		for (Browser browser : this.browsers)
			browser.loadUrl(url);
	}

	public void closeAllBrowsers() {
		for (Browser browser : this.browsers)
			browser.close();
	}

	public void compareAllElements() {
		List<Browser> browsers = this.browsers;

		int numberOfElems = 100000;
		for (Browser browser : browsers) {
			if (browser.elems.size() < numberOfElems)
				numberOfElems = browser.elems.size();
		}

		// i assume that elements are in the same order in all browsers
		// and there are the same number of them
		for (int i = 0; i < numberOfElems; i++) {

			// first browser is a pattern
			for (int j = 1; j < browsers.size(); j++) {
				WebElement patternElem = browsers.get(0).elems.get(i);
				WebElement elemToCompare = browsers.get(j).elems.get(i);

				if (!this.compareTwoElements(patternElem, elemToCompare)) {
					this.printComparisionResult(browsers.get(0).name, browsers.get(j).name,
							patternElem, elemToCompare);
				}
			}
		}
	}

	private void printComparisionResult(String firstBrowser,
			String secondBrowser, WebElement firstElem, WebElement secondElem) {

		String elem_class = "", elem_id = "";

		if (!firstElem.getAttribute("class").isEmpty())
			elem_class = " class='" + firstElem.getAttribute("class") + "'";
		if (!firstElem.getAttribute("id").isEmpty())
			elem_id = " id='" + firstElem.getAttribute("id") + "'";

		String output = "<" + firstElem.getTagName() + elem_id + elem_class + ">\n"
				+ firstBrowser + "\n"
				+ "size:" + firstElem.getSize()
				+ " location: " + firstElem.getLocation() + "\n"
				+ secondBrowser + "\n"
				+ "size:" + secondElem.getSize()
				+ " location: " + secondElem.getLocation()+"\n\n";

		System.out.println(output);
	}

	// return false if elements have different sizes or locations
	private boolean compareTwoElements(WebElement firstElement,
			WebElement secondElement) {
		if (!firstElement.getSize().equals(secondElement.getSize())
				|| !firstElement.getLocation().equals(
						secondElement.getLocation())) {
			return false;
		}
		return true;
	}
}

class Browser {
	public static String xPathToElements = "//a|//div|//span|//ul|//input|//textarea";

	public String name = "";

	// selenium browser driver
	public WebDriver driver;

	// dom elements of loaded page
	public List<WebElement> elems;

	public Browser(String name, WebDriver driver) {
		this.name = name;
		this.driver = driver;
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(0, 0));

	}

	public void loadUrl(String url) {
		this.driver.get(url);
		this.elems = driver.findElements(By.xpath(Browser.xPathToElements));
	}

	public void close() {
		this.driver.quit();
	}
}
