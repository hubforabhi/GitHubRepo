package com.abhi.department;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentControllerAdvice {//extends ResponseEntityExceptionHandler {
	
	@RequestMapping("/error123")
	public ResponseEntity<Void> error123() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
/*	@ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Void> unknownException(Exception ex, WebRequest req) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
*/}

/*@ControllerAdvice
public class DepartmentControllerAdvice extends ResponseEntityExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
			HttpRequestMethodNotSupportedException.class })
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}*/