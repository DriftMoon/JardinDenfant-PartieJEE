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
import tn.esprit.Interfaces.QuestionServiceRemote;
import tn.esprit.Interfaces.QuizServiceRemote;

@Stateful
@LocalBean
public class QuestionService implements QuestionServiceRemote {

	@Override
	public List<Question> GetAll() {
		
		List<Question>  lasp = new ArrayList<Question>();
    	Client client = ClientBuilder.newClient();
  		WebTarget webTarget = client.target("https://solutionweb220200609033335.azurewebsites.net/api/QuestionWebApi"); 
    	Response response = webTarget.request().get();
    	String result = response.readEntity(String.class); 
    	//System.out.println(result);
    	JsonReader jsonReader = Json.createReader(new StringReader(result));
    	JsonArray object =  jsonReader.readArray();
    	
    	for (int i=0;i<object.size();i++)
    	{
    		Question m = new Question();
    		m.setQuestionID(object.getJsonObject(i).getInt("QuestionID"));
    		m.setText(object.getJsonObject(i).getString("Text"));
    		m.setCorrectOpt(object.getJsonObject(i).getInt("CorrectOpt"));
    		m.setOpt1(object.getJsonObject(i).getString("Opt1"));
    		m.setOpt2(object.getJsonObject(i).getString("Opt2"));
    		m.setOpt3(object.getJsonObject(i).getString("Opt3"));
    		m.setOpt4(object.getJsonObject(i).getString("Opt4"));
    		m.setIDQuiz(object.getJsonObject(i).getInt("IDQuiz"));
    		lasp.add(m);
    	}
		
		return lasp;
	}

	@Override
	public void Delete(Question q) {
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("https://solutionweb220200609033335.azurewebsites.net/api/QuestionWebApi/"+q.getQuestionID()); 
		//WebTarget hello = target.path("");     	
    	Response res=(Response) target.request().delete();	
    	System.out.println(res);		
	}

	@Override
	public void Create(Question f) {
		System.out.println(f);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://solutionweb220200609033335.azurewebsites.net/api/QuestionWebApi");
		WebTarget hello =target.path("");		
		Response response =hello.request().post(Entity.entity(f, MediaType.APPLICATION_JSON) );		
		String result=response.readEntity(String.class);
		System.out.println(result);	
		response.close();	
	}

	@Override
	public void Update(int id, Question f) {

		Question q= new Question();
		q.setQuestionID(id);
		q.setText(f.getText());
		q.setCorrectOpt(f.getCorrectOpt());
		q.setOpt1(f.getOpt1());
		q.setOpt2(f.getOpt2());
		q.setOpt3(f.getOpt3());
		q.setOpt4(f.getOpt4());
		q.setIDQuiz(f.getCorrectOpt());
		
		System.out.println("OK");
  		System.out.println(q.toString());
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://solutionweb220200609033335.azurewebsites.net/api/QuestionWebApi");
		WebTarget hello =target.path("");
		Response response = hello.request().put(Entity.entity(f, MediaType.APPLICATION_JSON));
		   System.out.println(response);
			response.close();	
	}

}
