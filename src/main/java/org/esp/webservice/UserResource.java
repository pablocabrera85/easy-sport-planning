package org.esp.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.bson.types.ObjectId;
import org.esp.core.model.User;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;

@Path("/users")
public class UserResource {

	private final EspDB db;
	@Context
	UriInfo uriInfo;

	public UserResource() {
		db = DBFactory.createDBUtils();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {

		return db.getUsers();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public User getUser(@PathParam("id") String id) {
		User u = new User();
		u.set_id(new ObjectId(id));
		return db.getUser(u);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public List<User> getUsers(
			@DefaultValue("all") @QueryParam("fields") String fields,
			@QueryParam("q") String query,
			@QueryParam("group") String group) {

		if(group!=null)
			query = group;
		return db.searchUsers(fields, query);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
			User temp = new User();
			temp.setEmail(user.getEmail());
			if (db.getUser(temp) != null) {
				return Response.status(Response.Status.FORBIDDEN).build();
			}
			db.addUser(user);
			return Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void updateUser(@PathParam("id") String id, User user) {
		user.set_id(new ObjectId(id));
		db.updateUser(user);
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public User login(LoginRequest login) {
		User u = new User();
		u.setEmail(login.getEmail());
		return db.getUser(u);
	}
}
