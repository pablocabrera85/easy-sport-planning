package org.esp.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.esp.core.model.User;
import org.esp.core.model.trainning.Plan;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Path("/plans")
public class PlanResource {
	private final EspDB db;

	public PlanResource() {
		db = DBFactory.createDBUtils();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void addPlan(@PathParam("id") String userId, Plan plan) {
		User user = new User();
		user.set_id(new ObjectId(userId));
		db.addPlan(user, plan);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<Plan> getPlan(@PathParam("id") String userId) {

		User user = new User();
		user.set_id(new ObjectId(userId));
		return db.getPlans(user);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void updatePlan(@PathParam("id") String userId,
			@QueryParam(value = "path") String path, Plan plan) {
		User user = new User();
		user.set_id(new ObjectId(userId));
		String[] pathElements = path.split(",");
		Plan root = new Plan();

		if (pathElements.length == 1) {
			root = plan;
		} else {
			int index = 0;
			Plan current = null;
			Plan cursor = root;
			do {
				cursor.setName(pathElements[index]);
				index++;
				if (index + 1 == pathElements.length) {
					plan.setName(pathElements[index]);
					cursor.getPlans().add(plan);
					break;
				} else if (index < pathElements.length) {
					current = new Plan();
					cursor.getPlans().add(current);
					cursor = current;
				}
			} while (index < pathElements.length);
		}

		db.updatePlan(user, root);
	}

}
