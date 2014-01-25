package org.esp.core.model.competition;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.esp.core.model.User;
@XmlRootElement
public class EventResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private User athlete;
	private Integer order;
	/**
	 * @return the athlete
	 */
	public User getAthlete() {
		return athlete;
	}
	/**
	 * @param athlete the athlete to set
	 */
	public void setAthlete(User athlete) {
		this.athlete = athlete;
	}
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
