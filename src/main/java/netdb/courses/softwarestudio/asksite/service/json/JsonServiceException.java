package netdb.courses.softwarestudio.asksite.service.json;

import netdb.courses.softwarestudio.asksite.service.ServiceException;

public class JsonServiceException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public JsonServiceException() {
		super();
	}

	public JsonServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonServiceException(String message) {
		super(message);
	}

	public JsonServiceException(Throwable cause) {
		super(cause);
	}

}
