package org.esp.webservice;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.esp.core.model.Gender;
import org.esp.core.model.User;
import org.esp.core.model.Role;
import org.esp.core.model.Sport;
import org.esp.core.model.competition.Tournament;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;

@Path("/sports")
public class SportResource {
	private final EspDB db;
	public SportResource(){
		db = DBFactory.createDBUtils();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSports() {
		List<Sport> sl = db.getSports();
		return Response.ok().entity(new GenericEntity<List<Sport>>(sl) {
		}).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}/trainers")
	public List<User> getTrainers(@PathParam("{sport}") String sport,
			@DefaultValue("all") @QueryParam(value = "fields") String fields) {
		final User s = new User();
		if ("all".equals(fields)) {
			s.setFirstName("Daniel");
			s.setLastName("Diaz");
		} else {
			s.setLastName("Diaz");
		}
		s.getRoles().add(Role.TRAINNER);
		s.setDateOfBirth(new Date());

		return new ArrayList<User>() {
			{
				add(s);
			}
		};
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}/athletes")
	public List<User> getAthletes(@PathParam("{sport}") String sport) {
		final User s = new User();
		s.setFirstName("Pablo");
		s.setLastName("Cabrera");
		s.setGender(Gender.MALE);
		s.getRoles().add(Role.ATHLETE);
		s.getRoles().add(Role.TRAINNER);
		s.setDateOfBirth(new Date());
		return new ArrayList<User>() {
			{
				add(s);
			}
		};
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}/tournaments")
	public List<Tournament> getTournaments(@PathParam("{sport}") String sport) {
		final Tournament s = new Tournament();
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
		return new ArrayList<Tournament>() {
			{
				add(s);
			}
		};
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSport(Sport sport) {
		URI uri = null;
		try {
			uri = new URI("/sports/" + sport.getName());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.created(uri).build();
	}
}
