package wang.ralf.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyService
 * @email -
 * @date 2019/05/19 20:42
 **/
public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    private static final String PACKAGE_NAME = "wang.ralf.aidlclient";

    private List<Student> mStudents = new ArrayList<Student>();

    private IMyService.Stub mBinder = new IMyService.Stub() {
        @Override
        public void add(int a, int b) throws RemoteException {
            int result = a + b;
            Log.e(TAG, "执行了add() -  结果是 " + result);
        }

        @Override
        public List<Student> getStudent() throws RemoteException {
            synchronized (this) {
                return mStudents;
            }
        }

        @Override
        public void addStudent(Student student) throws RemoteException {
            synchronized (this) {
                if (!mStudents.contains(student)) {
                    mStudents.add(student);
                }
            }
        }

        //在这里可以做权限认证，return false意味着客户端的调用就会失败，比如下面，只允许包名为com.example.test的客户端通过，
        //其他apk将无法完成调用过程
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            String packageName = null;
            String[] packages = MyService.this.getPackageManager().
                    getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.d(TAG, "onTransact: " + packageName);
            if (!PACKAGE_NAME.equals(packageName)) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (this) {
            for (int i = 1; i < 6; i++) {
                Student student = new Student();
                student.name = "student#" + i;
                student.age = i * 5;
                mStudents.add(student);
            }
        }
        Log.e(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind()");
        return mBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }
}
