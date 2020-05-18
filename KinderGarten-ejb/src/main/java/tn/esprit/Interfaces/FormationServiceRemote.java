package tn.esprit.Interfaces;

import java.net.MalformedURLException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Entity.Formation;

@Remote
public interface FormationServiceRemote {
	
	List<Formation> GetAll() throws MalformedURLException;
	public void Delete(Formation FormationID);
	public void Create(Formation f);
	public void Update(int id,Formation f);	
	
}
