package org.esp.core.model.trainning;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Excersice {
	private String name;
	private double measure;
	private MeasureType dataType;
	private Execution execution;
	private Date date;
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
	 * @return the measure
	 */
	public double getMeasure() {
		return measure;
	}
	/**
	 * @param measure the measure to set
	 */
	public void setMeasure(double measure) {
		this.measure = measure;
	}
	/**
	 * @return the dataType
	 */
	public MeasureType getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(MeasureType dataType) {
		this.dataType = dataType;
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
	 * @return the execution
	 */
	public Execution getExecution() {
		return execution;
	}
	/**
	 * @param execution the execution to set
	 */
	public void setExecution(Execution execution) {
		this.execution = execution;
	}
}
