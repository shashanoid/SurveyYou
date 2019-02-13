import java.io.IOException;
import java.io.Serializable;
import java.util.*;


//Driver class for Surveys
public class Survey implements Serializable {

    //Name and List of questions
    public String name;
    protected ArrayList<Question> Questions = new ArrayList<Question>();


    public Survey(){ }

    //Creates new survey
    public void createSurvey() throws IOException{
        try {


            if (this.name == null) {
                System.out.println("Enter the Survey name: ");
                Scanner nameInput = new Scanner(System.in);
                this.name = nameInput.nextLine();
            }

            System.out.println("\nSurvey: " + this.name + "\n");
            System.out.println("Please enter the number of questions for your survey \n");

            Scanner numInput = new Scanner(System.in);
            int numQuestions = numInput.nextInt();

            //Loops over new questions
            for (int i = 0; i < numQuestions; i++) {
                addNewQuestion();
            }
        } catch (Exception e){
            System.out.println("Invalid Input, try again");
            createSurvey();
        }
    }


    //Adds new question
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
                break;

            case 2:
                MultipleChoice multipleChoice = new MultipleChoice();
                multipleChoice.createQuestion();
                this.Questions.add(multipleChoice);
                break;

            case 3:
                ShortAnswer shortAnswer = new ShortAnswer();
                shortAnswer.createQuestion();
                this.Questions.add(shortAnswer);
                break;
                //Display a Survey

            case 4:
                Essay essay = new Essay();
                essay.createQuestion();
                this.Questions.add(essay);
                break;
                //Display test

            case 5:
                Ranking ranking = new Ranking();
                ranking.createQuestion();
                this.Questions.add(ranking);
                break;
                //Load Survey

            case 6:
                Matching matching = new Matching();
                matching.createQuestion();
                this.Questions.add(matching);
                break;
                //Load Test

            case 7:
                TestSurveyClient.reset();
                //Exit survey
            default:
                System.out.println("Invalid Input");
                addNewQuestion();


        }

    }



}
