package netdb.courses.softwarestudio.asksite.service.objectify;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.*;

/**
 * Objectify needs every persistable object to be registered. This class
 * registers all domain objects at the time when the {@link ServletContext} is
 * initialized.
 */
public final class ObjectifyInitializer implements ServletContextListener {
	private static final Log log = LogFactory
			.getLog(ObjectifyInitializer.class);

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		if (log.isInfoEnabled())
			log.info("Register entity classes");
		log.info("Mom !!! I'm HERE !");
		ObjectifyService.register(Definition.class);
		ObjectifyService.register(Comment.class);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// do nothing
	}
}