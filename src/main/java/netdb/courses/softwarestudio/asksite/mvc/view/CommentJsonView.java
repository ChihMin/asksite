package netdb.courses.softwarestudio.asksite.mvc.view;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Comment;
import netdb.courses.softwarestudio.asksite.service.json.JsonService;
import netdb.courses.softwarestudio.asksite.mvc.view.json.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("serial")
public class CommentJsonView extends ModelAwareServlet<Comment> {
	private static final Log log = LogFactory.getLog(CommentJsonView.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		if(log.isDebugEnabled())
			log.debug("Responsing 200 OK");
		
		// serialize objects
		@SuppressWarnings("unchecked")
		List<Comment> messages = (List<Comment>) getModel(req);
		List<CommentJson> messageJsons = new LinkedList<CommentJson>();
		for (Comment message : messages)
			messageJsons.add(new CommentJson(message));
		
		// print the JSON string on the body of the HTTP response
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache"); // make sure no intermediate node caches the result
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(messageJsons));
		
		// 200 OK
		resp.setStatus(201);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(log.isDebugEnabled())
			log.debug("Responsing 201 Created");
		
		// 201 Created
		resp.setStatus(201);
	}
}
