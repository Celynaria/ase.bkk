package utilities;

public class RegistrationIDNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistrationIDNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationIDNotFoundException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public RegistrationIDNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public RegistrationIDNotFoundException(String ID) {
		super("Taxi ID:"+ID+" is not existed in Taxi collection");
	}

	public RegistrationIDNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
