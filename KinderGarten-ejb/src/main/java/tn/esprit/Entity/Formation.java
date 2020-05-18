package tn.esprit.Entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Formation implements Serializable {

	 
	 public int FormationID ;	 
     public String Title ;
     public String Description ;
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
     public Date Start ;
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
     public Date End ;
     public String Theme ;
     public boolean IsFullDay ;
     public int NbrMax ;
     public int Reserved ;
     public double Price ;
     public String Location ;
     public String Affiche ;
     public String UserId ;
     
     
     
	public Formation() {
		super();
	}
	public Formation(int formationID, String title, String description, Date start, Date end, String theme,
			boolean isFullDay, int nbrMax, int reserved, double price, String location, String affiche, String userId) {
		super();
		FormationID = formationID;
		Title = title;
		Description = description;
		Start = start;
		End = end;
		Theme = theme;
		IsFullDay = isFullDay;
		NbrMax = nbrMax;
		Reserved = reserved;
		Price = price;
		Location = location;
		Affiche = affiche;
		UserId = userId;
	}
	
	
	public Formation(String title, String description, Date start, Date end, String theme, boolean isFullDay,
			int nbrMax, int reserved, double price, String location, String affiche, String userId) {
		Title = title;
		Description = description;
		Start = start;
		End = end;
		Theme = theme;
		IsFullDay = isFullDay;
		NbrMax = nbrMax;
		Reserved = reserved;
		Price = price;
		Location = location;
		Affiche = affiche;
		UserId = userId;
	}
	@Override
	public String toString() {
		return "Formation [FormationID=" + FormationID + ", Title=" + Title + ", Description=" + Description
				+ ", Start=" + Start + ", End=" + End + ", Theme=" + Theme + ", IsFullDay=" + IsFullDay + ", NbrMax="
				+ NbrMax + ", Reserved=" + Reserved + ", Price=" + Price + ", Location=" + Location + ", Affiche="
				+ Affiche + ", UserId=" + UserId + "]";
	}
	public int getFormationID() {
		return FormationID;
	}
	public void setFormationID(int formationID) {
		FormationID = formationID;
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
	public Date getStart() {
		return Start;
	}
	public void setStart(Date start) {
		Start = start;
	}
	public Date getEnd() {
		return End;
	}
	public void setEnd(Date end) {
		End = end;
	}
	public String getTheme() {
		return Theme;
	}
	public void setTheme(String theme) {
		Theme = theme;
	}
	public boolean isIsFullDay() {
		return IsFullDay;
	}
	public void setIsFullDay(boolean isFullDay) {
		IsFullDay = isFullDay;
	}
	public int getNbrMax() {
		return NbrMax;
	}
	public void setNbrMax(int nbrMax) {
		NbrMax = nbrMax;
	}
	public int getReserved() {
		return Reserved;
	}
	public void setReserved(int reserved) {
		Reserved = reserved;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
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
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	
     
     
}
