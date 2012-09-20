import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Command Line Control Example
public class SampleUsage2 {

	public static void main(String[] args) throws IOException {
		EasyXBrowserTesting x = new EasyXBrowserTesting();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		String url = "http://kirnik0w0876.2ap.pl/xtest/";
		x.loadUrl(url);
		
		boolean exit = false;
		CMD cmd = CMD.n;
		
		
//		C:\Users\k\Desktop\Nowy folder\EasyXBrowserTesting>java -jar lib/EasyXBrowserTes
//		tingV0.0.1.jar
//		Started ChromeDriver
//		port=16435
//		version=22.0.1203.0b
//		log=C:\Users\k\Desktop\Nowy folder\EasyXBrowserTesting\chromedriver.log
//		2012-09-20 17:03:49 org.apache.http.impl.client.DefaultRequestDirector tryExecut
//		e
//		INFO: I/O exception (org.apache.http.NoHttpResponseException) caught when proces
//		sing request: The target server failed to respond
//		2012-09-20 17:03:49 org.apache.http.impl.client.DefaultRequestDirector tryExecut
//		e
//		INFO: Retrying request
//		Started InternetExplorerDriver server (32-bit)
//		2.25.3.0
//		Listening on port 5171
//		Exception in thread "main" java.lang.reflect.InvocationTargetException
//		        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//		        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
//		        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
//		        at java.lang.reflect.Method.invoke(Unknown Source)
//		        at org.eclipse.jdt.internal.jarinjarloader.JarRsr
		
		do {
			System.out.println("### MENU ###");
			System.out.println("u - load url, ch - check all elements, r - refresh website, q - quit");
			try{
				cmd = CMD.valueOf(br.readLine());
			}catch(IllegalArgumentException ex){
				//nothing
			}
			
			switch (cmd) {
			case q:
				System.out.println("closing browsers and quiting...");
				x.closeAllBrowsers();
				exit = true;
				break;
			case u:
				System.out.println("Enter url:");
				url = br.readLine();
				System.out.println("loading...");
				x.loadUrl(url);
				System.out.println("loaded");
				break;
			case r:
				System.out.println("refreshing...");
				x.loadUrl(url);
				System.out.println("refreshed...");
				break;
			case ch:
				System.out.println("Checking all elements and printing differences...");
				x.compareAllElements();
				System.out.println("### Completed ###");
			default:
			}

		} while (!exit);
	}

	public enum CMD {
		q, // quit
		u, // load url
		r, // refresh website
		ch, // check all elements
		n, // nothing

	}

}
