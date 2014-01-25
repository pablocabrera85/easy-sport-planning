package org.esp.db;

import java.net.UnknownHostException;

public class DBFactory {
	private static EspDB db;

	public static EspDB createDBUtils() {
		try {
			if (db == null) {
				db = new DBUtilsImpl("SportPlanner");
			}
			return db;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
}
