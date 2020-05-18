package tn.esprit.Entity;

import java.io.Serializable;

/**
 * @author Hudson
 *
 */
public class Question implements Serializable  {
	
	private int QuestionID;
	private String Text;
	private String Opt1;
	private String Opt2;
//salem
	private String Opt3;
	private String Opt4;
	private int CorrectOpt;
	private int IDQuiz;
	public int getQuestionID() {
		return QuestionID;
	}
	public void setQuestionID(int QuestionID) {
		this.QuestionID = QuestionID;
	}
	public String getText() {
		return Text;
	}
	public void setText(String Text) {
		this.Text = Text;
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
	public int getIDQuiz() {
		return IDQuiz;
	}
	public void setIDQuiz(int IDQuiz) {
		this.IDQuiz = IDQuiz;
	}
	public Question(int QuestionID, String Text, String opt1, String opt2, String opt3, String opt4, int correctOpt,
			int IDQuiz) {
		this.QuestionID = QuestionID;
		this.Text = Text;
		this.Opt1 = opt1;
		this.Opt2 = opt2;
		this.Opt3 = opt3;
		this.Opt4 = opt4;
		this.CorrectOpt = correctOpt;
		this.IDQuiz = IDQuiz;
	}
	public Question() {
		// TODO Auto-generated constructor stub
	}
	public Question(String text, String opt1, String opt2, String opt3, String opt4, int correctOpt, int iDQuiz) {
		Text = text;
		Opt1 = opt1;
		Opt2 = opt2;
		Opt3 = opt3;
		Opt4 = opt4;
		CorrectOpt = correctOpt;
		IDQuiz = iDQuiz;
	}
	@Override
	public String toString() {
		return "Question [QuestionID=" + QuestionID + ", Text=" + Text + ", Opt1=" + Opt1 + ", Opt2=" + Opt2 + ", Opt3="
				+ Opt3 + ", Opt4=" + Opt4 + ", CorrectOpt=" + CorrectOpt + ", IDQuiz=" + IDQuiz + "]";
	}
	
	
	
	
	
	
}
