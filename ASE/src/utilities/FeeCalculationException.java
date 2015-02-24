package utilities;

/**
 * Custom Exception for fee calculation method inside Journey class.
 * @author Wanchana
 *
 */
public class FeeCalculationException extends Exception{

	private static final long serialVersionUID = 1L;

	public FeeCalculationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeeCalculationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FeeCalculationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FeeCalculationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FeeCalculationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
