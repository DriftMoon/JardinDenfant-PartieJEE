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
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Parser;

import tn.esprit.Entity.Activite;
import tn.esprit.Entity.Formation;
import tn.esprit.Entity.Question;
import tn.esprit.Entity.Quiz;
import tn.esprit.Interfaces.ActiviteServiceRemote;
import tn.esprit.Interfaces.QuizServiceRemote;

@Stateful
@LocalBean
public class QuizServices implements QuizServiceRemote{

	
	public String launchQuiz(List<Question>questions) {
		
		JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
         JSONArray arr = new JSONArray();
         JSONArray arr1 = new JSONArray();
		
        obj.put("counterFormat", "Question %current of %total");
        for(int i=0;i<questions.size();i++) {
        Question quest= questions.get(i);
        obj1.put("q", quest.getText());
        arr1.put(quest.getOpt1());
        arr1.put(quest.getOpt2());
        arr1.put(quest.getOpt3());
        arr1.put(quest.getOpt4());
        obj1.put("options", arr1);
        obj1.put("correctIndex", quest.getCorrectOpt());
        obj1.put("correctResponse", "correct!");
        obj1.put("incorrectResponse", "incorrect!");
        arr.put(obj1);
        obj1 =new JSONObject();
        arr1= new JSONArray();
        }
        obj.put("questions", arr);
        
        System.out.println(obj);         
         return(obj.toString());
		
	}

	@Override
	public List<Quiz> GetAll()  {

		List<Quiz>  lasp = new ArrayList<Quiz>();
    	Client client = ClientBuilder.newClient();
  		WebTarget webTarget = client.target("http://localhost:44389/api/QuizWebApi"); 
    	Response response = webTarget.request().get();
    	String result = response.readEntity(String.class); 
    	//System.out.println(result);
    	JsonReader jsonReader = Json.createReader(new StringReader(result));
    	JsonArray object =  jsonReader.readArray();
    	
    	for (int i=0;i<object.size();i++)
    	{
    		Quiz m = new Quiz();
    		m.setID(object.getJsonObject(i).getInt("ID"));
    		m.setTitre(object.getJsonObject(i).getString("Titre"));
    		lasp.add(m);
    	}
		
		return lasp;
	}

	@Override
	public void Delete(Quiz quiz) {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("http://localhost:44389/api/QuizWebApi/"+quiz.getID()); 
		//WebTarget hello = target.path("");     	
    	Response res=(Response) target.request().delete();	
    	System.out.println(res);
		
	}

	@Override
	public void Create(Quiz f) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:44389/api/QuizWebApi");
		WebTarget hello =target.path("");
		
		Response response =hello.request().post(Entity.entity(f, MediaType.APPLICATION_JSON) );		
		
		String result=response.readEntity(String.class);
		System.out.println(result);	

		response.close();	
		
	}

	@Override
	public void Update(int id, Quiz f) {

		Quiz q= new Quiz();
		q.setID(id);
		q.setTitre(f.getTitre());
		System.out.println("OK");
  		System.out.println(f.toString());
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:44389/api/QuizWebApi");
		WebTarget hello =target.path("");
		Response response = hello.request().put(Entity.entity(f, MediaType.APPLICATION_JSON));
		   System.out.println(response);
			response.close();	
	}
	
	
}
