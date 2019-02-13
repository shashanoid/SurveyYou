import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
    All Questions in survey and test inherit from Question class
 */

public abstract class Question implements Serializable  {

    protected String questionPrompt;
    protected String type;
    protected AnswerSheet answers;
    protected List<String> answerChoices = new ArrayList<String>();
    protected Integer points;



    public Question() throws IOException {}
    public abstract void displayQuestion();
    public abstract void editQuestion();
    public abstract void editAnswer();
    public abstract void makeAnswer();
    public abstract AnswerSheet enterAnswer();


    //Sets question
    public void setPrompt(String prompt){
        this.questionPrompt = prompt;
    }


    //Sets the type for the question
    public void setType(String type){this.type = type;}


    //Gets the type of question
    public String getType(){return this.type;}

    //Gets question Prompt
    public String getPrompt(){return this.questionPrompt;}



}
