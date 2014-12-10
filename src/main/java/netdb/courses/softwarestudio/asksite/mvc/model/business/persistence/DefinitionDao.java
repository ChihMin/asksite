package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;
import netdb.courses.softwarestudio.asksite.service.wiki.WikiService;

/**
 * Provides a way to access the {@link Definition} domain objects and hides the
 * implementation details specific to the underlying datastore.
 */
@SuppressWarnings("serial")
public class DefinitionDao extends ModelAwareServlet<Definition> {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// parse request URL
		String title = URLDecoder.decode(req.getPathInfo().substring(1),
				"UTF-8");

		// setup model
		Definition def = new Definition(title, null);
		setModel(req, def);

		// retrieve description
		String content = WikiService.retrieve(title);
		if (content != null) {
			def.setDescription(DefinitionWikiParser.extractDefinition(content));
		}
	}
}
