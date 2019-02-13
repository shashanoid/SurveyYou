import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// Ranking question class
public class Ranking extends Matching implements Serializable {

    public Ranking()throws IOException {}
    public List<String> choices = new ArrayList<String>();

    //Method to create new question
    public void createQuestion(){
        try {


            Scanner questionInput = new Scanner(System.in);

            System.out.println("Please enter the prompt for ranking question: ");
            this.setPrompt(questionInput.nextLine());
            this.setType("Ranking");

            System.out.println("Enter the number of choices:");
            int numChoices = questionInput.nextInt();

            questionInput.nextLine();

            for (int i = 1; i <= numChoices; i++) {
                System.out.println("Enter Choice #" + i + ")");
                String choice = questionInput.nextLine();
                this.choices.add(choice);
            }
        }catch (Exception e){
            createQuestion();
        }
    }

    //Method to display question
    @Override
    public void displayQuestion() {
        System.out.println(this.questionPrompt);
        int optionCounter = 1;

        for (String choice: this.choices) {
            System.out.println(optionCounter + ")" + choice);
            optionCounter++;
        }
    }

    //Method to create answer
    @Override
    public void makeAnswer() {
        System.out.println("Please enter the rankings answer (strings) in correct order: \n");
        AnswerSheet answer = new AnswerSheet();
        Scanner answerInput = new Scanner(System.in);

        for (int i = 1; i <= choices.size(); i++){
            System.out.println("Rank " + i + ")");
            answer.setAnswer(answerInput.nextLine());

        }
        this.answers = answer;

    }




    @Override
    public void editQuestion() {
        System.out.println("Do you wish to modify the prompt?");
        Scanner modifyRankingInput = new Scanner(System.in);
        String modifyRankingPrompt = modifyRankingInput.nextLine();

        if (modifyRankingPrompt.equals("Yes") || modifyRankingPrompt.equals("yes")){
            System.out.println("Original Prompt:" + this.getPrompt() + "\n");
            System.out.println("Please enter a new prompt:");
            this.setPrompt(modifyRankingInput.nextLine());
            System.out.println("[Question successfully modified]");
        }

        System.out.println("Do you wish to modify the answers?");
        Scanner modifyChoicesInput = new Scanner(System.in);
        String modifyChoies = modifyChoicesInput.nextLine();

        if (modifyChoies.equals("yes") || modifyChoies.equals("Yes")){
            this.choices.clear();

            System.out.println("Please enter the new number of choices you want");
            Integer newChoiceCount = modifyChoicesInput.nextInt();
            Integer Counter = 1;

            modifyChoicesInput.nextLine();

            for (int i = 0; i < newChoiceCount; i++){
                System.out.println("Choice #" + Counter + ")");
                String choice = modifyChoicesInput.nextLine();
                this.choices.add(choice);
                Counter++;
            }

            System.out.println("[New choices modified successfully]");


        }else{
            return;
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
        System.out.println("Please enter the rankings answer (strings) in correct order: \n");
        AnswerSheet response = new AnswerSheet();
        Scanner answerInput = new Scanner(System.in);

        for (int i = 1; i <= choices.size(); i++){
            System.out.println("Rank " + i + ")");
            response.setAnswer(answerInput.nextLine());

        }

        return response;

    }
}
