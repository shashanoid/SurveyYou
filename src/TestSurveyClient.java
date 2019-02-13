// Author -- Shashwat Singh
// Test Survey Creator
// November 4th, 2018


import java.util.*;
import java.io.*;

// Main Driver Class
public class TestSurveyClient implements Serializable{

    // Arrays to store completed Surveys and Tests
    public static ArrayList <Survey> storedSurveys = new ArrayList<Survey>();
    public static ArrayList <Test> storedTests = new ArrayList<Test>();

    public static ArrayList <Survey> storedTakenSurveys = new ArrayList<>();
    public static ArrayList <Test> storedTakenTests = new ArrayList<>();
    public static Integer SurveyCounter = 0;
    public static Integer testCounter = 0;

    protected static ArrayList<ArrayList<AnswerSheet>> allTakenSurveys = new ArrayList<ArrayList<AnswerSheet>>();

    protected static ArrayList<ArrayList<AnswerSheet>> allTakenTests = new ArrayList<ArrayList<AnswerSheet>>();





    // Main Menu
    public static void main(String[] args) throws IOException {
        Scanner option_input = new Scanner(System.in);

        String menu = "Main Menu: \n" +
                "1) Create a new Survey \n" +
                "2) Create a new Test \n" +
                "3) Display a Survey \n" +
                "4) Display a Test \n" +
                "5) Load a Survey \n" +
                "6) Load a Test \n" +
                "7) Save a Survey \n" +
                "8) Save a Test \n" +
                "9) Modify an Existing Survey \n" +
                "10) Modify an Existing Test\n"+
                "11) Take a Survey\n"+
                "12) Take a Test\n"+
                "13) Grade a Test\n"+
                "14) Tabulate a Survey\n"+
                "15) Tabulate a Test\n"+
                "16) Quit \n";

        System.out.println(menu);

        try {
        System.out.println("Please select the index from the options above: ");
        int option = option_input.nextInt();


            switch (option) {
                case 1:
                    createSurvey();
                    break;
                //Create new survey
                case 2:
                    createTest();
                    break;
                // Create new Test
                case 3:
                    displaySurvey();
                    break;
                // Display Survey
                case 4:
                    displayTest();
                    break;
                // Display test
                case 5:
                    loadSurvey();
                    break;
                // Load Survey from file
                case 6:
                    loadTest();
                    break;
                // Load test from file
                case 7:
                    saveSurvey();
                    break;
                //Save Survey
                case 8:
                    saveTest();
                    break;
                //Save Test
                case 9:
                    modifySurvey();
                    break;
                case 10:
                    modifyTest();
                    break;
                case 11:
                    takeSurvey();
                    break;
                case 12:
                    takeTest();
                    break;
                case 13:
                    gradeTest();
                    break;
                case 14:
                    tabulateSurvey();
                    break;
                case 15:
                    tabulateTest();
                    break;
                case 16:
                    System.exit(0);
                default:
                    System.out.println("Invalid Input, try again \n");
                    reset();

            }


        }catch (Exception e){
            System.out.println("Bad input, please enter the index of the option you want to choose. \n");
            reset();
        }
    }

    // Resets, displays main menu
    public static void reset() throws IOException{
        String[] arguments = new String[] {""};
        main(arguments);
    }

    // Creates a new survey
    public static void createSurvey() throws IOException{
        Survey newSurvey = new Survey();
        newSurvey.createSurvey();
        storedSurveys.add(newSurvey);
        System.out.println("Survey Created !! \n");
        reset();
    }

    // Display a Survey from stored surveys array
    public static void displaySurvey() throws IOException{
        if (storedSurveys.size() == 0){
            System.out.println("There are no stored surveys. \n");
            reset();
        }else {
            try {
                int count = 1;
                System.out.println("\n");
                for (Survey survey : storedSurveys) {
                    System.out.println(count + ")" + survey.name);
                    count++;
                }

                Scanner optionInput = new Scanner(System.in);

                System.out.println("Enter the index of survey you want to display: ");
                int option = optionInput.nextInt();

                Survey selectedSurvey = storedSurveys.get(option - 1);

                System.out.println("#################### \n");

                System.out.println("\n #### Survey: " + selectedSurvey.name + " ####" + "\n");


                int optionCounter = 1;
                for (Question question : selectedSurvey.Questions) {
                    System.out.println(optionCounter + ")");
                    question.displayQuestion();

                    System.out.println("\n");
                    optionCounter++;
                }

                System.out.println("#################### \n");

                reset();
            }catch (Exception e){
                displaySurvey();
            }

        }
    }


