/**
 * @author loc.mh
 */
package vn.credit.home.util.param;

import java.util.Date;

/**
 * @author loc.mh
 *
 */
public class Result {

	private int error;
	private String message;
	private boolean isBool;

	public boolean isBool() {
		return isBool;
	}

	public void setBool(boolean isBool) {
		this.isBool = isBool;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void writeLog(String Log) {
		Date date = new Date();
	}

	public static Result SetMessage(int erroeCode, String message) {
		Result result = new Result();
		result.setError(erroeCode);
		result.setMessage(message);
		return result;
	}
}
