/* This is part of the starter code! 
 * You need to complete this class yourself!*/
package main;
import util.*;
import java.util.Scanner;


public class Main {
    private static void commandMenu() { //This is a static method to print out the command options.
        System.out.println("min score - minimum score");
        System.out.println("min letter - minimum letter grade");
        System.out.println("max score - maximum score");
        System.out.println("max letter - maximum letter grade");
        System.out.println("letter XXXXXXX - letter grade of specific student");
        System.out.println("name XXXXXXX - name of specific student");
        System.out.println("change XXXXXXX YY - changes a specific student's grade");
        System.out.println("average score - average score of grade book");
        System.out.println("average letter - average letter of grade book");
        System.out.println("median score - median score of grade book");
        System.out.println("median letter - median letter of grade book");
        System.out.println("tab scores - print all students with their scores");
        System.out.println("tab letters - print all students with their letters");
        System.out.println("menu - view menu");
    }

    private static boolean isValidFirstName(String firstName) { //This boolean method checks if the firstName entered is valid.
        //Checks if the first character is not uppercase.
        if (!Character.isUpperCase(firstName.charAt(0))) { //
            return false;
        }
        for (int i = 0; i < firstName.length(); i++) { //For loop to go through firstName characters.
            //Checks if the characters are a whitespace OR if they're NOT a letter.
            if (Character.isWhitespace(firstName.charAt(i)) || !Character.isLetter(firstName.charAt(i))) {
                return false;
            }
        }
        return true; //Returns true if fully validated.
    }

    private static boolean isValidLastName(String lastName) { //Method to check if lastName is valid.
        //Replaces the "." in the lastName with an empty string, and we subtract that from the length of lastName to get the dotCount of the lastName.
        int dotCount = lastName.length() - lastName.replace(".", "").length();

        if (!Character.isUpperCase(lastName.charAt(0))) { //Checks if the first character in lastName is NOT uppercase.
            return false;
        }
        else if (dotCount > 1) { //If the dotCount is greater than one(Meaning the lastName has more than one dot) we return false.
            return false;
        }

        //For loop to go through lastName
        for (int i = 0; i < lastName.length(); i++) {
            //If the lastName has a whitespace, or it has a non-letter character that isn't a dot. Return false.
            if (Character.isWhitespace(lastName.charAt(i)) || (!Character.isLetter(lastName.charAt(i)) && lastName.charAt(i) != 46)) {
                return false;
            }
        }


        return true;
    }

    private static boolean isValidPid(int pid) { //Method to validate pid.
        //We turn pid into a string then get the length of said string/
        int length = (Integer.toString(pid)).length();

        //If pid's length isn't 7 OR if it's very first digit is a zero, return false.
        if ((length != 7) || (Integer.parseInt(Integer.toString(pid).substring(0, 1)) == 0)) {
            return false;
        }
        else {
            return true;
        }
    }

