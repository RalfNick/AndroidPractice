package wang.ralf.serializationtest;

import java.io.Serializable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name Student
 * @email -
 * @date 2019/07/14 11:50
 **/
public class Student implements Serializable {

    private static final long serialVersionUID = -8297638771764014163L;
    private String name;
    private int age;
    private transient String nickName;

    public Student(String name, int age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
