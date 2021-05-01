package exceptions;

public class AlreadyFollowed extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AlreadyFollowed() {
		super();
	}

	/**
	 * This exception is triggered when the user already follows this team/player
	 * @param m
	 */
	public AlreadyFollowed(String m) {
		super(m);
	}
	
}
