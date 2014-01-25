package org.esp.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.esp.core.model.User;
import org.esp.core.model.competition.Event;
import org.esp.core.model.competition.EventResult;
import org.esp.core.model.competition.Location;
import org.esp.core.model.competition.Tournament;
import org.esp.db.DBFactory;
import org.esp.db.EspDB;

@Path("tournaments")
public class TournamentResource {
	private final EspDB db;
	public TournamentResource(){
		db = DBFactory.createDBUtils();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tournament> getTournaments(){
		return db.getTournaments();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}")
	public List<Tournament> getTournaments(@PathParam("sport") String sport){
		final Tournament s = new Tournament();
		s.setDescription("Un torneo");
		s.setStartDate(new Date());
		Date endDate = new Date();
		endDate.setTime(s.getStartDate().getTime()+360000);
		s.setEndDate(endDate);
		s.setName("Justo Roman");
		try {
			s.setUrl(new URL("http://www.cada-atletismo.org"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Tournament>(){
			{
				add(s);
			}
		};
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}/{id}")
	public Tournament getTournament(@PathParam("sport") String sport,@PathParam("id") Integer id){
		final Tournament s = new Tournament();
		s.setDescription("Un torneo");
		s.setStartDate(new Date());
		Date endDate = new Date();
		endDate.setTime(s.getStartDate().getTime()+360000);
		s.setEndDate(endDate);
		s.setName("Justo Roman");
		try {
			s.setUrl(new URL("http://www.cada-atletismo.org"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{sport}/{id}/event/{eventId}")
	public Event getResult(@PathParam("sport") String sport,@PathParam("id") Integer id,@PathParam("eventId") Integer eventId){
		final Event s = new Event();
		s.setName("100m");
		List<EventResult> results = new ArrayList<EventResult>();
		EventResult res=new EventResult();
		res.setAthlete(new User());
		res.setOrder(1);
		results.add(res);
		s.setResult(results );
		return s;
	}
}
