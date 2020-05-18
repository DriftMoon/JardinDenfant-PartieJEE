package tn.esprit.Interfaces;
import java.net.MalformedURLException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Entity.Quiz;
@Remote
public interface QuizServiceRemote {

	List<Quiz> GetAll() throws MalformedURLException;
	public void Delete(Quiz ID);
	public void Create(Quiz f);
	public void Update(int id,Quiz f);	
	
}
