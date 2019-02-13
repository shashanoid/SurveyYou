import java.io.IOException;
import java.io.Serializable;
import java.util.*;

//Driver Class for Tests
public class Test extends Survey implements Serializable {


    public Test(){}

    //Creates New Test
    public void createTest() throws IOException{
        try {
            if (this.name == null) {
                System.out.println("Enter the test name: ");
                Scanner nameInput = new Scanner(System.in);
                this.name = nameInput.nextLine();
            }

            System.out.println("\nTest: " + this.name + "\n");
            System.out.println("Please enter the number of questions for your test \n");

            Scanner numInput = new Scanner(System.in);
            int numQuestions = numInput.nextInt();

            // Loops over new question method
            for (int i = 0; i < numQuestions; i++) {
                addNewQuestion();
            }
        }catch (Exception e){
            System.out.println("Invalid Input, try again");
            createTest();
        }


    }

    //Adds new question based on choice
    @Override
    public void addNewQuestion() throws IOException {

        int questionChoice;

        String questionChoices ="\n1) Add a new T/F question \n" +
                "2) Add a new multiple choice question \n"+
                "3) Add a new short answer question \n"+
                "4) Add a new essay question \n"+
                "5) Add a new ranking question \n" +
                "6) Add a new matching question \n"+
                "7) Exit \n";

        System.out.println(questionChoices);

        Scanner questionInput = new Scanner(System.in);
        questionChoice = questionInput.nextInt();


        switch (questionChoice){
            case 1:
                TF TrueFalse = new TF();
                TrueFalse.createQuestion();
                this.Questions.add(TrueFalse);
                TrueFalse.makeAnswer();
                break;
            case 2:
                MultipleChoice multipleChoice = new MultipleChoice();
                multipleChoice.createQuestion();
                this.Questions.add(multipleChoice);
                multipleChoice.makeAnswer();
                break;

            case 3:
                ShortAnswer shortAnswer = new ShortAnswer();
                shortAnswer.createQuestion();
                this.Questions.add(shortAnswer);
                shortAnswer.makeAnswer();
                break;

            case 4:
                Essay essay = new Essay();
                essay.createQuestion();
                this.Questions.add(essay);
                essay.makeAnswer();
                break;
            case 5:
                Ranking ranking = new Ranking();
                ranking.createQuestion();
                this.Questions.add(ranking);
                ranking.makeAnswer();
                break;
            case 6:
                Matching matching = new Matching();
                matching.createQuestion();
                this.Questions.add(matching);
                matching.makeAnswer();
                break;
            case 7:
                TestSurveyClient.reset();
            default:
                System.out.println("Invalid Input");
                addNewQuestion();


        }



    }
}
