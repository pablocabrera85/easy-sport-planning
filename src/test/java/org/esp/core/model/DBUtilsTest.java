package org.esp.core.model;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.esp.core.model.competition.Tournament;
import org.esp.core.model.trainning.Group;
import org.esp.core.model.trainning.Plan;
import org.esp.core.model.trainning.Tag;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DBUtilsTest {

	EspDB utils;

	@Before
	public void setup() {
		utils = DBFactory.createDBUtils();
	}

	@Test
	public void createSport() {
		Sport s = new Sport();
		s.setName("Atletismo");
		utils.addSport(s);

		Sport retrieved = utils.getSport(s);

		assertTrue(retrieved.getName().endsWith(s.getName()));
	}

	@Test
	public void deleteSport() {
		Sport s = new Sport();
		s.setName("Atletismo");
		utils.addSport(s);

		utils.deleteSport(s);
		Sport retrieved = utils.getSport(s);

		assertTrue(retrieved == null);
	}

	@Test
	public void createSuperUser() throws ParseException {
		User s = new User();
		s.setFirstName("TestName");
		s.setLastName("TestLastName");
		s.setEmail("tt@foo.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ATHLETE);
		s.setRoles(roles);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("11/07/1995");
		s.setDateOfBirth(d);
		Group group = new Group();
		
		group.setName("Velocidad");
		s.getGroups().add(group );
		group = new Group();
		group.setName("Medio Fondo");
		s.getGroups().add(group );
		utils.addUser(s);
	}
	@Test
	public void createUser() throws ParseException {
		User s = new User();
		s.setFirstName("Daniel");
		s.setLastName("Diaz");
		s.setEmail("dcdiaz65@hotmail.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.TRAINNER);
		s.setRoles(roles);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("11/07/1965");
		s.setDateOfBirth(d);
		Group group = new Group();
		
		group.setName("Velocidad");
		s.getGroups().add(group );
		group = new Group();
		group.setName("Medio Fondo");
		s.getGroups().add(group );
		group = new Group();
		group.setName("Saltos");
		s.getGroups().add(group );
		utils.addUser(s);

		s = new User();
		s.setFirstName("Pablo");
		s.setLastName("Cabrera");
		s.setEmail("pablocabrera1985@gmail.com");
		roles = new ArrayList<Role>();
		roles.add(Role.ATHLETE);
		s.setRoles(roles);
		d = sdf.parse("24/04/1985");
		s.setDateOfBirth(d);
		Group g=new Group();
		g.setName("Velocidad");
		s.getGroups().add(g);
		g=new Group();
		g.setName("Saltos");
		s.getGroups().add(g);
		
		Tournament t = new Tournament();
		t.setDescription("Un torneo");
		t.setStartDate(new Date());
		Date endDate = new Date();
		endDate.setTime(t.getStartDate().getTime() + 360000);
		t.setEndDate(endDate);
		t.setName("Justo Roman");
		try {
			t.setUrl(new URL("http://www.consudatle.org/resultadosweb2014/archivos.php"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		s.getPlans().add(plan );
		plan = new Plan();
		plan.setName("Puesta a punto");
		
		Plan plan2 = new Plan();
		plan2.setName("Semana 1");
		plan.getPlans().add(plan2);
		s.getPlans().add(plan );
		s.getTournaments().add(t);
		Tag tag = new Tag();
		tag.setName("Fuerza");
		plan.getTags().add(tag );
		utils.addUser(s);

		User retrieved = utils.getUser(s);

		assertTrue(retrieved.getFirstName().endsWith(s.getFirstName()));
		
	}

	@Test
	public void deleteUser() {
		User s = new User();
		s.setEmail("test@test.com");
		utils.addUser(s);
		utils.deleteUser(s);
		User retrieved = utils.getUser(s);

		assertTrue(retrieved == null);
	}

	@Test
	public void createTournament() throws ParseException {
		Tournament s = new Tournament();
		s.setDescription("Un torneo");
		s.setStartDate(new Date());
		Date endDate = new Date();
		endDate.setTime(s.getStartDate().getTime() + 360000);
		s.setEndDate(endDate);
		s.setName("Justo Roman");
		try {
			s.setUrl(new URL("http://www.cada-atletismo.org"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		utils.addTournament(s);

		Tournament retrieved = utils.getTournament(s);

		assertTrue(retrieved.getDescription().endsWith(s.getDescription()));

		s.setName("Semana del Mar");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("14/12/2013");
		s.setStartDate(d);
		s.setEndDate(sdf.parse("15/12/2013"));
		utils.addTournament(s);
	}

	@Test
	public void deleteTournament() {
		Tournament s = new Tournament();
		s.setDescription("Un torneo");
		s.setName("Justo Roman");
		try {
			s.setUrl(new URL("http://www.cada-atletismo.org"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		utils.addTournament(s);

		utils.deleteTournament(s);
		Tournament retrieved = utils.getTournament(s);

		assertTrue(retrieved == null);
	}
	
	@Test
	public void findUser() throws UnknownHostException{
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SportPlanner");
		DBCollection c = db.getCollection("users");
		User user =new User();
		user.getRoles().add(Role.ATHLETE);
		final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.create();
		String json = gson.toJson(user);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		DBCursor cursor=c.find(dbObject);
		while(cursor.hasNext()){
			DBObject found = cursor.next();
			String name=(String) found.get("firstName");
			assertTrue("Pablo".equals(name));
		}
	}
	
	@Test
	public void findUserById() throws UnknownHostException{
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SportPlanner");
		DBCollection c = db.getCollection("users");
		User user = new User();
		user.getRoles().add(Role.ATHLETE);
		final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.create();
		String json = gson.toJson(user);
		DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(json);
		DBCursor cursor=c.find(dbObject);
		while(cursor.hasNext()){
			DBObject found = cursor.next();
			String name=(String) found.get("firstName");
			assertTrue("Pablo".equals(name));
			
			found=c.findOne(found.get("_id"));
			assertTrue(found!=null);
			
			DBObject ob = new BasicDBObject();
			ob.put("_id", new ObjectId(found.get("_id").toString()));
			found=c.findOne(ob);
			assertTrue(found!=null);
		}
	}
	
	
	@Test
	public void getGroups(){
		User s = new User();
		s = new User();
		s.setFirstName("Pablo");
		s.setLastName("Cabrera");
		s.setEmail("pablocabrera1985@gmail.com");
		
		utils.deleteUser(s);
		
		s.setFirstName("Daniel");
		s.setLastName("Diaz");
		s.setEmail("dcdiaz65@hotmail.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.TRAINNER);
		s.setRoles(roles);
		utils.deleteUser(s);
	}
	
	@Test
	public void addPlan(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo2@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		
		utils.addPlan(user, plan);
		
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==1);
	}
	
	@Test
	public void addNestedPlan(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo3@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		plan.getPlans().add(plan2);
		utils.addPlan(user, plan);
		
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==1);
		assertTrue(plans.get(0).getPlans().size()==1);
	}
	
	@Test
	public void addNested2LvlPlan(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo4@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		plan.getPlans().add(plan2);
		utils.addPlan(user, plan);
			
		Plan plan3 = new Plan();
		plan3.setName("Semana 1");
		plan2.getPlans().add(plan3);
		utils.addPlan(user, plan);
		
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==1);
		assertTrue(plans.get(0).getPlans().size()==1);
		assertTrue(plans.get(0).getPlans().get(0).getPlans().size()==1);
	}
	
	@Test
	public void complexTree(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo5@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		plan.getPlans().add(plan2);
		utils.addPlan(user, plan);
			
		Plan plan3 = new Plan();
		plan3.setName("Semana 1");
		plan2.getPlans().add(plan3);
		utils.addPlan(user, plan);
		
		
		Plan plan4 = new Plan();
		plan4.setName("Pretemporada");
		
		Plan plan5 = new Plan();
		plan5.setName("Puesta a punto");
		plan4.getPlans().add(plan5);
			
		Plan plan6 = new Plan();
		plan6.setName("Semana 2");
		plan5.getPlans().add(plan6);
		
		utils.addPlan(user, plan4);
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==1);
		assertTrue(plans.get(0).getPlans().size()==1);
		assertTrue(plans.get(0).getPlans().get(0).getPlans().size()==2);
	}
	
	@Test
	public void complexTree2(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo6@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		plan.getPlans().add(plan2);
		utils.addPlan(user, plan);
			
		Plan plan3 = new Plan();
		plan3.setName("Semana 1");
		plan2.getPlans().add(plan3);
		utils.addPlan(user, plan);
		
		
		Plan plan4 = new Plan();
		plan4.setName("Pretemporada");
		
		Plan plan5 = new Plan();
		plan5.setName("Saltos");
		plan4.getPlans().add(plan5);
		
		utils.addPlan(user, plan4);
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==1);
		assertTrue(plans.get(0).getPlans().size()==2);
		assertTrue(plans.get(0).getPlans().get(0).getPlans().size()==1);
	}
	
	@Test
	public void complexTreeMutipleRoot(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo9@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		Plan plan8 = new Plan();
		plan8.setName("Pretemporada 2013");
		utils.addPlan(user, plan8);
		
		Plan plan2 = new Plan();
		plan2.setName("Puesta a punto");
		plan.getPlans().add(plan2);
		utils.addPlan(user, plan);
			
		Plan plan3 = new Plan();
		plan3.setName("Semana 1");
		plan2.getPlans().add(plan3);
		utils.addPlan(user, plan);
		
		
		Plan plan4 = new Plan();
		plan4.setName("Pretemporada");
		
		Plan plan5 = new Plan();
		plan5.setName("Saltos");
		plan4.getPlans().add(plan5);
		
		utils.addPlan(user, plan4);
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==2);
		assertTrue(plans.get(1).getPlans().size()==2);
		assertTrue(plans.get(1).getPlans().get(0).getPlans().size()==1);
	}
	
	@Test
	public void addExercise(){
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("foo10@foo.com");
		
		Plan plan = new Plan();
		plan.setName("Pretemporada");
		utils.addPlan(user, plan);
		
		utils.updatePlan(user, plan);
		List<Plan> plans = utils.getPlans(user);
		assertTrue(plans.size()==2);
		assertTrue(plans.get(1).getPlans().size()==2);
		assertTrue(plans.get(1).getPlans().get(0).getPlans().size()==1);
	}
}
