package utilities;
/**
 * Simple custom exception with custom message. This exception will be throwed if the application
 * can not find matched destinations that were loaded into memory before processing journey.txt.
 * This exception will be used in Journey class.
 * @author Wanchana, Thanaphong
 *
 */
public class DestinationNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DestinationNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DestinationNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DestinationNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DestinationNotFoundException(String destination) {
		super("Destination Name:\""+destination+"\" is not existed in Destnation collection");
	}

	public DestinationNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
