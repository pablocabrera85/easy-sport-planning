package org.esp.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.esp.core.model.trainning.Tag;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;

@Path("/tags")
public class TagResource {

	private final EspDB db;
	public TagResource(){
		db = DBFactory.createDBUtils();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSports() {
		List<Tag> sl = db.getTags();
		return Response.ok().entity(new GenericEntity<List<Tag>>(sl) {
		}).build();
	}
}
