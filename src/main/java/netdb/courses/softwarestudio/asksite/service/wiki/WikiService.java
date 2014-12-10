package netdb.courses.softwarestudio.asksite.service.wiki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A service that interacts with Wiki pages.
 */
public class WikiService {

	/**
	 * Retrieves the content of a Wiki page with a specific title.
	 * 
	 * @param title
	 *            title of the Wiki page
	 * @return null if no Wiki page is found
	 */
	public static String retrieve(String title) {
		String urlString = getWikiUrl(preprocessString(title));
		try {
			// get the content of the url
			return getContent(new URL(urlString));
		} catch (MalformedURLException e) {
			// do nothing
		}
		return null;
	}

	/**
	 * Convert the title to that acceptable by Wiki.
	 */
	private static String preprocessString(String title) {
		// omit leading and trailing whitespace
		StringBuilder sb = new StringBuilder(title.trim());
		int i = 0;
		boolean pw = true; // whitespace encountered in the previous iteration
		while (i < sb.length()) {
			if (pw) {
				if (!Character.isWhitespace(sb.charAt(i))) {
					// convert the first character of each token to upper case
					sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
					pw = false;
				} else {
					sb.deleteCharAt(i); // remove pending whitespace
					continue;
				}
			} else if (Character.isWhitespace(sb.charAt(i))) {
				// replace the white space character with '_'
				sb.setCharAt(i, '_');
				pw = true;
			}
			i++;
		}

		return sb.toString();
	}

	private static String getWikiUrl(String query) {
		return "http://en.wikipedia.org/wiki/" + query;
	}

	private static String getContent(URL url) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			// do nothing
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

}
