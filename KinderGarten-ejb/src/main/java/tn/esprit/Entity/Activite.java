package tn.esprit.Entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Activite implements Serializable {

	public int ActiviteID;
    public String Title ;
    public String Description ;
    public String Outils ;
    //public String Type;
    public String Theme;
    public int AgeMin ;
    public int AgeMax;
    public int ClassSize ;
    public int Duration ;
    public String Location ;
    public String Affiche ;
    public String Document ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date Start ;
    public String Professor ;
    public String UserId ;
		
	
	public Activite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Activite(String title, String description, String outils, String theme, int ageMin, int ageMax,
			int classSize, int duration, String location, String affiche, String document, Date start, String professor,
			String userId) {
		Title = title;
		Description = description;
		Outils = outils;
	//	Type = type;
		Theme = theme;
		AgeMin = ageMin;
		AgeMax = ageMax;
		ClassSize = classSize;
		Duration = duration;
		Location = location;
		Affiche = affiche;
		Document = document;
		Start = start;
		Professor = professor;
		UserId = userId;
	}
	public Activite(int activiteID, String title, String description, String outils, String theme,
			int ageMin, int ageMax, int classSize, int duration, String location, String affiche, String document,
			Date start, String professor, String userId) {
		ActiviteID = activiteID;
		Title = title;
		Description = description;
		Outils = outils;
		//Type = type;
		Theme = theme;
		AgeMin = ageMin;
		AgeMax = ageMax;
		ClassSize = classSize;
		Duration = duration;
		Location = location;
		Affiche = affiche;
		Document = document;
		Start = start;
		Professor = professor;
		UserId = userId;
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
	@Override
	public String toString() {
		return "Activite [ActiviteID=" + ActiviteID + ", Title=" + Title + ", Description=" + Description + ", Outils="
				+ Outils   + ", Theme=" + Theme + ", AgeMin=" + AgeMin + ", AgeMax=" + AgeMax
				+ ", ClassSize=" + ClassSize + ", Duration=" + Duration + ", Location=" + Location + ", Affiche="
				+ Affiche + ", Document=" + Document + ", Start=" + Start + ", Professor=" + Professor + ", UserId="
				+ UserId + "]";
	}
	
    
    
    
    
}
