/* This is part of the starter code! 
 * You need to complete this class yourself!*/
package util;

public class Grade {

    public static String toLetterGrade(int score) {
        //Public method that takes in a score and returns a letter grade.
        if (score >= 95) {
            return "A";
        }
        else if (score >= 90) {
            return "A-";
        }
        else if (score >= 87) {
            return "B+";
        }
        else if (score >= 83) {
            return "B";
        }
        else if (score >= 80) {
            return "B-";
        }
        else if (score >= 77) {
            return "C+";
        }
        else if (score >= 70) {
            return "C";
        }
        else if (score >= 60) {
            return "D";
        }
        else {
            return "F";
        }
    }
    private int score;
    private String letterGrade;
    public Grade(int score) {
        //Constructor method for Grade.
        this.score = score;
        //Calls toLetterGrade() and passes the score given to get the letter grade.
        this.letterGrade = toLetterGrade(score);
    }

    public void setScore(int score) {
        //This method is used to update the score and letter grade of a student.
        this.score = score;
        this.letterGrade = toLetterGrade(score);
    }
    public int getScore() {
        return score;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
}
