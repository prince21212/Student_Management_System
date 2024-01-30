// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    private int age;

    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() { return age; }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() +
                    ", Grade: " + student.getGrade());
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = br.readLine();
                    System.out.print("Enter student's roll number: ");
                    String rollNumber = br.readLine();
                    System.out.print("Enter student's grade: ");
                    String grade = br.readLine();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    break;

                case 2:
                    System.out.print("Enter roll number of student to remove: ");
                    rollNumber = br.readLine();
                    sms.removeStudent(rollNumber);
                    break;

                case 3:
                    System.out.print("Enter roll number of student to search: ");
                    rollNumber = br.readLine();
                    Student student = sms.searchStudent(rollNumber);
                    if (student != null) {
                        System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() +
                                ", Grade: " + student.getGrade());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter filename to save to: ");
                    String saveFilename = br.readLine();
                    sms.saveToFile(saveFilename);
                    break;

                case 6:
                    System.out.print("Enter filename to load from: ");
                    String loadFilename = br.readLine();
                    sms.loadFromFile(loadFilename);
                    break;

                case 7:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
