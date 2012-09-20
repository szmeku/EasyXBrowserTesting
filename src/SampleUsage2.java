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
