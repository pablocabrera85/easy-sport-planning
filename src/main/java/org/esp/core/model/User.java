package org.esp.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.esp.core.model.competition.Tournament;
import org.esp.core.model.trainning.Group;
import org.esp.core.model.trainning.Plan;

@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private ObjectId _id;
	private String firstName;
	private String lastName;
	private String password;
	private String url;
	private List<Group> groups;
	private Sport sport;
	private List<Plan> plans;
	private List<Tournament> tournaments;
	private Date dateOfBirth;
	private Gender gender;
	private String email;
	private List<Phone> phones;
	private List<Role> roles;

	/**
	 * @return the groups
	 */
	public List<Group> getGroups() {
		if (groups == null) {
			groups = new ArrayList<Group>();
		}
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	/**
	 * @return the sport
	 */
	public Sport getSport() {
		return sport;
	}

	/**
	 * @param sport
	 *            the sport to set
	 */
	public void setSport(Sport sport) {
		this.sport = sport;
	}

	/**
	 * @return the plans
	 */
	public List<Plan> getPlans() {
		if (plans == null) {
			plans = new ArrayList<Plan>();
		}
		return plans;
	}

	/**
	 * @param plans
	 *            the plans to set
	 */
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the tournaments
	 */
	public List<Tournament> getTournaments() {
		if (tournaments == null) {
			tournaments = new ArrayList<Tournament>();
		}
		return tournaments;
	}

	/**
	 * @param tournaments
	 *            the tournaments to set
	 */
	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}
}
