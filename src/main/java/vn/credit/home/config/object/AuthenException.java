/**
 * @author loc.mh
 */
package vn.credit.home.config.object;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author loc.mh
 *
 */
public class AuthenException {
	private Exception exception;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return this.toString();
		}
	}

	public class Exception {
		private String name;
		private String message;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			ObjectMapper om = new ObjectMapper();
			try {
				return om.writeValueAsString(this);
			} catch (JsonProcessingException e) {
				return this.toString();
			}
		}
	}
}