    private static boolean isValidGrade(int grade) { //Method to validate grade
        if (grade >= 0 && grade <= 100) { //If grade is between 0 and 100 inclusive, return true.
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String studentInput;
        String  userCmd = "";
        String firstName;
        String lastName;
        int pid;
        int score;

        //New gradebook object to keep all students in.
        Gradebook gradebook = new Gradebook();


        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("\"firstName lastName PID grade\"");
        System.out.println("Press Enter when you are done.");

        //While loop that'll loop until user enters DONE.
        while (true) {
            studentInput = scnr.nextLine(); //Read the entire line.
            //Using a regex, we split what we read into a string depending on their spaces.
            String[] studentArr = studentInput.split("\\s");

            //If the first element in the array is "DONE", break out of the loop.
            if (studentArr[0].equals("DONE")) {
                break;
            }

            //Split the array into variables to validate.
            firstName = studentArr[0];
            lastName = studentArr[1];
            pid = Integer.parseInt(studentArr[2]);
             score = Integer.parseInt(studentArr[3]);

            //Checks if each variable is valid.
            if (isValidFirstName(firstName) && isValidLastName(lastName) && isValidPid(pid) && isValidGrade(score)) {
                //Create new grade object and pass the score.
                Grade grade = new Grade(score);

                //Create new student object with all the variables including the grade object.
                Student student = new Student(firstName, lastName, pid, grade);

                //After student is made, add student into gradebook.
                gradebook.addStudent(student);
            }
            else {
                //If the entries aren't valid, display a message and ask the user to try again.
                System.out.println("Invalid entries, try again.");
                continue;
            }

            System.out.println("Please enter the information of the next student using the same format.");
            System.out.println("If there is no more students, please enter the keyword “DONE”.");
            System.out.println("Press Enter when you are done.");
        }

        //Call to the commandMenu() method.
        commandMenu();
        System.out.print("Enter a command");

        //While loop that'll run until the user enters "quit"
        while (!userCmd.equals("quit")) {
            userCmd = scnr.nextLine();
            //Take the line read and split it using the spaces.
            String[] userArr = userCmd.split("\\s");

            //switch case that has a case for the first element in the array made.
           switch (userArr[0]) {
                case "min":
                    //This wll run if the user enters "min score".
                    if (userArr[1].equals("score")) {
                        //Call to the gradebook's minScore() method.
                        System.out.println("The minimum score is: " + gradebook.minScore());
                    }
                    //This wll run if the user enters "min letter".
                    else if (userArr[1].equals("letter")) {
                        //Call to the gradebook's minLetter() method.
                        System.out.println("The minimum letter is: " + gradebook.minLetter());
                    }
                    else {
                        System.out.println("Invalid input. Check spelling and try again");
                    }
                    break;

                case "max":
                    //This will run if the user enters "max score".
                    if (userArr[1].equals("score")) {
                        System.out.println("The maximum score is: " + gradebook.maxScore());
                    }
                    //This will run if the user enters "max letter".
                    else if (userArr[1].equals("letter")) {
                        System.out.println("The maximum letter is: " + gradebook.maxLetter());
                    }
                    else {
                        System.out.println("Invalid input. Check spelling and try again");
                    }
                    break;

                case "letter":
                    //Runs if the user inputs letter with a pid.
                    //We take pid and turn it back into an int since we read it as a string.
                    pid = Integer.parseInt(userArr[1]);
                    //We pass the pid into the gradebook's letterGivenPid() method.
                    System.out.println(gradebook.letterGivenPid(pid));
                    break;

                case "name":
                    //Another way to do it as well, turn the pid into an int and pass it to the method at the same time.
                    System.out.println(gradebook.nameGivenPid(Integer.parseInt(userArr[1])));
                    break;

               case "change":
                   //Here we take the pid and score from the array and turn them back into an int.
                   pid = Integer.parseInt(userArr[1]);
                   score = Integer.parseInt(userArr[2]);
                   //Pass both the pid and the score to gradebook's changeGrade() method.
                   System.out.println(gradebook.changeGrade(pid, score));
                   break;

                case "average":
                    if (userArr[1].equals("score")) { //Call to method to calculate average score.
                        System.out.printf("The average score is: %.2f\n", gradebook.calculateAvg());
                    }
                    else if (userArr[1].equals("letter")) { //Call to method to calculate average letter.
                        System.out.println("The average score is : " + gradebook.calculateAverageLetter());
                    }
                    else {
                        System.out.println("Invalid input. Check spelling and try again");
                    }
                    break;

                case "median":
                    if (userArr[1].equals("score")) { //Call to method to calculate median score.
                        System.out.printf("The median score is: %.2f", gradebook.calculateMedian());
                    }
                    else if (userArr[1].equals("letter")) { //Call to method to calculate median letter.
                        System.out.println("The median letter is: " + gradebook.calculateMedianLetter());
                    }
                    else {
                        System.out.println("Invalid input. Check spelling and try again");
                    }
                    break;

                case "tab":

                    if (userArr[1].equals("scores")) { //Prints all students with their scores.
                        gradebook.printAllStudents();
                    }
                    else if (userArr[1].equals("letters")) { //Prints all students with their letters.
                        gradebook.printAllStudentsLetter();
                    }
                    else {
                        System.out.println("Invalid input. Check spelling and try again");
                    }
                    break;

                case "quit": //If user enters "quit", break out of program.
                    System.out.println("Goodbye");
                    break;

               case "menu": //Prints the menu again.
                   commandMenu();
                   break;

               default: //If no command was said, asks the user to try again.
                   System.out.println("Invalid input. Check spelling and try again");
                    break;
            }

        }


    }

}
