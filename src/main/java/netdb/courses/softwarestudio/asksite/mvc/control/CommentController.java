package netdb.courses.softwarestudio.asksite.mvc.control;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.model.domain.Comment;
import netdb.courses.softwarestudio.asksite.mvc.view.json.CommentJson;
import netdb.courses.softwarestudio.asksite.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class CommentController extends ResourceController<Comment> {
	private static final Log log = LogFactory.getLog(CommentController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		if (log.isDebugEnabled())
			log.debug("Invoking business logics");
		include(req, resp, "/model/business/persistene/comment-dao");

		// dispatch to view
		if (log.isDebugEnabled())
			log.debug("Dispatching to view");
		forward(req, resp, "/view/json/comments");
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
			CommentJson m = JsonService.deserialize(UTF8Coder.encode(body), CommentJson.class);
			
			// check contents
			if (m.getContent() == null || m.getTitle() == null )
				throw new RuntimeException("Wrong message format.");
			
			// save the message
			setModel(req, new Comment(m.getTitle(), m.getContent()));
			
		} catch (Exception e) {
			forward(req, resp, "/view/400-bad-request-view");
			if (log.isInfoEnabled())
				log.info("Bad request: " + e.getMessage());
			return;
		}

		// invoke business logics
		if (log.isDebugEnabled())
			log.debug("Invoking business logics");
		include(req, resp, "/model/business/persistene/comment-dao");

		// dispatch to view
		if (log.isDebugEnabled())
			log.debug("Dispatching to view");
		forward(req, resp, "/view/json/comments");
	}
	
}