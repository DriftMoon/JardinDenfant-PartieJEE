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

import tn.esprit.Entity.Formation;
import tn.esprit.Interfaces.FormationServiceRemote;

@Stateful
@LocalBean
public class FormationService  implements FormationServiceRemote{

	@Override
	public List<Formation> GetAll()  {
		
		List<Formation>  lasp = new ArrayList<Formation>();
    	Client client = ClientBuilder.newClient();
    	
    //	RestClientFactory restClientFactory = new RestClientFactory();
		WebTarget webTarget = client.target("http://localhost:44389/Formation/GetFormations"); 
    	//URL myURL = new URL("https://localhost:44389/Formation/GetFormations");
    	//WebTarget webTarget = restClientFactory.buildUnsecureWebTarget(myURL);
    	Response response = webTarget.request().get();
    	
    	String result = response.readEntity(String.class); 
    	
    	//System.out.println(result);
    	JsonReader jsonReader = Json.createReader(new StringReader(result));
    	JsonArray object =  jsonReader.readArray();
    	
    	 
    	for (int i=0;i<object.size();i++)
    	{
    	 
    		Formation m = new Formation();
    	 //String dateString;
       	 m.setFormationID(object.getJsonObject(i).getInt("FormationID")); 
    	 m.setTitle(object.getJsonObject(i).getString("Title")); 
    	 m.setAffiche(object.getJsonObject(i).getString("Affiche")); 
    	 m.setTheme(object.getJsonObject(i).getString("Theme")); 
    	 m.setLocation(object.getJsonObject(i).getString("Location"));
    	 m.setStart(DateParser.parseMsTimestampToDate(object.getJsonObject(i).getString("Start")));
    	 m.setEnd(DateParser.parseMsTimestampToDate(object.getJsonObject(i).getString("End")));
    	 m.setDescription(object.getJsonObject(i).getString("Description"));
    	 m.setNbrMax( object.getJsonObject(i).getInt("NbrMax"));
    	 m.setPrice( object.getJsonObject(i).getJsonNumber("Price").doubleValue());
    	 m.setIsFullDay(false);
    	 m.setReserved(object.getJsonObject(i).getInt("Reserved"));
    	 m.setUserId(object.getJsonObject(i).getString("UserId"));
    	// System.out.println(m.Start.toString());
    	// SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	// try {
        
    	//  System.out.println(sourceFormat.parse(m.getStart().toString()));    	   
    	// } 
    	// catch (ParseException ex) {
    	//   ex.printStackTrace();
    //	 }
    	 
    	 lasp.add(m);
    	}
    	

    	return lasp;  
	}

	@Override
	public void Delete(Formation Formation) {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("http://localhost:44389/api/Formationwebapi/"+Formation.getFormationID()); 
		//WebTarget hello = target.path("");     	
    	Response res=(Response) target.request().delete();	
    	System.out.println(res);
	}

	@Override
	public void Create(Formation f) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:44389/api/FormationPost");
		WebTarget hello =target.path("");
		
		Response response =hello.request().post(Entity.entity(f, MediaType.APPLICATION_JSON) );		
		
		String result=response.readEntity(String.class);
		System.out.println(result);	

		response.close();	
		
	}

	@Override
	public void Update(int id, Formation e) {
		Formation f = new Formation();
		f.setFormationID(id);
		f.setTitle(e.getTitle());
		f.setAffiche(e.getAffiche());
		f.setTheme(e.getTheme());
		f.setLocation(e.getLocation());
		f.setStart(e.getStart());
		f.setEnd(e.getEnd());
		f.setDescription(e.getDescription());
		f.setNbrMax(e.getNbrMax());
		f.setPrice(e.getPrice());
		f.setUserId(e.getUserId());
		
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
		WebTarget target = client.target("http://localhost:44389/api/Formationwebapi");
		WebTarget hello =target.path("");
		Response response = hello.request().put(Entity.entity(f, MediaType.APPLICATION_JSON));
		   System.out.println(response);
			response.close();	

	}

	
}
