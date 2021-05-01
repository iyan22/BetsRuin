package exceptions;
/**This exception is triggered when the referralCode doesn't exist
 *@param s String of the exception
 */
public class NoReferralCodeFound extends Exception {
	private static final long serialVersionUID = 1L;

	public NoReferralCodeFound() {
		super();
	}
	public NoReferralCodeFound(String msg) {
		super(msg);
	}



}