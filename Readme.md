# Student Grade Tracker

A Java Swing desktop application for managing student records and tracking academic performance. The application allows users to add students, calculate grades automatically, and view performance statistics through a simple graphical user interface.

---

## Overview

Student Grade Tracker is a desktop application developed in Java using Swing. It helps users maintain student records, calculate grades based on marks, and display useful statistics such as average, highest, and lowest marks.

---

## Features

- Add student records
- Automatic grade calculation
- Display average marks
- Display highest marks
- Display lowest marks
- Show total number of students
- Clear all records
- Input validation for marks (0–100)
- User-friendly Java Swing interface

---

## Technologies Used

- Java
- Java Swing
- AWT
- ArrayList (Java Collections Framework)

---

## Project Structure

```text
codeAlpha_studentTracker/
│
├── StudentGradeTrackerGUI.java
├── Student.class
├── StudentGradeTrackerGUI.class
└── README.md
```

---

## How to Run

### Clone the Repository

```bash
git clone https://github.com/your-username/codeAlpha_studentTracker.git
```

### Navigate to the Project Folder

```bash
cd codeAlpha_studentTracker
```

### Compile the Program

```bash
javac StudentGradeTrackerGUI.java
```

### Run the Application

```bash
java StudentGradeTrackerGUI
```

---

## Application Workflow

1. Enter the student's name.
2. Enter marks between 0 and 100.
3. Click **Add Student**.
4. The application will:
   - Store the student record
   - Calculate the grade
   - Update the table
   - Refresh the performance statistics
5. Click **Clear All** to remove all records.

---

## Grade Criteria

| Marks | Grade |
|------:|:-----:|
| 90–100 | A+ |
| 80–89 | A |
| 70–79 | B |
| 60–69 | C |
| 50–59 | D |
| Below 50 | Fail |

---

## Future Enhancements

- Edit student records
- Delete individual students
- Search functionality
- Sort students by name or marks
- Export data to CSV or PDF
- Database integration using MySQL
- User authentication
- Graphical reports and charts

---

## Learning Outcomes

This project demonstrates:

- Object-Oriented Programming (OOP)
- Java Swing GUI Development
- Event Handling
- Data Management using ArrayList
- Input Validation
- Statistical Calculations
- Desktop Application Development

---

## Author

**Keshav Mehra**

---

## License

This project is developed for educational and learning purposes.