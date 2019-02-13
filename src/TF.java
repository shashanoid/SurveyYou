import java.io.IOException;
import java.io.Serializable;
import java.util.*;


//True False question class
public class TF extends MultipleChoice implements Serializable {

    public TF()throws IOException {}
    public List<String> choices = new ArrayList<String>();


    //Method to create new question
    @Override
    public void createQuestion() {
        Scanner questionInput = new Scanner(System.in);

        System.out.println("Please enter the prompt for your question");
        this.setPrompt(questionInput.nextLine());
        this.setType("TF");

        // Choices for true false question
        this.choices.add("T");
        this.choices.add("F");

        this.answerChoices = choices;

    }

    @Override
    public void displayQuestion() {
        System.out.println("Q) " + this.questionPrompt);
        int optionCounter = 1;

        for (String choice: this.choices) {
            System.out.println(" " + optionCounter + ")" + choice);
            optionCounter++;
        }
    }

    //Method to create answer
    @Override
    public void makeAnswer() {
        try {
                System.out.println("Please enter the index of correct choice: \n");

                System.out.println("1)T " + "\n" + "2)F");
                Integer choice;
                AnswerSheet answer = new AnswerSheet();

                Scanner answerInput = new Scanner(System.in);
                choice = answerInput.nextInt();

                if (choice == 1) {
                    answer.setAnswer("T");
                } else {
                    answer.setAnswer("F");
                }

                this.answers = answer;

        }catch (Exception e){
            makeAnswer();
        }
    }

    @Override
    public void editQuestion() {
        System.out.println("Please enter a new prompt for the question:");
        Scanner questionInput = new Scanner(System.in);
        this.setPrompt(questionInput.nextLine());
        System.out.println("[Question prompt has been successfully edited]");

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
        System.out.println("Enter the index of choice: \n");

        Integer choice;
        AnswerSheet response = new AnswerSheet();

        Scanner answerInput = new Scanner(System.in);
        choice = answerInput.nextInt();

        if (choice.equals(1)) {
            response.setAnswer("T");
            response.answer = "T";
        } else {
            response.setAnswer("F");
            response.answer = "F";
        }

        return response;
    }
}
