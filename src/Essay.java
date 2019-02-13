import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// Essay inheriting Question
public class Essay extends Question implements Serializable {

    // Size of essay
    protected Integer size;
    public List<String> choices = new ArrayList<String>();


    // Empty constructor
    public Essay()throws IOException {}


    // New Question method
    public void createQuestion(){
        Scanner questionInput = new Scanner(System.in);
        System.out.println("Please enter the prompt for Essay Question");
        this.setPrompt(questionInput.nextLine());
        this.setType("Essay");


        System.out.println("Please enter the length of Essay Answer: ");
        this.size = questionInput.nextInt();


    }


    // Overriding display method
    @Override
    public void displayQuestion() {
        System.out.println(this.questionPrompt);
        System.out.println("Note: Limit your answer to " + this.size + " words");

    }

    // Stores answer for the essay
    @Override
    public void makeAnswer() {
        System.out.println("Please enter the Essay answer: \n");

        String Essay;
        AnswerSheet answer = new AnswerSheet();

        Scanner answerInput = new Scanner(System.in);
        Essay = answerInput.nextLine();

        answer.setAnswer(Essay);
        //answer.setnewAnswer(Essay);

        this.answers = answer;


    }


    @Override
    public void editQuestion() {
        System.out.println("Please enter a new prompt");
        Scanner questionInput = new Scanner(System.in);
        this.setPrompt(questionInput.nextLine());
        System.out.println("[Question modified successfully]");
    }

    @Override
    public void editAnswer() {
        System.out.println("Do you wish to edit the answers?");

        Scanner editInput = new Scanner(System.in);
        String editAnswer = editInput.nextLine();


        if (editAnswer.equals("yes") || editAnswer.equals("Yes")){
            this.makeAnswer();
            System.out.println("[Answer modified successfully]");
        }else{
            return;
        }
    }




    @Override
    public AnswerSheet enterAnswer() {
        String Essay;
        AnswerSheet answer = new AnswerSheet();
        AnswerSheet response = new AnswerSheet();

        Scanner answerInput = new Scanner(System.in);
        Essay = answerInput.nextLine();


        response.setAnswer(Essay);
        return response;
    }


}
