package hibernateexample;


import hibernateexample.dao.StudentDao;
import hibernateexample.entity.Student;

import java.util.List;

public class App {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("dd", "gg", "dd@gg.com");
        studentDao.saveStudent(student);
        List <Student> students = studentDao.getStudents();
        students.forEach(s -> System.out.println(s.getFirstName()));
    }
}