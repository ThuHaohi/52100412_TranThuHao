package Lab7_2.service;

import Lab7_2.model.Student;

public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student student);
}
