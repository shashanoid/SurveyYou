import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// MCQ questions clss
public class MultipleChoice extends Question implements Serializable {

    public List<String> choices = new ArrayList<>();
    public MultipleChoice()throws IOException {}

    //Method to create new question
    public void createQuestion(){

        Scanner questionInput = new Scanner(System.in);

        try {
            System.out.println("Please enter the question prompt:");
            this.setPrompt(questionInput.nextLine());
            this.setType("MCQ");


            System.out.println("Please enter the number of choices:");
            int numChoices;

            numChoices = questionInput.nextInt();

            questionInput.nextLine();

            for (int i = 1; i <= numChoices; i++) {
                System.out.println("Enter choice #" + i + ": ");
                String choice;
                choice = questionInput.nextLine();

                this.choices.add(choice);
            }

            this.answerChoices = choices;

        }catch (Exception e){
            createQuestion();
        }
    }

    //Display MCQ question
    @Override
    public void displayQuestion() {
        System.out.println("Q) " + this.questionPrompt);
        int optionCounter = 1;

        for (String choice: this.choices) {
            System.out.println(" " + optionCounter + ")" + choice);
            optionCounter++;
        }


    }

    //Create answers method
    @Override
    public void makeAnswer(){
        try {
                System.out.println("Please enter the correct index of answer from the choices below \n");
                Integer choice;
                AnswerSheet answer = new AnswerSheet();
                int counter = 1;

                for (String answerchoice : this.choices) {
                    System.out.println(" " + counter + ") " + answerchoice);
                    counter++;
                }

                Scanner answerInput = new Scanner(System.in);
                choice = answerInput.nextInt();
                answer.setAnswer(this.choices.get(choice - 1));
                this.answers = answer;

        }catch (Exception e){
            makeAnswer();
        }


    }

    @Override
    public void editQuestion() {
        System.out.println("Do you wish to modify the prompt?");
        Scanner modifyInput = new Scanner(System.in);
        String modifyPrompt = modifyInput.nextLine();

        if (modifyPrompt.equals("yes") || modifyPrompt.equals("Yes")){
            System.out.println("\nOriginal Prompt: " + this.getPrompt() + "\n");
            System.out.println("Enter a new prompt: ");
            this.setPrompt(modifyInput.nextLine());
            System.out.println("[Question prompt modification successful] \n");

        }

        System.out.println("Do you wish to modify choices? ");
        String modifyChoices = modifyInput.nextLine();

        if (modifyChoices.equals("Yes") || modifyChoices.equals("yes")){
            System.out.println("\nOriginal Choices: ");

            Integer choiceCounter = 1;

            for (String choice: this.answerChoices){
                System.out.println(choiceCounter + ") " + choice);
                choiceCounter++;
            }

            System.out.println("Enter the index of choice you want to modify:");
            Integer answerChoice;
            Scanner modifyChoice = new Scanner(System.in);
            answerChoice = modifyChoice.nextInt();

            modifyChoice.nextLine();
            System.out.println("Enter new value: ");
            String newValue;
            newValue = modifyChoice.nextLine();

            this.answerChoices.set(answerChoice - 1, newValue);
            System.out.println("[Choice edited successfully] \n");


        }

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

            System.out.println("Enter desired index from the choices above:");
            Integer choice;
            AnswerSheet response = new AnswerSheet();

            Scanner answerInput = new Scanner(System.in);
            choice = answerInput.nextInt();
            response.setAnswer(this.choices.get(choice - 1));
            return response;

    }
}
