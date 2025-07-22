import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSubjects = 0;

        // Input number of subjects with validation
        while (true) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                numSubjects = scanner.nextInt();
                if (numSubjects > 0) {
                    break;
                } else {
                    System.out.println("Number of subjects must be greater than 0!");
                }
            } else {
                System.out.println("ERROR! Please enter a valid integer number.");
                scanner.next(); // Consume invalid input
            }
        }

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks (out of 100) for Subject " + (i + 1) + ": ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter an integer between 0 and 100: ");
                scanner.next();
            }
            marks[i] = scanner.nextInt();

            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks! Please enter between 0 and 100: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input! Please enter a number: ");
                    scanner.next();
                }
                marks[i] = scanner.nextInt();
            }

            totalMarks += marks[i];
        }

        // Calculate average
        double averagePercentage = (double) totalMarks / numSubjects;

        // Grade assignment
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n📊 Result Summary:");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
