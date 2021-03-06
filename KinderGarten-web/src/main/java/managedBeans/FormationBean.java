package managedBeans;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import tn.esprit.Entity.Formation;
import tn.esprit.Services.FormationService;
@ManagedBean
public class FormationBean {
	static int id;
	private int FormationID ;	 
	private String Title ;
	private String Description ;
	private Date Start ;
	private Date End ;
    private String Theme ;
    private boolean IsFullDay ;
    private int NbrMax ;
    private int Reserved ;
    private double Price ;
    private String Location ;
    private String Affiche ;
    private String UserId ;
	
    private List<Formation> Formation;
    
    FormationService fs= new FormationService();
    
    
    public List<Formation> getFormation() {
    	Formation=fs.GetAll();
		return Formation;
	}
    
    
public String modifier(Formation e) throws IOException{
		
		this.setFormationID(e.getFormationID());
		this.setTitle(e.getTitle());
		this.setAffiche(e.getAffiche());
		this.setTheme(e.getTheme());
		this.setLocation(e.getLocation());
		this.setStart(e.getStart());
		this.setEnd(e.getEnd());
		this.setDescription(e.getDescription());
		this.setNbrMax(e.getNbrMax());
		this.setPrice(e.getPrice());
		this.setUserId(e.getUserId());
		System.out.println(e.getFormationID());
		id=e.getFormationID();
		System.out.println(id);
		return "edit.jsf";	
	}

public String MAJEvent(){
	System.out.println(id);
	fs.Update(id,new Formation( Title, Description, Start, End, Theme, false, NbrMax, Reserved, Price, Location, Affiche, UserId) );
	Formation = fs.GetAll();
	id=0;
	return "Index.jsf";
}


public	String AddFormation() {
	
	fs.Create(new Formation( Title, Description, Start, End, Theme, false, NbrMax, Reserved, Price, Location, Affiche, UserId) );
	return "Index.jsf";
}

public String supprimer(Formation e){
	fs.Delete(e);
	return "Index.jsf";
	
	
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


	public void setFormation(List<Formation> formation) {
		Formation = formation;
	}
    
    
    
}
