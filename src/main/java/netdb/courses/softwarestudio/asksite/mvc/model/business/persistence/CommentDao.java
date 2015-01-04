package netdb.courses.softwarestudio.asksite.mvc.model.business.persistence;


import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
		
		String regex = "^(" + req.getServletPath() + ")";
		String title = URLDecoder.decode(
				req.getRequestURI().replaceAll(regex, "").replace("/", ""),
				StandardCharsets.UTF_8.name());
		log.info("URL_PARSER : " + title); 
		
		// load the message list from database
		List<Comment> ms = ObjectifyService.ofy().load().type(Comment.class).list();
		
		log.info( req.getAttribute("title") );
		
		List<Comment> ans = new ArrayList<Comment>();
		for(int i = 0; i < ms.size(); ++i){
			if( ms.get(i).getTitle().equals( title ) ){
				ans.add( ms.get( i ) );
			}
		}
		setModel(req, ans);
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