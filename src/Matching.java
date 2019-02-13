import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// Matching answer class
public class Matching extends Question implements Serializable {


    public Matching() throws IOException {}
    public List<String> choices = new ArrayList<String>();

    //Method to create new question
    public void createQuestion(){
        Scanner questionInput = new Scanner(System.in);

        System.out.println("Please enter the prompt for your matching question:");
        this.setPrompt(questionInput.nextLine());
        this.setType("Matching");


        System.out.println("Enter the number of Choice/Pair you want: ");
        int numChoices = questionInput.nextInt();
        questionInput.nextLine();

        //Stores matching pairs one after another
        for (int i = 1; i <= numChoices; i++){

            System.out.println("Enter Choice #" + i + ")");
            String key = questionInput.nextLine();
            this.choices.add(key);
            System.out.println("Enter the matching pair: ");
            String pair = questionInput.nextLine();
            this.choices.add(pair);
        }

    }

    //Displays Question
    @Override
    public void displayQuestion() {
        System.out.println(this.questionPrompt);
        int optionCounter = 0;

        // Every second element makes one pair, therefore iterate over every second element
        for (int i = 1; i <= choices.size() / 2; i++){
            System.out.println(" " + i + ")" + choices.get(optionCounter) + "\t" + choices.get(optionCounter + 1));
            optionCounter = optionCounter + 2;
        }
    }

    //Method to store answer
    @Override
    public void makeAnswer(){
        Scanner answerInput = new Scanner(System.in);
        AnswerSheet answer = new AnswerSheet();

        System.out.println("Please enter the correct answer for the following rows.");

        String newAnswer;
        int counter = 1;
        int choiceCounter = 0;

        for (int i = 0; i < choices.size() / 2; i++){
            System.out.println(counter + ")" + choices.get(choiceCounter));
            newAnswer = answerInput.nextLine();
            answer.setAnswer(newAnswer);
            counter++;
            choiceCounter = choiceCounter + 2;
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

        System.out.println("Do you wish to modify the answer?");
        Scanner modifyAnswerInput = new Scanner(System.in);
        String modifyAnswer = modifyAnswerInput.nextLine();

        if (modifyAnswer.equals("yes") || modifyAnswer.equals("Yes")){
            this.choices.clear();


            System.out.println("Enter the number of Choice/Pair you want: ");
            int numChoices = modifyAnswerInput.nextInt();
            modifyAnswerInput.nextLine();

            //Stores matching pairs one after another
            for (int i = 1; i <= numChoices; i++){

                System.out.println("Enter Choice #" + i + ")");
                String key = modifyAnswerInput.nextLine();
                this.choices.add(key);
                System.out.println("Enter the matching pair: ");
                String pair = modifyAnswerInput.nextLine();
                this.choices.add(pair);
            }

            System.out.println("\n[Answers modified successfully]");



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
        Scanner answerInput = new Scanner(System.in);
        AnswerSheet response = new AnswerSheet();

        System.out.println("Enter choices for the following rows:");

        String newAnswer;
        int counter = 1;
        int choiceCounter = 0;

        for (int i = 0; i < choices.size() / 2; i++){
            System.out.println(counter + ")" + choices.get(choiceCounter));
            newAnswer = answerInput.nextLine();
            response.setAnswer(newAnswer);
            counter++;
            choiceCounter = choiceCounter + 2;
        }

        return response;
    }
}
