package tn.esprit.Services;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Entity.Event;
import tn.esprit.Entity.User;
import tn.esprit.Interfaces.EventServiceRemote;

@Stateful
@LocalBean
public class EventService implements EventServiceRemote {
	public String GlobalEndPoint = "localhost:44389";
	EntityManager em;

	public List<User> GetAllUsersMail() {
		List<User> lasp = new ArrayList<User>();
		Client client = ClientBuilder.newClient();

		WebTarget web = client.target("http://" + GlobalEndPoint + "/api/UserApi");

		Response response = web.request().get();

		String result = response.readEntity(String.class);

		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {

			User m = new User();
			
			m.setEmail(object.getJsonObject(i).getString("Email"));
			
			lasp.add(m);
		}

		return lasp;

	}

	@Override
	public List<Event> GetAll() {
		List<Event> lasp = new ArrayList<Event>();
		Client client = ClientBuilder.newClient();

		WebTarget web = client.target("http://" + GlobalEndPoint + "/api/EventApi");

		Response response = web.request().get();

		String result = response.readEntity(String.class);

		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {

			Event m = new Event();
			m.setEventId(object.getJsonObject(i).getInt("EventId"));
			m.setName(object.getJsonObject(i).getString("Name"));
			m.setImage(object.getJsonObject(i).getString("Image"));
			m.setQrCode(object.getJsonObject(i).getString("qrCode"));

			m.setDescription(object.getJsonObject(i).getString("Description"));
			m.setPhone(object.getJsonObject(i).getInt("Phone"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			;
			try {
				m.setDate(format.parse(object.getJsonObject(i).getString("DateEvent")));
				m.setDeb(format.parse(object.getJsonObject(i).getString("Debut")));
				m.setFi(format.parse(object.getJsonObject(i).getString("Fin")));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			// m.setCategory(object.getJsonObject(i).getString("Category"));

			lasp.add(m);
		}

		return lasp;

	}

	public Event GetByQrCode(String qrcode) {
		List<Event> lasp = new ArrayList<Event>();
		Client client = ClientBuilder.newClient();
		System.out.println("qrcode" + qrcode);
		WebTarget web = client.target("http://" + GlobalEndPoint + "/api/EventApi/QrCode?id=" + qrcode);

		Response response = web.request().get();

		String result = response.readEntity(String.class);

		JsonReader jsonReader = Json.createReader(new StringReader(result));

		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {

			Event m = new Event();
			m.setEventId(object.getJsonObject(i).getInt("EventId"));
			m.setName(object.getJsonObject(i).getString("Name"));
			m.setImage(object.getJsonObject(i).getString("Image"));
			m.setQrCode(object.getJsonObject(i).getString("qrCode"));

			m.setDescription(object.getJsonObject(i).getString("Description"));
			m.setPhone(object.getJsonObject(i).getInt("Phone"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			;
			try {
				m.setDate(format.parse(object.getJsonObject(i).getString("DateEvent")));
				m.setDeb(format.parse(object.getJsonObject(i).getString("Debut")));
				m.setFi(format.parse(object.getJsonObject(i).getString("Fin")));

			} catch (ParseException e) {
				e.printStackTrace();
			}

			lasp.add(m);
		}

		return lasp.get(0);

	}

	@Override
	public void Delete(Event event) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/api/EventApi/Delete?id=" + event.getEventId());
		WebTarget hello = target.path("");
		Response response = hello.request(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE).delete();

		System.out.println("LOG DELETED" + response.getStatus());
		String result = response.readEntity(String.class);
		System.out.println("XXXXXXXXXXX:" + result);

		response.close();
	}

	@Override
	public void Create(Event e) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/api/EventApi/Create");
		WebTarget hello = target.path("");
		Response response = hello.request().post(Entity.entity(e, MediaType.APPLICATION_JSON));

		String result = response.readEntity(String.class);
		System.out.println(result);

		response.close();

	}

	@Override
	public void Update(int id, Event e) {
		Event km = new Event();

		km.setName(e.getName());
		km.setImage(e.getImage());
		km.setDateEvent(e.getDateEvent());
		km.setDescription(e.getDescription());
		km.setPhone(e.getPhone());
		km.setDebut(e.getDebut());
		km.setFin(e.getFin());

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/api/EventApi/Put?id=" + id);
		Response response = target.request().put(Entity.entity(e, MediaType.APPLICATION_JSON));
		System.out.println(response);
	}

}
