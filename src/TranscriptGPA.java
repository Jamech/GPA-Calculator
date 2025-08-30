import java.io.File;
import java.util.*;
import java.util.regex.*;

public class TranscriptGPA {
    public static void main(String[] args) {
        try {
            File myFile = new File("filename1.txt");
            Scanner read = new Scanner(myFile);

            // Grade to GPA points
            Map<String, Double> gradePoints = new HashMap<>();
            gradePoints.put("A+", 4.0);
            gradePoints.put("A", 4.0);
            gradePoints.put("B+", 3.5);
            gradePoints.put("B", 3.0);
            gradePoints.put("C+", 2.5);
            gradePoints.put("C", 2.0);
            gradePoints.put("D+", 1.5);
            gradePoints.put("D", 1.0);
            gradePoints.put("F", 0.0);

            double totalPoints = 0;
            int totalCredits = 0;
            int creditsPerCourse = 4;

            System.out.println("Course | Grade | Points");
            System.out.println("------------------------");

            // Regex to capture course line: CourseCode ... CourseName ... Grade
            Pattern linePattern = Pattern.compile("^[0-9]+\\s+([A-Z]{2,4}\\s*[0-9]{3,4})\\s+(.+?)\\s+([A-F][+]?|DQ|EX|INC|NE|WP)$");

            while (read.hasNextLine()) {
                String line = read.nextLine().trim();

                Matcher m = linePattern.matcher(line);
                if (m.find()) {
                    String courseCode = m.group(1).trim();
                    String courseName = m.group(2).trim();
                    String grade = m.group(3).trim();

                    if (gradePoints.containsKey(grade)) {
                        double points = gradePoints.get(grade) * creditsPerCourse;
                        totalPoints += points;
                        totalCredits += creditsPerCourse;

                        System.out.println(courseCode + " " + courseName + " | " + grade + " | " + points);
                    } else {
                        System.out.println(courseCode + " " + courseName + " | " + grade + " (Not counted in GPA)");
                    }
                }
            }

            read.close();

            if (totalCredits > 0) {
                double gpa = totalPoints / totalCredits;
                System.out.printf("%nYour GPA = %.2f%n", gpa);
            } else {
                System.out.println("No valid grades found for GPA calculation.");
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
