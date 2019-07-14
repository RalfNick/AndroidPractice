package wang.ralf.serializationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.serialization_btn).setOnClickListener(this);
        findViewById(R.id.parcelable_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.serialization_btn) {
            Student student = new Student("Ralf", 26, "Nick");
            Intent intent = new Intent(this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.parcelable_btn) {
            Teacher teacher = new Teacher("Wang", 28);
            Intent intent = new Intent(this, ThirdActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("teacher", teacher);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Ralf", 26, "Nick");
        System.out.println(student);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp"));
        oos.writeObject(student);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("temp")));
        Student studentRead = (Student) ois.readObject();
        System.out.println(studentRead);
    }
}
