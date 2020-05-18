package tn.esprit.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable  {
	
	private int ID;
	private String Titre;
//	List<Question> questions;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public Quiz(int iD, String titre) {
		ID = iD;
		Titre = titre;
	//	this.questions= new ArrayList<Question>();
	}
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Quiz(String titre) {
		Titre = titre;
	}
	@Override
	public String toString() {
		return "Quiz [ID=" + ID + ", Titre=" + Titre + "]";
	}
		

}
