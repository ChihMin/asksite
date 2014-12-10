package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;

/**
 * A Wiki page parser for definitions. 
 */
public class DefinitionWikiParser {
	
	/**
	 * Extracts definitions from content of a Wiki page.
	 * 
	 * @param content content of a Wiki page
	 * @return definition a sentence
	 */
	public static String extractDefinition(String content) {
		
		return extractDefinitionSentance(processDefinitionParagragh(
						extractDefinitionParagragh(content)));
	}

	private static String extractDefinitionParagragh(String content) {
		int start = 0, end, disam;

		if ((start = content.indexOf("<div id=\"bodyContent\">", start)) == -1)
			return null;
		
		// Modified to match the latest Wikipedia webpage
		if ((start = content.indexOf(
				"<div id=\"mw-content-text\" lang=\"en\" dir=\"ltr\" class=\"mw-content-ltr\">",
				start)) == -1)
			return null;
		
		// Find the beginning of the definition paragraph
		// Usually the definition sentence begins with the domain term with bold font
		while(true){
			if ((start = content.indexOf("<p>", start+1)) == -1)
				return null;
			if(content.indexOf("<b>", start) - start < 120){
				break;
			}
		}

		start += 3;
		
		// Find the end of the paragraph
		// For the disambiguation pages, the first paragraph would be too short,
		// thus the entire Disambiguation page content is fetched
		if((disam = content.indexOf("title=\"Help:Disambiguation\"")) > -1){
			end = content.substring(start, disam).lastIndexOf("</ul>") + start;
		}
		else{
			end = content.indexOf("</p>", start);
		}
					
		return content.substring(start, end);
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
	
	private static String extractDefinitionSentance(String p) {
		int end = 0;
		if (p == null)
			return null;
		
		while(true){
			// Disambiguation pages usually do not have periods
			if((end = p.indexOf(". ", end+1)) == -1){
				return p;
			}
			// Find the period of the definition sentence
			// If the index of the period is less than 20, it is more likely to be an abbreviation. e.g. "Google Inc." 
			if(end > 50){
				return p.substring(0, end+1);
			}
		}
	}
}
