package netdb.courses.softwarestudio.asksite.mvc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;

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

}
