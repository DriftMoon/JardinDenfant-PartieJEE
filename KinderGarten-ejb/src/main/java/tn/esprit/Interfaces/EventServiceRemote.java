package tn.esprit.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Entity.Event;


@Remote
public interface EventServiceRemote {
	List<Event> GetAll();
	public void Delete(Event event);
	public void Create(Event e);
	public void Update(int id,Event e);

}
