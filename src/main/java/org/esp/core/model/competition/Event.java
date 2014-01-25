package org.esp.core.model.competition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.esp.core.model.User;

@XmlRootElement
public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	private Date endDate;
	private String description;
	private String name;
	private List<User> participants;
	private List<EventResult> result;
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the participants
	 */
	public List<User> getParticipants() {
		return participants;
	}
	/**
	 * @param participants the participants to set
	 */
	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	/**
	 * @return the result
	 */
	public List<EventResult> getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(List<EventResult> result) {
		this.result = result;
	}
	
}
