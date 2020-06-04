package br.com.trevezani.zipcode.infraestructure.shared.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LoggerExtended {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final String packageAccept = "br.com.trevezani";

	public enum LoggerType {
		text, json
	}

	private LoggerType type = null;
	
	public LoggerExtended() {
		type = LoggerType.text;
	}
	
	public LoggerExtended(final LoggerType type) {
		this.type = type;
	}
	
	public void debug(String msg) {
		logger.debug(format(msg, null));
	}
	
	public void info(String msg) {
		logger.info(format(msg, null));
	}

    public void info(String format, Object... arguments) {
    	logger.info(format(format, null), arguments);
    }	
	
	public void error(String msg) {
		logger.error(format(msg, null));
	}
	
	public void error(String msg, Exception e) {
		logger.error(format(msg, e));
	}

	private String format(String message, Exception exception) {
		if (type.equals(LoggerType.json)) {
			return logToJSon(message, exception);
		} else {
			return logToString(message, exception);
		}
	}
	
	private String logToString(String message, Exception exception) {
		StringBuilder info = new StringBuilder();
		info.append(message);
		
		if (exception != null) {
			List<String> stack = new ArrayList<>();
			
		    for (StackTraceElement ste : exception.getStackTrace()) {
		    	if (!Objects.isNull(ste) && ste.getClassName().startsWith(packageAccept)) {
		    		stack.add(String.format("%s.%s (%d)", ste.getClassName(), ste.getMethodName(), ste.getLineNumber()));
		    	}
		    }
		    
		    info.append(" ").append(stack.toString());
		}

		return info.toString();		
	}
	
	private String logToJSon(String message, Exception exception) {
		JsonObject body = new JsonObject();
		
		body.addProperty("message", message);
		
		if (exception != null) {
			JsonArray stack = new JsonArray();
		    
		    for (StackTraceElement ste : exception.getStackTrace()) {
		    	if (!Objects.isNull(ste) && ste.getClassName().startsWith(packageAccept)) {
		    		stack.add(String.format("%s.%s (%d)", ste.getClassName(), ste.getMethodName(), ste.getLineNumber()));
		    	}
		    }
		    
		    body.add("stack", stack);
		}

		return new Gson().toJson(body);		
	}
}
