package br.com.trevezani.zipcode.infraestructure.delivery.responses;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CensusResponse<T> implements Serializable {
	private static final long serialVersionUID = -2403538270734520765L;

	private LocalDateTime timestamp;
	private String status;
	private String message;
	private String correlationId;

	private T data;

	public CensusResponse() {
		super();
	}

	public CensusResponse(String status, T data) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.data = data;
	}

	public CensusResponse(String status, String correlationId, T data) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.correlationId = correlationId;
		this.data = data;
	}

	public CensusResponse(String status, String message) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
	}

	public CensusResponse(String status, String message, String correlationId) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.correlationId = correlationId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