    // Creates a new test
    public static void createTest() throws IOException{
        Test newTest = new Test();
        newTest.createTest();
        storedTests.add(newTest);
        System.out.println("Test Created !! \n");
        reset();
    }


    //Display a test from stored tests array
    public static void displayTest() throws IOException{
        if (storedTests.size() == 0){
            System.out.println("There are no stored tests. \n");
            reset();
        }else {

            int count = 1;
            System.out.println("\n");
            for (Survey test: storedTests){
                System.out.println(count + ")" + test.name);
                count++;
            }

            Scanner optionInput = new Scanner(System.in);

            System.out.println("Enter the index test you want to display: ");
            int option = optionInput.nextInt();

            Survey selectedTest = storedTests.get(option-1);
            System.out.println("#################### \n");

            System.out.println("\n #### Test: " + selectedTest.name + " ####" + "\n");


            int optionCounter = 1;
            for (Question question: selectedTest.Questions){
                System.out.println(optionCounter + ")");
                question.displayQuestion();
                System.out.println("\nCorrect answer is:");
                question.answers.display();
                System.out.println("\n");
                optionCounter++;

            }

            System.out.println("#################### \n");

            reset();


        }
    }


    // Saves survey inside ./Survey folder as <name>.ser file

    public static void saveSurvey() throws IOException{

        if (storedSurveys.size() == 0){
            System.out.println("No surveys found, please create one. \n");
            reset();
        }else {
            try {
                System.out.println("Please select the survey you want to save: ");
                int counter = 1;
                for (Survey survey : storedSurveys) {
                    System.out.println(counter + ")" + survey.name);
                    counter++;

                }

                Scanner optionInput = new Scanner(System.in);
                int option = optionInput.nextInt();

                Survey selectedSurvey = storedSurveys.get(option - 1);

                FileOutputStream file = new FileOutputStream("../Survey/" + selectedSurvey.name + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(selectedSurvey);

                out.close();
                file.close();

                System.out.println("Survey has been saved \n");

                reset();
            }catch (Exception e){
                System.out.println("Saving failed, please try again \n");
                reset();
            }
        }
    }


    // Saves Test inside ./Test folder under <name>.ser file
    public static void saveTest() throws IOException{
        if (storedTests.size() == 0){
            System.out.println("No tests found, please create one \n");
            reset();
        }else {
            try {
                System.out.println("Please select the survey you want to save: \n");
                int counter = 1;
                for (Survey test : storedTests) {
                    System.out.println(counter + ")" + test.name);
                    counter++;

                }

                Scanner optionInput = new Scanner(System.in);
                int option = optionInput.nextInt();

                Survey selectedTest = storedTests.get(option - 1);

                FileOutputStream file = new FileOutputStream("../Test/" + selectedTest.name + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(selectedTest);


                out.close();
                file.close();

                System.out.println("Test has been saved \n");

                reset();
            }catch (Exception e){
                System.out.println("Failed to save test, please try again.");
                reset();
            }
        }

    }


    // Loads Survey from a .ser file placed inside ./Upload folder
    public static void loadSurvey() throws IOException{
        System.out.println("Please enter the name of the survey file you want to load from /Upload folder");
        String fileName;

        Scanner fileInput = new Scanner(System.in);
        fileName = fileInput.nextLine();

        try {


            FileInputStream fis = new FileInputStream("../Upload/" + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Survey loadedSurvey = (Survey) ois.readObject();

            storedSurveys.add(loadedSurvey);
            System.out.println("Survey Successfully Loaded \n");
            reset();

        }catch (Exception e){
            System.out.println("Upload Unsuccessful: Try Again \n");
            reset();
        }
    }


    // Loads Test from a .ser file placed inside ./Upload folder
    public static void loadTest() throws IOException{
        System.out.println("Please enter the name of the test file you want to load from /Upload folder");
        String fileName;

        Scanner fileInput = new Scanner(System.in);
        fileName = fileInput.nextLine();

        try {


            FileInputStream fis = new FileInputStream("../Upload/" + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Test loadedTest = (Test) ois.readObject();

            storedTests.add(loadedTest);
            System.out.println("Test Successfully Loaded \n");
            reset();

        }catch (Exception e){
            System.out.println("Upload Unsuccessful: Try Again \n");
            reset();
        }
    }


    //Modify a survey based on the given choices
    public static void modifySurvey() throws IOException{
        System.out.println("Stored surveys: \n");
        int counter = 1;

        HashMap<String, Survey> surveyMap = new HashMap<String, Survey>();
        HashMap<Integer, Question> questionMap = new HashMap<Integer, Question>();

        if (storedSurveys.size() > 0) {
            for (Survey survey : storedSurveys) {
                System.out.println(counter + ") " + survey.name);
                surveyMap.put(survey.name, survey);
                counter++;
            }

                // Getting the survey
                Scanner surveyInput = new Scanner(System.in);
                String surveyName;

                System.out.println("Enter the name of the survey you wish to modify or 'back' for main menu: \n");
                surveyName = surveyInput.nextLine();

                if (surveyName.equals("back")){
                    reset();
                }


                    Survey surveyToEdit = surveyMap.get(surveyName);


                    System.out.println("#########\nEnter the index of the question you want to modify.");

                    int questionCounter = 1;

                    for (Question question : surveyToEdit.Questions) {
                        System.out.println(questionCounter + ") " + question.questionPrompt + "\n");
                        questionMap.put(questionCounter, question);
                        questionCounter++;
                    }

                    //Editing the question
                    Scanner questionInput = new Scanner(System.in);
                    Integer questionIndex = questionInput.nextInt();
                    Question questionToEdit = questionMap.get(questionIndex);

                    String questionType = questionToEdit.getType();


                switch (questionType) {
                    case "TF":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;

                    case "Short":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;

                    case "Essay":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;

                    case "MCQ":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;

                    case "Ranking":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;
                    case "Matching":
                        questionToEdit.editQuestion();
                        modifySurvey();
                        break;

                        default:
                            System.out.println("Some error occoured");
                            modifySurvey();
                            break;

                }

        }else{
            System.out.println("No surveys found ! Please create one. \n");
            reset();
        }


    }



    //Modify a test based upon user selection
    public static void modifyTest() throws IOException {
        System.out.println("Stored Tests: \n");
        int counter = 1;

        HashMap<String, Survey> testMap = new HashMap<String, Survey>();
        HashMap<Integer, Question> questionMap = new HashMap<Integer, Question>();

        if (storedTests.size() > 0) {
            for (Survey test : storedTests) {
                System.out.println(counter + ") " + test.name);
                testMap.put(test.name, test);
                counter++;
            }


            // Getting the test
            Scanner testInput = new Scanner(System.in);
            String testName;

            System.out.println("Enter the name of the test you wish to modify or 'back' for main menu: \n");
            testName = testInput.nextLine();


            if (testName.equals("back")) {
                reset();
            }


            Survey testToEdit = testMap.get(testName);


            System.out.println("#########\nEnter the index of the question you want to modify.");

            int questionCounter = 1;

            for (Question question : testToEdit.Questions) {
                System.out.println(questionCounter + ") " + question.questionPrompt + "\n");
                questionMap.put(questionCounter, question);
                questionCounter++;
            }

            //Editing the question
            Scanner questionInput = new Scanner(System.in);
            Integer questionIndex = questionInput.nextInt();
            Question questionToEdit = questionMap.get(questionIndex);

            String questionType = questionToEdit.getType();


            switch (questionType) {
                case "TF":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;

                case "Short":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;

                case "Essay":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;

                case "MCQ":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;

                case "Ranking":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;
                case "Matching":
                    questionToEdit.editQuestion();
                    questionToEdit.editAnswer();
                    modifyTest();
                    break;

                default:
                    System.out.println("Some error occoured");
                    modifyTest();
                    break;
            }

        } else {
            System.out.println("No surveys found ! Please create one. \n");
            reset();
        }

    }

    // Take a survey -- Add store to file method
    public static void takeSurvey() throws IOException {

        System.out.println("Stored surveys: \n");
        int counter = 1;

        HashMap<String, Survey> surveyMap = new HashMap<String, Survey>();
        ArrayList<AnswerSheet> userAnswer = new ArrayList<AnswerSheet>();
        AnswerSheet response;


        if (storedSurveys.size() > 0) {
            for (Survey survey : storedSurveys) {
                System.out.println(counter + ") " + survey.name);
                surveyMap.put(survey.name, survey);
                counter++;
            }


            // Getting the survey
            Scanner surveyInput = new Scanner(System.in);
            String surveyName;

            System.out.println("Enter the name of the survey you wish to take or 'back' for main menu: \n");
            surveyName = surveyInput.nextLine();

            if (surveyName.equals("back")) {
                reset();
            }


            Survey getSurvey = surveyMap.get(surveyName);


            Survey newSurvey = new Survey();
            newSurvey.name = getSurvey.name;
            newSurvey.Questions = getSurvey.Questions;



            System.out.println("#################### \n");

            System.out.println("\n #### Survey: " + newSurvey.name + " ####" + "\n");

            int optionCounter = 1;


            for (Question question : newSurvey.Questions) {
                System.out.println(optionCounter + ")");
                question.displayQuestion();
                response = question.enterAnswer();
                userAnswer.add(response);
                System.out.println("\n");
                optionCounter++;

            }

            allTakenSurveys.add(userAnswer);

            System.out.println("#################### \n");
            storedTakenSurveys.add(SurveyCounter, newSurvey);
            SurveyCounter = SurveyCounter + 1;

            try {


                Survey selectedSurvey = newSurvey;

                FileOutputStream file = new FileOutputStream("../TakenSurveys/" + selectedSurvey.name + SurveyCounter + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(selectedSurvey);

                out.close();
                file.close();

                System.out.println("Survey Taken and has been saved under dir 'TakenSurveys' \n");
            }catch (Exception e){
                System.out.println("Error saving the survey, try again");
                takeSurvey();
            }


            reset();


        }else{
            System.out.println("No surveys found, please create one.\n");
            reset();
        }

    }


    //Take a stored test
    public static void takeTest() throws IOException {
        System.out.println("Stored tests: \n");
        int counter = 1;

        HashMap<String, Test> testMap = new HashMap<String, Test>();
        ArrayList<AnswerSheet> userAnswer = new ArrayList<AnswerSheet>();
        AnswerSheet response;


        if (storedTests.size() > 0) {
            for (Test test : storedTests) {
                System.out.println(counter + ") " + test.name);
                testMap.put(test.name, test);
                counter++;
            }


            // Getting the survey
            Scanner testInput = new Scanner(System.in);
            String testName;

            System.out.println("Enter the name of the test you wish to take or 'back' for main menu: \n");
            testName = testInput.nextLine();

            if (testName.equals("back")) {
                reset();
            }


            Test getTest = testMap.get(testName);


            Test newTest = new Test();
            newTest.name = getTest.name;
            newTest.Questions = getTest.Questions;



            System.out.println("#################### \n");

            System.out.println("\n #### Test: " + newTest.name + " ####" + "\n");

            int optionCounter = 1;


            for (Question question : newTest.Questions) {
                System.out.println(optionCounter + ")");
                question.displayQuestion();
                response = question.enterAnswer();
                userAnswer.add(response);
                System.out.println("\n");
                optionCounter++;

            }

            allTakenTests.add(userAnswer);

            System.out.println("#################### \n");
            storedTakenTests.add(testCounter, newTest);
            testCounter = testCounter + 1;


            try {

                Test selectedTest = newTest;

                FileOutputStream file = new FileOutputStream("../TakenTests/" + selectedTest.name + testCounter + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(selectedTest);

                out.close();
                file.close();

                System.out.println("Test taken and saved successfully !\n");
            }catch (Exception e){
                System.out.println("Error saving the test, try again");
                takeSurvey();
            }



            reset();


        }else{
            System.out.println("No stored tests found, please create one");
            reset();
        }


    }


    //Grades a test upon selection
    public static void gradeTest() throws IOException {
        System.out.println("Stored tests: \n");
        int counter = 1;

        HashMap<String, Test> testMap = new HashMap<String, Test>();

        if (storedTakenTests.size() > 0) {
            for (Test test : storedTakenTests) {
                System.out.println(counter + ") " + test.name);
                testMap.put(test.name, test);
                counter++;
            }


            // Getting the survey
            Scanner testInput = new Scanner(System.in);
            String testName;

            System.out.println("Enter the name of the test you wish to grade or 'back' for main menu: \n");
            testName = testInput.nextLine();

            if (testName.equals("back")) {
                reset();
            }


            Test getTest = testMap.get(testName);
            Test TestToGrade = getTest;


            int total = 0;
            int pointsRecieved = 0;

            for (Question question : TestToGrade.Questions) {
                total = total + 10;
                pointsRecieved = pointsRecieved + question.points;
            }

            System.out.println("\nGrade:" + pointsRecieved + "/" + total + "\n");

            reset();


        }else{
            System.out.println("No tests were taken, please take one in order to grade !");
            reset();
        }

    }


    //Tabulates the stored surveys
    public static void tabulateSurvey() throws IOException{

        System.out.println("Stored Surveys: \n");
        int counter = 1;

        HashMap<String, Survey> surveyMap = new HashMap<String, Survey>();


        if (storedSurveys.size() > 0) {
            for (Survey survey : storedSurveys) {
                System.out.println(counter + ") " + survey.name);
                surveyMap.put(survey.name, survey);
                counter++;
            }


            // Getting the survey
            Scanner surveyInput = new Scanner(System.in);
            String surveyName;

            System.out.println("Enter the name of survey you want to tabulate or 'back' for main menu: \n");
            surveyName = surveyInput.nextLine();

            Survey surveyToTabulate = surveyMap.get(surveyName);


            int count = 1;
            //for each question in the SOT
            for(int index = 0; index < surveyToTabulate.Questions.size(); index++){

                System.out.println(count + ".) ");
                surveyToTabulate.Questions.get(index).displayQuestion();
                System.out.println("");

                HashMap<List<String>, Integer> hmap = new HashMap<List<String>, Integer>();

                for(ArrayList<AnswerSheet> savedResponces : allTakenSurveys){

                    List<String> temp = savedResponces.get(index).getAnswer();

                    if(hmap.containsKey(temp)){

                        Integer number = hmap.get(temp) + 1;
                        hmap.put(temp, number);

                    }
                    else
                        hmap.put(temp, 1);

                }

                for (Map.Entry<List<String> ,Integer> entry : hmap.entrySet()) {

                    List<String> key = entry.getKey();
                    Integer value = entry.getValue();

                    //display the keys and values
                    System.out.print(" " + key + " ");
                    System.out.println(": " + value);

                }

                System.out.println("\n");
                count++;

            }

            reset();


        }else {
            System.out.println("No stored surveys found, please create one first");
            reset();
        }
}



    //Tabulates the stored tests
    public static void tabulateTest() throws IOException{

        System.out.println("Stored Tests: \n");
        int counter = 1;

        HashMap<String, Test> surveyMap = new HashMap<String, Test>();


        if (storedTests.size() > 0) {
            for (Test test : storedTests) {
                System.out.println(counter + ") " + test.name);
                surveyMap.put(test.name, test);
                counter++;
            }


            // Getting the test
            Scanner testInput = new Scanner(System.in);
            String testName;

            System.out.println("Enter the name of survey you want to tabulate or 'back' for main menu: \n");
            testName = testInput.nextLine();

            Survey testToTabulate = surveyMap.get(testName);


            int count = 1;
            for(int index = 0; index < testToTabulate.Questions.size(); index++){

                System.out.println(count + ".) ");
                testToTabulate.Questions.get(index).displayQuestion();
                System.out.println("");

                HashMap<List<String>, Integer> hmap = new HashMap<List<String>, Integer>();

                for(ArrayList<AnswerSheet> savedResponces : allTakenTests){

                    List<String> temp = savedResponces.get(index).getAnswer();

                    if(hmap.containsKey(temp)){

                        Integer number = hmap.get(temp) + 1;
                        hmap.put(temp, number);

                    }
                    else
                        hmap.put(temp, 1);

                }

                for (Map.Entry<List<String> ,Integer> entry : hmap.entrySet()) {

                    List<String> key = entry.getKey();
                    Integer value = entry.getValue();

                    System.out.print(" " + key + " ");
                    System.out.println(": " + value);

                }

                System.out.println("\n");
                count++;

            }

            reset();


        }else {
            System.out.println("No stored tests found, please create one first");
            reset();
        }
    }





}
