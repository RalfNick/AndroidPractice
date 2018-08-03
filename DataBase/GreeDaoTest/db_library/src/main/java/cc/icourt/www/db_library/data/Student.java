package cc.icourt.www.db_library.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author Ralf
 * DESCRIPTION
 * @name Student
 * @email
 * @date 2018/06/05 上午9:15
 **/
@Entity(nameInDb = "Student")
public class Student {

    @Unique
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "studentID")
    private int studentID;

    private String name;
    private long clazID;
    private int age;

    @Generated(hash = 1556870573)
    public Student() {
    }

    @Generated(hash = 435762767)
    public Student(Long id, int studentID, String name, long clazID, int age) {
        this.id = id;
        this.studentID = studentID;
        this.name = name;
        this.clazID = clazID;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getClazID() {
        return this.clazID;
    }

    public void setClazID(long clazID) {
        this.clazID = clazID;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "[" + "Student ID=" + studentID + ",clazz ID=" + clazID
                + ", name=" + name + ",age=" + age + "]";
    }
}
