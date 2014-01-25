package org.esp.core.model;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class ModelTest {
	String sportsCollection = "sports";
	String usersCollection = "users";
	@Test
	public void setupDB() throws UnknownHostException, ParseException {
//		MongoClient mongoClient = new MongoClient();
//		DB db = mongoClient.getDB("SportPlanner");
//		
//		DBCollection users = db.getCollection(usersCollection);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date d = sdf.parse("24/04/1985");
//		
//		Date d2 = sdf.parse("11/07/1965");
//		User person = new User();
//		person.setDateOfBirth(d2);
//		person.setGender(Gender.MALE);
//		person.setFirstName("Daniel");
//		person.setLastName("Diaz");
//		person.getRoles().add(Role.TRAINER);
//		person.setEmail("test@test.com");
//		
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();		
//		String json = gson.toJson(person);
//		DBObject dbObject = (DBObject)com.mongodb.util.JSON.parse(json);
//		
//		User athlete = new User();
//		athlete.setDateOfBirth(d2);
//		athlete.setGender(Gender.MALE);
//		athlete.setFirstName("Pablo");
//		athlete.setLastName("Cabrera");
//		athlete.getRoles().add(Role.ATHLETE);
//		athlete.setEmail("pablocabrera2985@gmail.com");
//		users.insert(dbObject);
//		
//		json = gson.toJson(athlete);
//		dbObject = (DBObject)com.mongodb.util.JSON.parse(json);
//		users.insert(dbObject);
//		
//		DBCollection sports = db.getCollection(sportsCollection);
//
//		BasicDBObject sport = new BasicDBObject("name", "Atletismo");
//
//		sports.insert(sport);
//		
//		sports = db.getCollection(sportsCollection);
//		
//		DBCursor cursor = users.find();
//		 if( cursor.hasNext() ){
//		     DBObject obj = cursor.next();
//		     User p=  gson.fromJson(com.mongodb.util.JSON.serialize(obj), User.class);
//		     assertTrue(p!=null);
//		 }
//		users.drop();
//		sports.drop();
//		
//		mongoClient.close();

	}
}
