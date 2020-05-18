package tn.esprit.Interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Entity.Formation;

@Local
public interface FormationServiceLocal {

	List<Formation> GetAll();
	public void Delete(Formation FormationID);
	public void Create(Formation f);
	public void Update(int id,Formation f);	
}
