package tn.esprit.Interfaces;

import java.net.MalformedURLException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Entity.Activite;
@Remote
public interface ActiviteServiceRemote {

	List<Activite> GetAll() throws MalformedURLException;
	public void Delete(Activite ActiviteID);
	public void Create(Activite f);
	public void Update(int id,Activite f);	
	
}
