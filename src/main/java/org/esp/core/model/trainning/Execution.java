package org.esp.core.model.trainning;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Execution implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int repetitions;
	private int series;
	private int blocks;
	private int volume;
	private double effort;
	private double microPause;
	private double macroPause;

	/**
	 * @return the repetitions
	 */
	public int getRepetitions() {
		return repetitions;
	}
	/**
	 * @param repetitions the repetitions to set
	 */
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	/**
	 * @return the series
	 */
	public int getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(int series) {
		this.series = series;
	}
	/**
	 * @return the Blocks
	 */
	public int getBlocks() {
		return blocks;
	}
	/**
	 * @param Blocks the Blocks to set
	 */
	public void setBlocks(int Blocks) {
		this.blocks = Blocks;
	}
	/**
	 * @return the effort
	 */
	public double getEffort() {
		return effort;
	}
	/**
	 * @param effort the effort to set
	 */
	public void setEffort(double effort) {
		this.effort = effort;
	}
	/**
	 * @return the microPause
	 */
	public double getMicroPause() {
		return microPause;
	}
	/**
	 * @param microPause the microPause to set
	 */
	public void setMicroPause(double microPause) {
		this.microPause = microPause;
	}
	/**
	 * @return the macroPause
	 */
	public double getMacroPause() {
		return macroPause;
	}
	/**
	 * @param macroPause the macroPause to set
	 */
	public void setMacroPause(double macroPause) {
		this.macroPause = macroPause;
	}
	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
