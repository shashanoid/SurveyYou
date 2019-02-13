import java.io.IOException;
import java.io.Serializable;
import java.util.*;

//ShortAnswer question class
public class ShortAnswer extends Essay implements Serializable {

    public ShortAnswer() throws IOException {}
    public List<String> choices = new ArrayList<String>();

    //Method to create new question
    @Override
    public void createQuestion() {
        Scanner questionInput = new Scanner(System.in);
        System.out.println("Please enter prompt for short answer: ");

        this.setPrompt(questionInput.nextLine());
        this.setType("Short");

        System.out.println("Please enter the size of short answer: ");
        this.size = questionInput.nextInt();



    }

    //Method to store answer
    @Override
    public void makeAnswer() {
        System.out.println("Please enter the correct answer in under " + this.size + "words: \n");

        String shortAnswer;
        AnswerSheet answer = new AnswerSheet();

        Scanner answerInput = new Scanner(System.in);
        shortAnswer = answerInput.nextLine();

        answer.setAnswer(shortAnswer);

        //answer.setnewAnswer(shortAnswer);
        this.answers = answer;


    }

    @Override
    public void editQuestion() {
        System.out.println("Please enter a new prompt:");
        Scanner questionInput = new Scanner(System.in);
        this.setPrompt(questionInput.nextLine());
        System.out.println("[Question successful modified]");

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
        String shortAnswer;
        AnswerSheet response = new AnswerSheet();

        Scanner answerInput = new Scanner(System.in);
        shortAnswer = answerInput.nextLine();

        response.setAnswer(shortAnswer);
        return response;
    }
}
