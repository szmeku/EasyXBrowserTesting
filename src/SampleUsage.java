public class SampleUsage {

	public static void main(String[] args) {		
		EasyXBrowserTesting x = new EasyXBrowserTesting();

		// url to sample test page
		x.loadUrl("http://kirnik0w0876.2ap.pl/xtest/");
		
		System.out.println("Elements that don't match:");
		x.compareAllElements();
		
		x.closeAllBrowsers();
	}

}
