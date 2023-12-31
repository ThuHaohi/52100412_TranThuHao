package Lab7_4.service;

import Lab7_4.model.Student;

import java.util.List;

public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student student);

    public List<Student> findStudentsWithAgeGreaterThanEqual(int age);

    public Integer countStudentsWithIeltsScore(double score);

    public List<Student> searchStudentsByName(String keyword);
}
