package cmc.cmc15th_hackathon.global.exception;

import cmc.cmc15th_hackathon.global.common.Result;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	private Result result;
	private String debug;

	public CustomException(Result result) {
		this.result = result;
		this.debug = result.getMessage();
	}
}
