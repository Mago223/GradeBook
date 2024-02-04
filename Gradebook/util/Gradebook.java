/* This is part of the starter code! 
 * You need to complete this class yourself!*/
package util;

import java.util.*;

public class Gradebook {
    private ArrayList<Student> listOfStudents;

	public Gradebook() { //Constructor method that initializes the ArrayList.
		listOfStudents = new ArrayList<Student>();
	}
	public double calculateAvg() {
		//Method that take's the sum of all scores and divides by the amount of students.
		double sum = 0;
		for(Student s: listOfStudents) {
			sum += s.getGrade().getScore();
		}
		return sum / listOfStudents.size();
	}

	public String calculateAverageLetter() {
		//We call the calculateAvg() method and round since we need an int.
		// We then call Grade's toLetterGrade() method to get letter grade.
		String avgLetter;
		int averageInt = (int) Math.round(calculateAvg());
		avgLetter = Grade.toLetterGrade(averageInt);
		return avgLetter;
	}
    public float calculateMedian() { //Method to calculate median score.
		int i = 0, n = listOfStudents.size(); //n is the size of the array.
		int[] scores = new int[n];
		for(Student s: listOfStudents) {
			scores[i++] = s.getGrade().getScore(); //We take all the student's scores and add them to an array.
		}
		Arrays.sort(scores); //We sort the array in incrementing order using Array.sort().
		//If the size of the array is even, we return  the value at n/2 plus the value right before it divided by 2.
		if (n % 2 == 0) {
			return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
		}
		//if the array is not even, the median is simply the value in the middle.
		else {
			return scores[n / 2];
		}
    }

	public String calculateMedianLetter() { //Method to calculate median letter grade.
		int i = 0;
		int smallestIndex;
		int tempVal;
		int n = listOfStudents.size();
		int[] scores = new int[n];
		float medianScore;

		//Takes all student's scores and add them to an array.
		for (Student student: listOfStudents) {
			scores[i++] = student.getGrade().getScore();
		}

		//Selection sort algorithm to sort it in ascending order.

		for (i = 0; i < scores.length - 1; i++) {
			smallestIndex = i;
			for (int j = i + 1; j < scores.length; j++) {
				if (scores[j] < scores[smallestIndex]) {
					smallestIndex = j;
				}
			}
			tempVal = scores[i];
			scores[i] = scores[smallestIndex];
			scores[smallestIndex] = tempVal;
		}

		//Check median and transform to letter.
		if (n % 2 == 0) {
			medianScore = (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
		}
		else {
			medianScore = scores[n /2];
		}

		return Grade.toLetterGrade(Math.round(medianScore));

	}
    public void printAllStudents() { //Prints all students with their scores.
		for(Student s: listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
		}
    }

	public void printAllStudentsLetter() { //Prints all students with their letter grade.
		for (Student s: listOfStudents) {
			System.out.printf("%s\t%s\t%d\t%s\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
		}
	}

	public void addStudent(Student student) { //Adds a student into listOfStudents ArrayList/
		listOfStudents.add(student);
	}

	public int minScore() { //method that calculates min score.
		//Sets minimum value temporarily to the first element in ArrayList.
		int minValue = listOfStudents.get(0).getGrade().getScore();
		for (Student student: listOfStudents) {
			//For loop that compares each student's score to the minValue and updates it if the student score is less than or equal to minValue.
			if (student.getGrade().getScore() <= minValue) {
				minValue = student.getGrade().getScore();
			}
		}
		return minValue;
	}

	public String minLetter() {
		//Method that uses the same for loop calculates the minimum score as well as the minimum letter and returns that.
		//Could also call minLetter() and turn in into a letter grade using Grade.toLetterGrade().
		String minLetter = null;
		int minValue = listOfStudents.get(0).getGrade().getScore();
		for (Student student: listOfStudents) {
			if (student.getGrade().getScore() <= minValue) {
				minValue = student.getGrade().getScore();
				minLetter = student.getGrade().getLetterGrade();
			}
		}
		return minLetter;
	}

	public int maxScore() {
		//Similar to minScore, we check here for the maximum value in the ArrayList.
		int maxValue = listOfStudents.get(0).getGrade().getScore();
		for (Student student: listOfStudents) {
			if (student.getGrade().getScore() >= maxValue) {
				maxValue = student.getGrade().getScore();
			}
		}
		return maxValue;
	}

	public String maxLetter() {
		//Similar to minLetter, we check here for maxScore and directly grab that letterGrade.
		String maxLetter = null;
		int maxValue = listOfStudents.get(0).getGrade().getScore();
		for (Student student: listOfStudents) {
			if (student.getGrade().getScore() >= maxValue) {
				maxValue = student.getGrade().getScore();
				maxLetter = student.getGrade().getLetterGrade();
			}
		}
		return maxLetter;
	}

	public String letterGivenPid(int pid) {
		//Method that gets a student's letter grade given their pid.
		String letterGivenPid = null;

		for (Student student: listOfStudents) {
			//for loop that goes through each student and compares their pid to the one given.
			if (student.getPid() == pid) {
				//If a pid matches, retrieve that student's pid.
				letterGivenPid = student.getGrade().getLetterGrade();
			}
		}
		return "The letter grade of the pid: " + pid + " is: " + letterGivenPid;
	}

	public String nameGivenPid(int pid) {
		//Similarly to letterGivenPid(), we match the pid given to a student's pid and obtain their full name.
		String fullName = null;
		for (Student student: listOfStudents) {
			if (student.getPid() == pid) {
				fullName = student.getFirstName() + " " + student.getLastName();
			}
		}
		return "The full name of pid: " + pid + " is: " + fullName;
	}

	public String changeGrade(int pid, int newGrade) {
		//method that takes in a pid and a new grade to change a grade.
		for (Student student: listOfStudents) {
			//for loop that goes through all students until one matches the pid given.
			if (student.getPid() == pid) {
				//Using Grade's setScore() we update their score which automatically updates their letter grade.
				student.getGrade().setScore(newGrade);
			}
		}
		return "Grade changed to: " + newGrade + " for pid: " + pid;
	}




}
