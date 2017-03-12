package org.guide.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rgdavis on 3/11/17.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {
}
