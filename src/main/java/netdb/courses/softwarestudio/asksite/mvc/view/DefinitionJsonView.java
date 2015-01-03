package netdb.courses.softwarestudio.asksite.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.asksite.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;
import netdb.courses.softwarestudio.asksite.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class DefinitionJsonView extends ModelAwareServlet<Definition> {
	private static final Log log = LogFactory.getLog(DefinitionJsonView.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Responsing 200 OK");
		}
		Object d = getModel(req);
		resp.setContentType("application/json");
		// make sure no intermediate node caches the result
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(d));
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
