package tn.esprit.Services;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.swing.JSpinner.DateEditor;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.impl.cookie.DateUtils;
import org.xml.sax.Parser;

import tn.esprit.Entity.Activite;
import tn.esprit.Entity.Formation;
import tn.esprit.Interfaces.ActiviteServiceRemote;

@Stateful
@LocalBean
public class ActiviteService  implements ActiviteServiceRemote{

	
	
	@Override
	public List<Activite> GetAll()  {
		
		List<Activite>  lasp = new ArrayList<Activite>();
    	Client client = ClientBuilder.newClient();
    	
    //	RestClientFactory restClientFactory = new RestClientFactory();
		WebTarget webTarget = client.target("http://localhost:44389/api/ActiviteWebApi"); 
    	//URL myURL = new URL("https://localhost:44389/Formation/GetFormations");
    	//WebTarget webTarget = restClientFactory.buildUnsecureWebTarget(myURL);
    	Response response = webTarget.request().get();
    	
    	String result = response.readEntity(String.class); 
    	
    	//System.out.println(result);
    	JsonReader jsonReader = Json.createReader(new StringReader(result));
    	JsonArray object =  jsonReader.readArray();
    	
    	 
    	for (int i=0;i<object.size();i++)
    	{
    	 
    		Activite m = new Activite();
    	 //String dateString;
       	 m.setActiviteID(object.getJsonObject(i).getInt("ActiviteID")); 
    	 m.setTitle(object.getJsonObject(i).getString("Title")); 
    	 m.setAffiche(object.getJsonObject(i).getString("Affiche")); 
    	 m.setTheme(object.getJsonObject(i).getString("Theme")); 
    	 m.setLocation(object.getJsonObject(i).getString("Location"));
    	// m.setStart(DateParser.parseMsTimestampToDate(object.getJsonObject(i).getString("Start")));
    	// m.setEnd(DateParser.parseMsTimestampToDate(object.getJsonObject(i).getString("End")));
    	 m.setDescription(object.getJsonObject(i).getString("Description"));
    	 m.setAgeMax( object.getJsonObject(i).getInt("AgeMax"));
    	 m.setAgeMin( object.getJsonObject(i).getInt("AgeMin"));
    	 m.setClassSize( object.getJsonObject(i).getInt("ClassSize"));
    	 m.setDuration( object.getJsonObject(i).getInt("Duration"));
    	// m.setDocument(object.getJsonObject(i).getString("Document"));
    	 m.setUserId(object.getJsonObject(i).getString("UserId"));
    	 m.setOutils(object.getJsonObject(i).getString("Outils"));
    	 m.setProfessor(object.getJsonObject(i).getString("Professor"));
    	 
    	 SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	 
    	 String dateString;
    	 dateString = object.getJsonObject(i).get("Start").toString();
    	dateString = dateString.replaceAll("\"", "");
    	 Date date = null;
    	 try {
        
    	  date = sourceFormat.parse(dateString);
    	   
    	 } 
    	 catch (ParseException e) {
    	   e.printStackTrace();
    	 }
    	
    	 m.setStart(date);

    	 
    	 lasp.add(m);
    	}
    	

    	return lasp;  
	}

	@Override
	public void Delete(Activite Activite) {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("http://localhost:44389/api/ActiviteWebapi/"+Activite.getActiviteID()); 
		//WebTarget hello = target.path("");     	
    	Response res=(Response) target.request().delete();	
    	System.out.println(res);
	}

	@Override
	public void Create(Activite f) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:44389/api/ActiviteWebApi");
		WebTarget hello =target.path("");
		
		Response response =hello.request().post(Entity.entity(f, MediaType.APPLICATION_JSON) );		
		
		String result=response.readEntity(String.class);
		System.out.println(result);	

		response.close();	
		
	}

	@Override
	public void Update(int id, Activite e) {
		Activite f = new Activite();
		f.setActiviteID(id);
		f.setTitle(e.getTitle());
		f.setAffiche(e.getAffiche());
		f.setTheme(e.getTheme());
		f.setOutils(e.getOutils());
		f.setLocation(e.getLocation());
		f.setStart(e.getStart());
		f.setAgeMax(e.getAgeMax());
		f.setAgeMin(e.getAgeMin());
		f.setDescription(e.getDescription());
		f.setUserId(e.getUserId());
		f.setClassSize(e.getClassSize());
		f.setDuration(e.getDuration());
		f.setProfessor(e.getProfessor());
		//   	 SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
//	 try {
//    
//	  f.setStart(sourceFormat.parse(e.getStart().toString()));
//	  f.setEnd(sourceFormat.parse(e.getEnd().toString()));
//	   
//	 } 
//	 catch (ParseException ex) {
//	   ex.printStackTrace();
//	 }
  		System.out.println("OK");
  		System.out.println(f.toString());
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:44389/api/ActiviteWebApi");
		WebTarget hello =target.path("");
		Response response = hello.request().put(Entity.entity(f, MediaType.APPLICATION_JSON));
		   System.out.println(response);
			response.close();	

	}
	
	
}
