package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;
import netdb.courses.softwarestudio.asksite.service.wiki.WikiService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

/**
 * Provides a way to access the {@link Definition} domain objects and hides the
 * implementation details specific to the underlying datastore.
 */
@SuppressWarnings("serial")
public class DefinitionDao extends ModelAwareServlet<Definition> {
	private static final Log log = LogFactory.getLog(DefinitionDao.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// parse request URL

		// Get raw (encoded) URI and replace servlet name to get query string,
		// using pattern (a.k.a. regular expression):
		// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		String regex = "^(" + req.getServletPath() + ")";
		String title = URLDecoder.decode(
				req.getRequestURI().replaceAll(regex, "").replace("/", ""),
				StandardCharsets.UTF_8.name());

		// setup model
		Definition def = new Definition(title, null);
		setModel(req, def);

		// retrieve description
		String content = WikiService.retrieve(title);
		if (content != null) {
			def.setDescription(DefinitionWikiParser.extractDefinition(content));
		} else{
			List<Definition> d = ObjectifyService.ofy().load().type(Definition.class).list();
		/*	
			Definition tar = new Definition("", "");
			for(int i = 0; i < d.size();++i ){
				if( d.get(i).getTitle().equals( title ) ){
					tar = d.get(i);
					break;
				}
			}
			setModel(req, tar);
		*/
			
			
			setModel(req, d);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled())
			log.debug("Creating a domain object");
		
		// save a new message into database
		Definition def = getModel(req);
		ObjectifyService.ofy().save().entity( def ).now();
	}
}
