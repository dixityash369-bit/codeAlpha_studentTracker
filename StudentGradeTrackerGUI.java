import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTrackerGUI extends JFrame {

    ArrayList<Student> students = new ArrayList<>();
    JTextField nameField, marksField;
    JTable table;
    DefaultTableModel model;
    JLabel averageLabel, highestLabel, lowestLabel, totalLabel;

    StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(new Color(240, 244, 255));
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("🎓 Student Grade Tracker", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(88, 86, 214));
        title.setBorder(BorderFactory.createEmptyBorder(25, 10, 25, 10));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 1, 12, 12));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 235), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JLabel formTitle = new JLabel("Add Student");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        formTitle.setForeground(new Color(45, 52, 80));

        nameField = createInput();
        marksField = createInput();

        JButton addBtn = createButton("➕ Add Student", new Color(39, 174, 96));
        JButton clearBtn = createButton("🗑 Clear All", new Color(231, 76, 60));

        formPanel.add(formTitle);
        formPanel.add(new JLabel("Student Name"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Marks"));
        formPanel.add(marksField);
        formPanel.add(addBtn);
        formPanel.add(clearBtn);

        model = new DefaultTableModel(new String[]{"Student Name", "Marks", "Grade"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(35);
        table.setGridColor(new Color(220, 225, 240));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 17));
        table.getTableHeader().setBackground(new Color(52, 152, 219));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 235)));

        JPanel cards = new JPanel(new GridLayout(1, 4, 15, 15));
        cards.setBackground(new Color(240, 244, 255));

        totalLabel = createCard("Total\n0", new Color(155, 89, 182));
        averageLabel = createCard("Average\n0", new Color(241, 196, 15));
        highestLabel = createCard("Highest\n0", new Color(46, 204, 113));
        lowestLabel = createCard("Lowest\n0", new Color(231, 76, 60));

        cards.add(totalLabel);
        cards.add(averageLabel);
        cards.add(highestLabel);
        cards.add(lowestLabel);

        main.add(formPanel, BorderLayout.WEST);
        main.add(scroll, BorderLayout.CENTER);
        main.add(cards, BorderLayout.SOUTH);

        add(title, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        addBtn.addActionListener(e -> addStudent());
        clearBtn.addActionListener(e -> clearAll());

        setVisible(true);
    }

    JTextField createInput() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 200, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return field;
    }

    JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        return btn;
    }

    JLabel createCard(String text, Color color) {
        JLabel label = new JLabel("<html><center>" + text.replace("\n", "<br>") + "</center></html>", JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        return label;
    }

    void addStudent() {
        String name = nameField.getText().trim();
        String marksText = marksField.getText().trim();

        if (name.isEmpty() || marksText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try {
            double marks = Double.parseDouble(marksText);

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.");
                return;
            }

            students.add(new Student(name, marks));
            model.addRow(new Object[]{name, marks, getGrade(marks)});

            nameField.setText("");
            marksField.setText("");

            updateSummary();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid marks only!");
        }
    }

    String getGrade(double marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "Fail";
    }

    void updateSummary() {
        double total = 0;
        double highest = students.get(0).marks;
        double lowest = students.get(0).marks;

        for (Student s : students) {
            total += s.marks;
            highest = Math.max(highest, s.marks);
            lowest = Math.min(lowest, s.marks);
        }

        double avg = total / students.size();

        totalLabel.setText("<html><center>Total<br>" + students.size() + "</center></html>");
        averageLabel.setText("<html><center>Average<br>" + String.format("%.2f", avg) + "</center></html>");
        highestLabel.setText("<html><center>Highest<br>" + highest + "</center></html>");
        lowestLabel.setText("<html><center>Lowest<br>" + lowest + "</center></html>");
    }

    void clearAll() {
        students.clear();
        model.setRowCount(0);

        totalLabel.setText("<html><center>Total<br>0</center></html>");
        averageLabel.setText("<html><center>Average<br>0</center></html>");
        highestLabel.setText("<html><center>Highest<br>0</center></html>");
        lowestLabel.setText("<html><center>Lowest<br>0</center></html>");
    }

    public static void main(String[] args) {
        new StudentGradeTrackerGUI();
    }
}