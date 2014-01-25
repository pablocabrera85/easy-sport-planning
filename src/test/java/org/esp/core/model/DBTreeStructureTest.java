package org.esp.core.model;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DBTreeStructureTest {

	MongoClient mongoClient = null;
	String planCollections = "plans";
	DBCollection plans = null;

	@Before
	public void setup() throws UnknownHostException {
		mongoClient = new MongoClient();
		DB db = mongoClient.getDB("SportPlanner");
		plans = db.getCollection(planCollections);
		plans.ensureIndex( new BasicDBObject("path", 1 ));
	}

	@After
	public void tearDown() {
		plans.drop();
		mongoClient.close();

	}

	@Test
	public void createPlanTreeRoot() {
		BasicDBObject plan = new BasicDBObject("_id", "test@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject id = new BasicDBObject("_id", "test@test.com");
		DBCursor cursor = plans.find(id);

		if (cursor.hasNext()) {
			DBObject obj = cursor.next();

			assertTrue(obj != null);
		}
		assertTrue(cursor != null);
	}

	@Test
	public void createPlanTree() {
		BasicDBObject plan = new BasicDBObject("_id", "test2@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", "test2@test.com");
		plans.insert(plan1);
		
		BasicDBObject id = new BasicDBObject("path", "test2@test.com");
		DBCursor cursor = plans.find(id);

		if (cursor.hasNext()) {
			DBObject obj = cursor.next();

			assertTrue(obj != null);
		}
		assertTrue(cursor != null);
	}
	
	@Test
	public void createPlanTreeMultipleSons() {
		BasicDBObject plan = new BasicDBObject("_id", "test3@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", "test3@test.com");
		plans.insert(plan1);
		
		BasicDBObject plan2 = new BasicDBObject("_id", "Puesta A punto 2014").append(
				"path", "test3@test.com");
		plans.insert(plan2);
		
		BasicDBObject id = new BasicDBObject("path", "test3@test.com");
		DBCursor cursor = plans.find(id);

		int sons=0;
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 2);
		assertTrue(cursor != null);
	}
	
	@Test
	public void createPlanTreeMultipleSonsLvl2() {
		BasicDBObject plan = new BasicDBObject("_id", "test4@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", "test4@test.com");
		plans.insert(plan1);
		
		BasicDBObject plan1Lvl2 = new BasicDBObject("_id", "Enero").append(
				"path", "test4@test.com,PreTemporada 2014");
		plans.insert(plan1Lvl2);
		
		BasicDBObject plan2 = new BasicDBObject("_id", "Puesta A punto 2014").append(
				"path", "test4@test.com");
		plans.insert(plan2);
		
		BasicDBObject id = new BasicDBObject("path", "test4@test.com");
		DBCursor cursor = plans.find(id);

		int sons=0;
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 2);
		
		id = new BasicDBObject("path", "test4@test.com,PreTemporada 2014");
		cursor = plans.find(id);

		sons=0;
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 1);
		
	}
	
	@Test
	public void createPlanEntireTree() {
		BasicDBObject plan = new BasicDBObject("_id", "test4@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", "test4@test.com");
		plans.insert(plan1);
		
		BasicDBObject plan1Lvl2 = new BasicDBObject("_id", "Enero").append(
				"path", "test4@test.com,PreTemporada 2014");
		plans.insert(plan1Lvl2);
		
		BasicDBObject plan2 = new BasicDBObject("_id", "Puesta A punto 2014").append(
				"path", "test4@test.com");
		plans.insert(plan2);
		
		DBCursor cursor = plans.find().sort(new BasicDBObject("path",1));

		int sons=0;
		
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 4);
		
	}
	
	@Test
	public void subTree() {
		BasicDBObject plan = new BasicDBObject("_id", "test5@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", "test5@test.com");
		plans.insert(plan1);
		
		BasicDBObject plan1Lvl2 = new BasicDBObject("_id", "Enero").append(
				"path", "test5@test.com,PreTemporada 2014");
		plans.insert(plan1Lvl2);
		
		BasicDBObject plan2 = new BasicDBObject("_id", "Puesta A punto 2014").append(
				"path", "test5@test.com");
		plans.insert(plan2);
		
		DBCursor cursor = plans.find(new BasicDBObject("path",Pattern.compile("PreTemporada 2014"))).sort(new BasicDBObject("path",1));

		int sons=0;
		
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 1);
		
	}
	
	@Test
	public void subFullTree() {
		BasicDBObject plan = new BasicDBObject("_id", "test6@test.com").append(
				"path", null);
		plans.insert(plan);

		BasicDBObject plan1 = new BasicDBObject("_id", "PreTemporada 2014").append(
				"path", ",test6@test.com,");
		plans.insert(plan1);
		
		BasicDBObject plan1Lvl2 = new BasicDBObject("_id", "Enero").append(
				"path", ",test6@test.com,PreTemporada 2014,");
		plans.insert(plan1Lvl2);
		
		BasicDBObject plan2 = new BasicDBObject("_id", "Puesta A punto 2014").append(
				"path", ",test6@test.com,");
		plans.insert(plan2);
		
		DBCursor cursor = plans.find(new BasicDBObject("path",Pattern.compile("^,test6@test.com,"))).sort(new BasicDBObject("path",1));

		int sons=0;
		
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			sons++;
			assertTrue(obj != null);
		}
		assertTrue(sons== 3);
		
	}
}
