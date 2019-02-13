import java.io.Serializable;
import java.util.*;

// Answer Class
public class AnswerSheet implements Serializable {

    //Stores answers in a list -- MCQ compatible

    public List<String> answers = new ArrayList<String>();
    public String answer;


    public AnswerSheet(){}

    //Get answers
    public List<String> getAnswers(){
        return this.answers;
    }

    //Set a new answer for a question
    public void setAnswer(String answer){
        this.answers.add(answer);
    }

    //Display answer
    public void display(){
        for (String answer : answers){
            System.out.println(answer);
        }
    }

    public void setnewAnswer(String answer){
        this.answer = answer;
    }

    public void getnewAnswer(){
        System.out.println(this.answer);
    }

    public List<String> getAnswer(){
        return this.answers;
    }


}
