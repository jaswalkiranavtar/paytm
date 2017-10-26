package com.paytm.pojo;

public class Result<T> {

    private ResponseCode healthCheckStatusCode = ResponseCode.SUCCESS;
    private String healthCheckStatusMessage;
    private T data;
    
    public Result(ResponseCode healthCheckStatusCode, String healthCheckStatusMessage, T data) {
		super();
		this.healthCheckStatusCode = healthCheckStatusCode;
		this.healthCheckStatusMessage = healthCheckStatusMessage;
		this.data = data;
	}
    
	public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public ResponseCode getHealthCheckStatusCode() {
        return healthCheckStatusCode;
    }
    public void setHealthCheckStatusCode(ResponseCode healthCheckStatusCode) {
        this.healthCheckStatusCode = healthCheckStatusCode;
    }
    public String getHealthCheckStatusMessage() {
        return healthCheckStatusMessage;
    }
    public void setHealthCheckStatusMessage(String healthCheckStatusMessage) {
        this.healthCheckStatusMessage = healthCheckStatusMessage;
    }
}

