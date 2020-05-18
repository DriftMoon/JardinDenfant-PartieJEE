package managedBeans;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.Entity.Activite;
import tn.esprit.Interfaces.ActiviteServiceRemote;
import tn.esprit.Services.ActiviteService;
@ManagedBean
public class ActiviteBean {
	static int id ;
	public int ActiviteID ;
    public String Title ;
    public String Description ;
    public String Outils ;
    public String Theme ;
    public int AgeMin ;
    public int AgeMax;
    public int ClassSize ;
    public int Duration ;
    public String Location ;
    public String Affiche ;
    public String Document="" ;
    public Date Start ;
    public String Professor ;
    public String UserId ;
	
    private List<Activite> Activite;
    ActiviteService fs = new ActiviteService() ;
     
    
    
    public List<Activite> getActivite() {
    	Activite=fs.GetAll();
		return Activite;
	}
    
    
public String modifier(Activite e) throws IOException{
		
		this.setActiviteID(e.getActiviteID());
		this.setTitle(e.getTitle());
		this.setAffiche(e.getAffiche());
		this.setTheme(e.getTheme());
		this.setLocation(e.getLocation());
		this.setStart(e.getStart());
		this.setDescription(e.getDescription());
		this.setAgeMax(e.getAgeMax());
		this.setAgeMin(e.getAgeMin());
		this.setClassSize(e.getClassSize());
		this.setDuration(e.getDuration());
		this.setOutils(e.getOutils());
		this.setProfessor(e.getProfessor());		
		this.setUserId(e.getUserId());
		System.out.println(e.getActiviteID());
		id=e.getActiviteID();
		System.out.println(id);
		return "edit.jsf";			
	}

public String MAJEvent(){
	System.out.println(id);
	fs.Update(id, new Activite(Title, Description, Outils, Theme, AgeMin, AgeMax, ClassSize, Duration, Location, Affiche, Document, Start, Professor, UserId));
	Activite = fs.GetAll();
	id=0;
	return "Index.jsf";
}


public	String AddActivite() {
	fs.Create( new Activite(Title, Description, Outils, Theme, AgeMin, AgeMax, ClassSize, Duration, Location, Affiche, Document, Start, Professor, UserId));
	return "Index.jsf";
}

public String supprimer(Activite e){
	fs.Delete(e);
	return "Index.jsf";
	
	
}
    
    

	public int getActiviteID() {
		return ActiviteID;
	}
	public void setActiviteID(int activiteID) {
		ActiviteID = activiteID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getOutils() {
		return Outils;
	}
	public void setOutils(String outils) {
		Outils = outils;
	}

	public String getTheme() {
		return Theme;
	}
	public void setTheme(String theme) {
		Theme = theme;
	}
	public int getAgeMin() {
		return AgeMin;
	}
	public void setAgeMin(int ageMin) {
		AgeMin = ageMin;
	}
	public int getAgeMax() {
		return AgeMax;
	}
	public void setAgeMax(int ageMax) {
		AgeMax = ageMax;
	}
	public int getClassSize() {
		return ClassSize;
	}
	public void setClassSize(int classSize) {
		ClassSize = classSize;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getAffiche() {
		return Affiche;
	}
	public void setAffiche(String affiche) {
		Affiche = affiche;
	}
	public String getDocument() {
		return Document;
	}
	public void setDocument(String document) {
		Document = document;
	}
	public Date getStart() {
		return Start;
	}
	public void setStart(Date start) {
		Start = start;
	}
	public String getProfessor() {
		return Professor;
	}
	public void setProfessor(String professor) {
		Professor = professor;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}

	public void setActivite(List<Activite> activite) {
		Activite = activite;
	}
	public ActiviteService getFs() {
		return fs;
	}
	public void setFs(ActiviteService fs) {
		this.fs = fs;
	}
    
    
    
    
    


}
