package org.esp.core.model.trainning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Plan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String comments;
	private Date from;
	private Date to;
	private List<Plan> plans;
	private List<Tag> tags;
	private List<Excersice> excersices;
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		if(tags==null){
			tags = new ArrayList<Tag>();
		}
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
	 * @return the plans
	 */
	public List<Plan> getPlans() {
		if(plans==null){
			plans = new ArrayList<Plan>();
		}
		return plans;
	}
	/**
	 * @param plans the plans to set
	 */
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
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
	
	@Override
	public String toString(){
		return this.getName();
	}
}
