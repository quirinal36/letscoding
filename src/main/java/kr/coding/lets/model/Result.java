package kr.coding.lets.model;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
	public static final String SUCCESS_MESSAGE = "성공";
    public static final String SERVER_ERROR_MESSAGE = "실패";

    private HttpStatus statusCode;
    private String message;
    private Object data;
    private int totalCount;

    public Result(){}

    public static Result successInstance(){
        return new Result().success();
    }
    public static Result failInstance(){
        return new Result().fail();
    }

    public Result success(){
        statusCode = HttpStatus.OK;
        message = SUCCESS_MESSAGE;
        return this;
    }

    public Result fail(){
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        message = SERVER_ERROR_MESSAGE;
        return this;
    }  
}
