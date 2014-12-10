package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * A Wiki page parser for definitions.
 */
public class DefinitionWikiParser {

	/**
	 * Extracts definitions from content of a Wiki page.
	 * 
	 * @param content
	 *            content of a Wiki page
	 * @return definition a sentence
	 */
	public static String extractDefinition(String content) {

		return extractDefinitionSentence(processDefinitionParagragh(extractDefinitionParagragh(content)));
	}

	private static String extractDefinitionParagragh(String content) {

		Document doc = Jsoup.parse(content);
		String firstParagraph = doc.select("#mw-content-text p").first().text();

		return firstParagraph;
	}

	private static String processDefinitionParagragh(String p) {
		if (p == null)
			return null;
		StringBuilder sb = new StringBuilder(p);
		int start, end = 0;

		while ((start = sb.indexOf("<")) >= 0) {
			end = sb.indexOf(">", start) + 1;
			sb.delete(start, end);
		}
		while ((start = sb.indexOf("[")) >= 0) {
			end = sb.indexOf("]", start) + 1;
			sb.delete(start, end);
		}

		return sb.toString();
	}

	private static String extractDefinitionSentence(String p) {
		int end = 0;
		if (p == null)
			return null;

		while (true) {
			// Disambiguation pages usually do not have periods
			if ((end = p.indexOf(". ", end + 1)) == -1) {
				return p;
			}
			// Find the period of the definition sentence
			// If the index of the period is less than 20, it is more likely to
			// be an abbreviation. e.g. "Google Inc."
			if (end > 50) {
				return p.substring(0, end + 1);
			}
		}
	}
}
