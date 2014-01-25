package org.esp.webservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	
	private Object result;

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
}
