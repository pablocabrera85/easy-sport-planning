package org.esp.core.model.trainning;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Register implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date date;
	
	private Execution expected;
	private Execution achieved;
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the expected
	 */
	public Execution getExpected() {
		return expected;
	}
	/**
	 * @param expected the expected to set
	 */
	public void setExpected(Execution expected) {
		this.expected = expected;
	}
	/**
	 * @return the achieved
	 */
	public Execution getAchieved() {
		return achieved;
	}
	/**
	 * @param achieved the achieved to set
	 */
	public void setAchieved(Execution achieved) {
		this.achieved = achieved;
	}
	
}
