import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TranscriptGPAGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Transcript GPA Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setResizable(false);

            JTextArea outputArea = new JTextArea();
            outputArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputArea);

            JButton openButton = new JButton("Select Transcript File");

            openButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    outputArea.setText(""); // clear previous results
                    calculateGPA(file, outputArea);
                }
            });

            frame.add(openButton, "North");
            frame.add(scrollPane, "Center");
            frame.setVisible(true);
        });
    }

    private static void calculateGPA(File file, JTextArea outputArea) {
        try (Scanner read = new Scanner(file)) {

            // Grade to GPA mapping
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

            outputArea.append("Course | Grade | Points\n");
            outputArea.append("--------------------------------\n");

            // Regex for course lines
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

                        outputArea.append(courseCode + " " + courseName + " | " + grade + " | " + points + "\n");
                    } else {
                        outputArea.append(courseCode + " " + courseName + " | " + grade + " (Not counted)\n");
                    }
                }
            }

            if (totalCredits > 0) {
                double gpa = totalPoints / totalCredits;
                outputArea.append("\nYour GPA = " + String.format("%.2f", gpa));
            } else {
                outputArea.append("\nNo valid grades found for GPA calculation.");
            }

        } catch (Exception e) {
            outputArea.append("Error reading file: " + e.getMessage());
        }
    }
}
