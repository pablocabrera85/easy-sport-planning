package org.esp.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.esp.core.model.trainning.Excersice;
import org.esp.core.model.trainning.Plan;
import org.esp.core.model.trainning.Tag;

@XmlRootElement
public class DAOPlan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String comments;
	private Date from;
	private Date to;
	private List<Tag> tags;
	private List<Excersice> excersices;
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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	/**
	 * @return the excersices
	 */
	public List<Excersice> getExcersices() {
		return excersices;
	}
	/**
	 * @param excersices the excersices to set
	 */
	public void setExcersices(List<Excersice> excersices) {
		this.excersices = excersices;
	}

	public static DAOPlan fromPlan(Plan plan){
		DAOPlan daoPlan = new DAOPlan();
		daoPlan.setComments(plan.getComments());
		daoPlan.setExcersices(plan.getExcersices());
		daoPlan.setName(plan.getName());
		daoPlan.setTags(plan.getTags());
		daoPlan.setFrom(plan.getFrom());
		daoPlan.setTo(plan.getTo());
		return daoPlan;
	}
	
}
