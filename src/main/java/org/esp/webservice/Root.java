package org.esp.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Root {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResources(){
		
		List<Resource> resources = getResourcesFromSystem();
		
		return Response.ok().entity(new GenericEntity<List<Resource>>(resources ) {}).build();
	}

	private List<Resource> getResourcesFromSystem() {
		List<Resource> resources =  new ArrayList<Resource>();
		Resource res = new Resource();
		res.setName("Sports");
		res.setUrl(SportResource.class.getAnnotation(Path.class).value());
		resources.add(res);
		res = new Resource();
		res.setName("Users");
		res.setUrl(UserResource.class.getAnnotation(Path.class).value());
		resources.add(res);
		return resources;
	}
}
