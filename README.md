📘 Transcript GPA Calculator

This Java program calculates your GPA from your exam slip transcript.
It extracts courses and grades from a .txt file and computes GPA for:

The overall GPA across all semesters

🔹 How to Use

Convert your Exam Slip PDF to Text

Use an online tool like Zamzar PDF to TXT
 or any other PDF-to-text converter.

Save the converted file as exam_slip.txt.

Run the Program

Compile:

javac TranscriptGPAGUI.java


Run:

java TranscriptGPAGUI


Select Transcript File

When the GUI window opens, click "Select Transcript File".

Browse and open your converted .txt exam slip.

View Results

At the bottom, you’ll see the overall GPA for all semesters combined.

🔹 Example

Transcript snippet (from exam slip text):

DAY-S1-2023 - DAY S1 2023 (Jan Intake)
1   BIT 1111   Communication and Technical Writing     B
2   BIT 1120   Mathematics                             C+
3   BIT 1160   Introduction to Systems Analysis        C+

DAY-S2-2023 - DAY S2 2023
1   BIT 1131   Fundamentals of Electrical & Electronics   C
2   BIT 1140   Data Communications & Networks             B
3   ICT 1110   Introduction to Programming                B+


Program Output (GUI):

Course | Grade | Points
--------------------------------
BIT 1111 Communication and Technical Writing | B  | 12.0
BIT 1120 Mathematics                         | C+ | 10.0
BIT 1160 Introduction to Systems Analysis    | C+ | 10.0
Semester GPA (DAY-S1-2023) = 2.58

BIT 1131 Fundamentals of Electrical & Electronics | C  | 8.0
BIT 1140 Data Communications & Networks           | B  | 12.0
ICT 1110 Introduction to Programming              | B+ | 14.0
Semester GPA (DAY-S2-2023) = 2.83

--------------------------------
Overall GPA = 2.71

🔹 Notes

Each course is assumed to have 4 credit hours.

GPA scale used:

A+ = 4.0

A = 4.0

B+ = 3.5

B = 3.0

C+ = 2.5

C = 2.0

D+ = 1.5

D = 1.0

F = 0.0
