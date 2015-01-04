package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Comment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class CommentDao extends ModelAwareServlet<Comment> {
	private static final Log log = LogFactory.getLog(CommentDao.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled())
			log.debug("Getting/listing domain object(s)");
		
		// load the message list from database
		List<Comment> ms = ObjectifyService.ofy().load().type(Comment.class).list();
		setModel(req, ms);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled())
			log.debug("Creating a domain object");
		
		// save a new message into database
		Comment m = getModel(req);
		ObjectifyService.ofy().save().entity(m).now();
	}
}