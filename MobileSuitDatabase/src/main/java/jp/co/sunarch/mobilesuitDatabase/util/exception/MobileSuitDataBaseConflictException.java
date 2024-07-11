package jp.co.sunarch.mobilesuitDatabase.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MobileSuitDataBaseConflictException extends RuntimeException {

	public MobileSuitDataBaseConflictException () {
		super();
	}

	public MobileSuitDataBaseConflictException(String message) {
		super(message);
	}

	public MobileSuitDataBaseConflictException(String message, Throwable cause) {
		super(message, cause);
	}

}
