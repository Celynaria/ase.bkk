package utilities;
/**
 * A simple custom exception to handle a registration ID format.
 * This Exception will be throwed when the registration ID is incorrect.
 * This Exception will be used inside Journey class and Taxi class.
 * @author Wanchana, Thanaphong
 *
 */
public class InvalidRegistrationFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRegistrationFormatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidRegistrationFormatException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public InvalidRegistrationFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidRegistrationFormatException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidRegistrationFormatException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
