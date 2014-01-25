package org.esp.db;

import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import org.bson.types.BasicBSONList;
import org.esp.core.model.Sport;
import org.esp.core.model.User;
import org.esp.core.model.competition.Tournament;
import org.esp.core.model.trainning.Group;
import org.esp.core.model.trainning.Plan;
import org.esp.core.model.trainning.Tag;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class DBUtilsImpl implements EspDB {

	String sportsCollection = "sports";
	String usersCollection = "users";
	String tournamentsCollection = "tournaments";
	String tagsCollection = "tags";
	String plansCollection = "plans";

	final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.create();

	public DBUtilsImpl(String dataBase) throws UnknownHostException {
		mongoClient = new MongoClient();
		db = mongoClient.getDB(dataBase);
	}

	final MongoClient mongoClient;
	final DB db;

	@Override
	public void addUser(User user) {
		DBCollection sports = db.getCollection(usersCollection);
		String json = gson.toJson(user);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		sports.insert(dbObject);
	}

	@Override
	public User getUser(User user) {
		DBCollection users = db.getCollection(usersCollection);
		String json = gson.toJson(user);

		DBObject request = (DBObject) com.mongodb.util.JSON.parse(json);
		if (user.get_id() != null)
			request.put("_id", user.get_id());

		DBObject dbObject = users.findOne(request);

		User userVal = gson.fromJson(com.mongodb.util.JSON.serialize(dbObject),
				User.class);

		List<Plan> plans = this.getPlans(userVal);
		userVal.setPlans(plans);
		return userVal;
	}

	@Override
	public void updateUser(User user) {
		DBCollection sports = db.getCollection(usersCollection);
		String json = gson.toJson(user);

		DBObject request = new BasicDBObject();

		if (user.get_id() != null) {
			request.put("_id", user.get_id());
		}

		DBObject set = new BasicDBObject();
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		dbObject.removeField("_id");

		if (dbObject.get("groups") != null) {
			DBObject each = new BasicDBObject("$each", dbObject.get("groups"));
			DBObject groups = new BasicDBObject("groups", each);
			set.put("$push", groups);
		}

		WriteResult res = sports.update(request, set);
		res.getError();
	}

	@Override
	public void deleteUser(User user) {
		DBCollection sports = db.getCollection(usersCollection);
		String json = gson.toJson(user);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		sports.remove(dbObject);
	}

	@Override
	public void addSport(Sport sport) {
		DBCollection sports = db.getCollection(sportsCollection);
		String json = gson.toJson(sport);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		sports.insert(dbObject);
	}

	@Override
	public Sport getSport(Sport sport) {
		DBCollection sports = db.getCollection(sportsCollection);
		String json = gson.toJson(sport);
		DBObject dbObject = sports.findOne((DBObject) com.mongodb.util.JSON
				.parse(json));
		return gson.fromJson(com.mongodb.util.JSON.serialize(dbObject),
				Sport.class);
	}

	@Override
	public void updateSport(Sport sport) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void deleteSport(Sport sport) {
		DBCollection sports = db.getCollection(sportsCollection);
		String json = gson.toJson(sport);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		sports.remove(dbObject);
	}

	@Override
	public List<Sport> getSports() {
		DBCollection sports = db.getCollection(sportsCollection);
		List<Sport> list = new ArrayList<Sport>();
		DBCursor cursor = sports.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Sport p = gson.fromJson(com.mongodb.util.JSON.serialize(obj),
					Sport.class);
			list.add(p);
		}
		return list;
	}

	@Override
	public List<User> getUsers() {
		DBCollection users = db.getCollection(usersCollection);
		List<User> list = new ArrayList<User>();
		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			User p = gson.fromJson(com.mongodb.util.JSON.serialize(obj),
					User.class);
			p.setUrl("v1/users/" + obj.get("_id").toString());
			list.add(p);
		}
		return list;
	}

	@Override
	public void addTournament(Tournament tournament) {
		DBCollection tournaments = db.getCollection(tournamentsCollection);
		String json = gson.toJson(tournament);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		tournaments.insert(dbObject);
	}

	@Override
	public Tournament getTournament(Tournament tournament) {
		DBCollection sports = db.getCollection(tournamentsCollection);
		String json = gson.toJson(tournament);
		DBObject dbObject = sports.findOne((DBObject) com.mongodb.util.JSON
				.parse(json));
		return gson.fromJson(com.mongodb.util.JSON.serialize(dbObject),
				Tournament.class);
	}

	@Override
	public void updateTournament(Tournament tournament) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void deleteTournament(Tournament tournament) {
		DBCollection sports = db.getCollection(tournamentsCollection);
		String json = gson.toJson(tournament);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		sports.remove(dbObject);
	}

	@Override
	public List<Tournament> getTournaments() {
		List<Tournament> list = getElementFromCollection(tournamentsCollection,
				Tournament.class);
		return list;
	}

	private <T extends Object> void addToCollection(T t, String collectionName) {
		DBCollection collection = db.getCollection(collectionName);
		String json = gson.toJson(t);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		collection.insert(dbObject);
	}

	private <T extends Object> List<T> getElementFromCollection(
			String collection, Class<T> clazz) {
		DBCollection set = db.getCollection(collection);
		List<T> list = new ArrayList<T>();
		DBCursor cursor = set.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			T p = gson.fromJson(com.mongodb.util.JSON.serialize(obj), clazz);
			list.add(p);
		}
		return list;
	}

	/**
	 * TODO: User fields parameter and parse query Only query
	 */
	@Override
	public List<User> searchUsers(String fields, String query) {
		DBCollection users = db.getCollection(usersCollection);
		List<User> list = new ArrayList<User>();
		DBObject dbQuery = null;
		User user = new User();
		if ("ATHLETE".equals(query)) {
			BasicDBList docIds = new BasicDBList();
			docIds.add(query);
			DBObject elemMatch = new BasicDBObject("$in", docIds);
			dbQuery = new BasicDBObject("roles", elemMatch);
		} else if ("TRAINNER".equals(query)) {
			BasicDBList docIds = new BasicDBList();
			docIds.add(query);
			DBObject elemMatch = new BasicDBObject("$in", docIds);
			dbQuery = new BasicDBObject("roles", elemMatch);
		} else {
			Group group = new Group();
			group.setName(query);
			user.getGroups().add(group);

			BasicDBList docIds = new BasicDBList();
			docIds.add(group.getName());
			DBObject inClause = new BasicDBObject("$in", docIds);
			DBObject inName = new BasicDBObject("name", inClause);
			DBObject elemMatch = new BasicDBObject("$elemMatch", inName);

			BasicDBList roles = new BasicDBList();
			roles.add("ATHLETE");
			DBObject elemMatchRole = new BasicDBObject("$in", roles);

			dbQuery = new BasicDBObject("groups", elemMatch).append("roles",
					elemMatchRole);

		}

		DBCursor cursor = users.find(dbQuery);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			User p = gson.fromJson(com.mongodb.util.JSON.serialize(obj),
					User.class);
			p.setUrl("v1/users/" + obj.get("_id").toString());
			list.add(p);
		}
		return list;
	}

	@Override
	public void addTag(Tag tag) {
		addToCollection(tag, tagsCollection);
	}

	@Override
	public Tag getTag(Tag tag) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void updateTag(Tag tag) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void deleteTag(Tag tag) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public List<Tag> getTags() {
		List<Tag> list = getElementFromCollection(tagsCollection, Tag.class);
		return list;
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public Group getGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlan(User user, Plan plan) {

		DBCollection users = db.getCollection(usersCollection);
		String json = gson.toJson(user);

		DBObject request = (DBObject) com.mongodb.util.JSON.parse(json);
		if (user.get_id() != null)
			request.put("_id", user.get_id());

		DBObject dbObject = users.findOne(request);

		User userVal = gson.fromJson(com.mongodb.util.JSON.serialize(dbObject),
				User.class);

		DBCollection plans = db.getCollection(plansCollection);

		BasicDBObject root = new BasicDBObject("_id", "," + user.getEmail()
				+ ",").append("path", null);
		if (plans.findOne(root) == null)
			plans.insert(root);

		json = gson.toJson(Utils.getPlanData(plan));

		BasicDBObject userPlan = new BasicDBObject("_id", Utils.getPlanPath(
				userVal, plan))
				.append("path", Utils.getPlanPath(userVal, plan)).append(
						"data", (DBObject) com.mongodb.util.JSON.parse(json));
		plans.insert(userPlan);

	}

	@Override
	public Plan getPlan(User user, Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePlan(User user, Plan plan) {

		DBCollection users = db.getCollection(usersCollection);
		String json = gson.toJson(user);

		DBObject request = (DBObject) com.mongodb.util.JSON.parse(json);
		if (user.get_id() != null)
			request.put("_id", user.get_id());

		DBObject dbObject = users.findOne(request);

		User userVal = gson.fromJson(com.mongodb.util.JSON.serialize(dbObject),
				User.class);

		DBCollection plans = db.getCollection(plansCollection);

		BasicDBObject root = new BasicDBObject("_id", "," + user.getEmail()
				+ ",").append("path", null);
		if (plans.findOne(root) == null)
			plans.insert(root);

		json = gson.toJson(Utils.getPlanData(plan));

		plans.update(
				new BasicDBObject("_id", Utils.getPlanPath(userVal, plan))
						.append("path", Utils.getPlanPath(userVal, plan)),
				new BasicDBObject("data", (DBObject) com.mongodb.util.JSON
						.parse(json)).append("path",
						Utils.getPlanPath(userVal, plan)));

	}

	@Override
	public void deleteGroup(User user, Plan plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Plan> getPlans(User user) {

		List<Plan> planes = new ArrayList<Plan>();
		DBCollection plans = db.getCollection(plansCollection);
		List<Plan> order = new ArrayList<Plan>();
		DBCursor cursor = plans.find(
				new BasicDBObject("path", Pattern.compile("^,"
						+ user.getEmail() + ","))).sort(
				new BasicDBObject("path", 1));
		Plan parent = null;
		Vector<Plan> stack = new Vector<Plan>();
		Vector<String> paths = new Vector<String>();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Plan current = gson.fromJson(
					com.mongodb.util.JSON.serialize(obj.get("data")),
					Plan.class);
			order.add(current);
			String path = (String) obj.get("path");
			if (stack.isEmpty()) {
				stack.add(current);
				paths.add(path);
				planes.add(current);
			} else {
				if (Utils.isParentPath(path, paths.lastElement())) {
					parent = stack.lastElement();
					stack.add(current);
					paths.add(path);
					parent.getPlans().add(current);
				} else {
					do {
						stack.remove(stack.size() - 1);
						paths.remove(paths.size() - 1);
					} while (!stack.isEmpty()
							&& !Utils.isParentPath(path, paths.lastElement()));

					if (stack.isEmpty()) {
						stack.add(current);
						paths.add(path);
						planes.add(current);
					} else {
						stack.lastElement().getPlans().add(current);
						stack.add(current);
						paths.add(path);
					}
				}
			}
		}

		return planes;
	}

}
