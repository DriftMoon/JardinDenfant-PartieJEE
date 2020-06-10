package managedBeans;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.Entity.Activite;
import tn.esprit.Interfaces.ActiviteServiceRemote;
import tn.esprit.Services.ActiviteService;
@ManagedBean
public class ActiviteBean {
	static int id ;
	private int ActiviteID ;
	private String Title ;
	private String Description ;
	private String Outils ;
	private String Theme ;
    private int AgeMin ;
    private int AgeMax;
    private int ClassSize ;
    private int Duration ;
    private String Location ;
    private String Affiche ;
    private String Document="" ;
    private Date Start ;
    private String Professor ;
    private String UserId ;
    private List<Activite> sortedActs;
    private List<Activite> Activite;
    ActiviteService fs = new ActiviteService() ;

    
    public List<Activite> getActivite() {
    	Activite=fs.GetAll();
		return Activite;
	}
    
public String details(Activite e) throws IOException{
		
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
		return "Details.jsf";			
	}
public String details2(Activite e) throws IOException{
	
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
	return "../Activite/Details.jsf";			
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
	if((AgeMin>AgeMax)||(AgeMax>11 && AgeMax<3)||(AgeMin>11 && AgeMin<3)) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier l'age min est l'age max", null));
		return "edit.jsf";}
	if(Duration>12 || Duration<1)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier la durée : entre 1 et 12 mois!", null));
		return "edit.jsf";}
	if(ClassSize>50 || ClassSize<5)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier la taille de la classe 5-50", null));
		return "edit.jsf";}
	fs.Update(id, new Activite(Title, Description, Outils, Theme, AgeMin, AgeMax, ClassSize, Duration, Location, Affiche, Document, Start, Professor, UserId));
	Activite = fs.GetAll();
	id=0;
	return "Index.jsf";
}


public	String AddActivite() {
	if((AgeMin>AgeMax)||(AgeMax>11 && AgeMax<3)||(AgeMin>11 && AgeMin<3)) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier l'age min est l'age max", null));
		return "Create.jsf";}
	if(Duration>12 || Duration<1)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier la durée : entre 1 et 12 mois!", null));
		return "Create.jsf";}
	if(ClassSize>50 || ClassSize<5)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		        FacesMessage.SEVERITY_ERROR, "Vérifier la taille de la classe 5-50", null));
		return "Create.jsf";}
	
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

	public List<Activite> getSortedActs() {
		return	this.getActivite().stream().sorted((o1, o2) -> o1.getStart().compareTo(o2.getStart())).limit(5)
		.collect(Collectors.toList());		
	}

	public void setSortedActs(List<Activite> sortedActs) {
		this.sortedActs = sortedActs;
	}


    


}
