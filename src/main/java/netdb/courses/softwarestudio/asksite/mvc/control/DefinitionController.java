package netdb.courses.softwarestudio.asksite.mvc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;
import netdb.courses.softwarestudio.asksite.mvc.view.json.DefinitionJson;
import netdb.courses.softwarestudio.asksite.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class DefinitionController extends ResourceController<Definition> {
	private static final Log log = LogFactory
			.getLog(DefinitionController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		if (log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/wikiretrieve/definition-dao");

		// dispatch to view
		if (log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		Definition def = getModel(req);
		if (def.getDescription() == null) {
			forward(req, resp, "/view/404-not-found-view");
			return;
		}
		if (!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/definition-json-view");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// setup model in request
		try {
			if (log.isDebugEnabled())
				log.debug("Setting up model in request");

			// check the header
			if (!req.getHeader("Content-Type").contains("application/json"))
				throw new RuntimeException("Unacceptable type.");


			// deserialize the content of the message
			String body = getRequestBody(req);
			
			DefinitionJson def = JsonService.deserialize(UTF8Coder.encode(body), DefinitionJson.class);
			
			String title = def.getTitle();
			String description = def.getDescription();
			
			// check contents
			log.info("Body is " + body );
			log.info("Title is "+ title );
			log.info("Description is " + description);
			if ( title == null || description == null )
				throw new RuntimeException("Wrong message format.");
			log.info("Mom !! I'm HERE !!!");
			// save the message
			setModel(req, new Definition(title, description));
			
			log.info("Dad!! I'm Here !!!");
			
		} catch (Exception e) {
			forward(req, resp, "/view/400-bad-request-view");
			if (log.isInfoEnabled())
				log.info("Bad request: " + e.getMessage());
			return;
		}

		// invoke business logics
		if (log.isDebugEnabled())
			log.debug("Invoking business logics");
		include(req, resp, "/model/business/wikiretrieve/definition-dao");

		// dispatch to view
		if (log.isDebugEnabled())
			log.debug("Dispatching to view");
		forward(req, resp, "/view/definition-json-view");
	}
}
