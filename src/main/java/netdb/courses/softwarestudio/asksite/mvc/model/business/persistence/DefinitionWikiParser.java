package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

		return extractDefinitionSentence((extractDefinitionParagragh(content)));
	}

	private static String extractDefinitionParagragh(String content) {

		/*
		 * 
		 * We use jsoup to parse HTML documents, which enables us to model the
		 * HTML content as a Document.
		 * 
		 * Then we'll be able to retrieve the contents using CSS-style selector
		 * methods.
		 * 
		 * For more details, please refer to: jsoup: http://jsoup.org/ CSS
		 * Selectors: http://www.w3.org/TR/css3-selectors/
		 */

		Document doc = Jsoup.parse(content);
		String paragraph = null;

		if (doc.select("a[title=Help:Disambiguation]").size() == 0) {

			// Avoid `coordinate` paragraph.
			if (doc.select("span[id=coordinates]").size() == 0) {
				paragraph = doc.select("#mw-content-text > p:not(span)")
						.first().text();
			} else {
				paragraph = doc.select("#mw-content-text > p:not(span)").get(1)
						.text();
			}

		} else {

			/*
			 * For Disambiguation pages, parse all of the contents.
			 */

			StringBuilder builder = new StringBuilder();

			builder.append(doc.select("#mw-content-text p").first().text());
			builder.append('\n');

			Elements items = doc.select("#mw-content-text > ul > li");

			for (Element item : items) {
				builder.append('\t');
				builder.append(item.text());
				builder.append('\n');
			}

			paragraph = builder.toString();

		}

		return paragraph;
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
			// If the index of the period is less than 30, it is more likely to
			// be an abbreviation. e.g. "Apple Inc."
			if (end > 30) {
				return p.substring(0, end + 1);
			}
		}
	}
}
