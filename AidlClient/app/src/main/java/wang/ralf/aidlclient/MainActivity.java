package wang.ralf.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import wang.ralf.aidlserver.IMyService;
import wang.ralf.aidlserver.Student;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private IMyService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyService.Stub.asInterface(service);
            Log.e(TAG, "onServiceConnected 已连接 ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected 已断开连接 ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bind_btn).setOnClickListener(this);
        findViewById(R.id.add_btn).setOnClickListener(this);
        findViewById(R.id.unbind_btn).setOnClickListener(this);
        findViewById(R.id.get_student_btn).setOnClickListener(this);
        findViewById(R.id.add_student_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.unbind_btn) {
            if (mConnection != null) {
                unbindService(mConnection);
            }
        } else if (id == R.id.add_btn) {
            if (mService != null) {
                try {
                    mService.add(10, 20);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.bind_btn) {
            Intent intent = new Intent("wang.ralf.aidlserver.MyService");
            intent.setPackage("wang.ralf.aidlserver");
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        } else if (id == R.id.get_student_btn) {
            if (mService != null) {
                try {
                    List<Student> list = mService.getStudent();
                    for (Student student : list) {
                        Log.e(TAG, student.toString());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.add_student_btn) {
            if (mService != null) {
                Student student = new Student();
                student.age = 21;
                student.name = "Ralf";
                student.sex = Student.SEX_MALE;
                student.sno = 1;
                try {
                    mService.addStudent(student);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
