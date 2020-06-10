package managedBeans;

import tn.esprit.Entity.Quiz;
import tn.esprit.Interfaces.QuestionServiceRemote;
import tn.esprit.Services.QuestionService;
import tn.esprit.Services.QuizServices;
import tn.esprit.Entity.Activite;
import tn.esprit.Entity.Formation;
import tn.esprit.Entity.Question;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class QuizBean {
	static int qid;
	static int qqid;
	private int ID;
	private String Titre;
	private List<Question> questions;
	private int QuestionID;
	private String Text;
	private String Opt1;
	private String Opt2;
	private String Opt3;
	private String Opt4;
	private int CorrectOpt;
	private String QuizJson;
	Quiz quiz;
	private List<Quiz> quizz;
	Question question=new Question();
	QuizServices qs = new QuizServices();
	QuestionService qqs= new QuestionService();

	//test methode tasna3 lquiz 
	public	String test(Quiz e) {
		this.ID=e.getID();
		if(qqs.GetAll().stream().filter(q->q.getIDQuiz()==e.getID()).collect(Collectors.toList()).size()<2)
		return modifier(e);
		this.Titre=e.getTitre();
		System.out.println("test: "+this.getID()+this.getTitre());
		this.setQuizJson(qs.launchQuiz(qqs.GetAll().stream().filter(q->q.getIDQuiz()==e.getID()).collect(Collectors.toList())));
		System.out.println("test:"+this.getQuizJson());
		return "Quiz.jsf";
	}
	public	String play(Quiz e) {
		this.ID=e.getID();
		if(qqs.GetAll().stream().filter(q->q.getIDQuiz()==e.getID()).collect(Collectors.toList()).size()<2)
		return "Error.jsf";
		this.Titre=e.getTitre();
		System.out.println("test: "+this.getID()+this.getTitre());
		this.setQuizJson(qs.launchQuiz(qqs.GetAll().stream().filter(q->q.getIDQuiz()==e.getID()).collect(Collectors.toList())));
		System.out.println("test:"+this.getQuizJson());
		return "Quiz.jsf";
	}
	
	//
	public	String AddQuiz() {
		System.out.println("minaaaaaaa:"+this.getID()+this.getTitre());
		qs.Create(new Quiz( Titre));
		
		return "Index.jsf";		
	}
		
	
	public String supprimer(Quiz e){		
		qs.Delete(e);
		return "Index.jsf";
			
	}
	
	public String modifier(Quiz e){
		System.out.println("minaaa "+e);
		this.ID=e.getID();
		this.Titre=e.getTitre();
		qid=e.getID();
		System.out.println("qid "+qid);		
		return "edit.jsf";			
	}
	
	public String MAJEvent()  {
		System.out.println(this.ID);
		qs.Update(ID,new Quiz(ID, Titre));
		quizz = qs.GetAll();
		return "Index.jsf";
	}
	
	public String  addQuestion()  {
		System.out.println("qid:"+ qid);
		System.out.println("text"+Text+" : "+this.getText());
		
		if(CorrectOpt>4 || CorrectOpt<1)
		return "edit.jsf";		
		CorrectOpt --;
		qqs.Create(new Question(Text, Opt1, Opt2, Opt3, Opt4, CorrectOpt, qid));
		//questions.add(new Question(QuestionID, Text, Opt1, Opt2, Opt3, Opt4, CorrectOpt, this.getID()));
		questions = qqs.GetAll();
		System.out.println("size:"+questions.size());
		qid=0;
		return "Index.jsf";
	}	
	    				
	
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
	public List<Question> getQuestions() {
		questions = qqs.GetAll();
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public int getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(int questionID) {
		QuestionID = questionID;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getOpt1() {
		return Opt1;
	}
	public void setOpt1(String opt1) {
		Opt1 = opt1;
	}
	public String getOpt2() {
		return Opt2;
	}
	public void setOpt2(String opt2) {
		Opt2 = opt2;
	}
	public String getOpt3() {
		return Opt3;
	}
	public void setOpt3(String opt3) {
		Opt3 = opt3;
	}
	public String getOpt4() {
		return Opt4;
	}
	public void setOpt4(String opt4) {
		Opt4 = opt4;
	}
	public int getCorrectOpt() {
		return CorrectOpt;
	}
	public void setCorrectOpt(int correctOpt) {
		CorrectOpt = correctOpt;
	}

	public List<Quiz> getQuizz() {
		quizz = qs.GetAll();
		return quizz;
	}


	public void setQuizz(List<Quiz> quizz) {
		this.quizz = quizz;
	}

	public String getQuizJson() {
		return QuizJson;
	}

	public void setQuizJson(String quizJson) {
		QuizJson = quizJson;
	}
	
	

	
}
