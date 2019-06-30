// IMyService.aidl
package wang.ralf.aidlserver;

import wang.ralf.aidlserver.Student;

interface IMyService {

    void add(int a,int b);
    List<Student> getStudent();
    void addStudent(in Student student);
}
