package tn.esprit.Entity;

import java.io.Serializable;
import java.util.Date;

public class Event  implements Serializable{
	 private int EventId ;
     private String Name ;
     private Category Category ;
     private int Phone ;
     private String DateEvent ;
     private Date date;
     private String Debut ;
     private Date deb;
     private String Fin ;
     private Date fi;

     private String Description ;
     private String Image ;
     private String qrCode;
 	private static final long serialVersionUID = 1L;

	public int getEventId() {
		return EventId;
	}
	public void setEventId(int eventId) {
		EventId = eventId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Category getCategory() {
		return Category;
	}
	public void setCategory(Category category) {
		Category = category;
	}
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	
	public String getDateEvent() {
		return DateEvent;
	}
	public void setDateEvent(String dateEvent) {
		DateEvent = dateEvent;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDebut() {
		return Debut;
	}
	public void setDebut(String debut) {
		Debut = debut;
	}
	public Date getDeb() {
		return deb;
	}
	public void setDeb(Date deb) {
		this.deb = deb;
	}
	public String getFin() {
		return Fin;
	}
	public void setFin(String fin) {
		Fin = fin;
	}
	public Date getFi() {
		return fi;
	}
	public void setFi(Date fi) {
		this.fi = fi;
	}
	
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	@Override
	public String toString() {
		return "Event [EventId=" + EventId + ", Name=" + Name + ", Category=" + Category + ", Phone=" + Phone
				+ ", DateEvent=" + DateEvent + ", Debut=" + Debut + ", Fin=" + Fin + ", Description=" + Description
				+ ", Image=" + Image + "]";
	}
	
	
	
	public Event() {
		super();
	}
	
	public Event(int eventId, String name, tn.esprit.Entity.Category category, int phone, String dateEvent,
			String debut, String fin, String description, String image) {
		super();
		EventId = eventId;
		Name = name;
		Category = category;
		Phone = phone;
		DateEvent = dateEvent;
		Debut = debut;
		Fin = fin;
		Description = description;
		Image = image;
	}
	public Event(String name, tn.esprit.Entity.Category category, int phone, String dateEvent, String debut, String fin,
			String description, String image) {
		super();
		Name = name;
		Category = category;
		Phone = phone;
		DateEvent = dateEvent;
		Debut = debut;
		Fin = fin;
		Description = description;
		Image = image;
	}
	public Event(String name, tn.esprit.Entity.Category category, int phone, String dateEvent, String debut, String fin,
			String description, String image, String qrCode) {
		super();
		Name = name;
		Category = category;
		Phone = phone;
		DateEvent = dateEvent;
		Debut = debut;
		Fin = fin;
		Description = description;
		Image = image;
		this.qrCode = qrCode;
	}
	
    

}
