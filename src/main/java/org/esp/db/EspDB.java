package org.esp.db;

import java.util.List;

import org.esp.core.model.Sport;
import org.esp.core.model.User;
import org.esp.core.model.competition.Tournament;
import org.esp.core.model.trainning.Group;
import org.esp.core.model.trainning.Plan;
import org.esp.core.model.trainning.Tag;

public interface EspDB {
	public void addUser(User user);
	public User getUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public List<User> getUsers();
	
	public void addSport(Sport sport);
	public Sport getSport(Sport sport);
	public void updateSport(Sport sport);
	public void deleteSport(Sport sport);
	public List<Sport> getSports();
	
	public void addTournament(Tournament tournament);
	public Tournament getTournament(Tournament tournament);
	public void updateTournament(Tournament tournament);
	public void deleteTournament(Tournament tournament);
	public List<Tournament> getTournaments();
	public List<User> searchUsers(String fields, String query);
	
	public void addTag(Tag tag);
	public Tag getTag(Tag tag);
	public void updateTag(Tag tag);
	public void deleteTag(Tag tag);
	public List<Tag> getTags();
	
	public void addGroup(Group group);
	public Group getGroup(Group group);
	public void updateGroup(Group group);
	public void deleteGroup(Group group);
	public List<Group> getGroups();
	
	public void addPlan(User user,Plan plan);
	public Plan getPlan(User user,Plan plan);
	public void updatePlan(User user,Plan plan);
	public void deleteGroup(User user,Plan plan);
	public List<Plan> getPlans(User user);
}
