package tn.esprit.Interfaces;
import java.net.MalformedURLException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Entity.Question;
@Remote
public interface QuestionServiceRemote {


	List<Question> GetAll() throws MalformedURLException;
	public void Delete(Question ID);
	public void Create(Question f);
	public void Update(int id,Question f);	
}
