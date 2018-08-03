package cc.icourt.www.greedaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cc.icourt.www.db_library.data.Clazz;
import cc.icourt.www.db_library.data.Student;
import cc.icourt.www.db_library.db.ClazzDao;
import cc.icourt.www.db_library.db.StudentDao;
import cc.icourt.www.db_library.util.DbCallBack;
import cc.icourt.www.db_library.util.DbUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DbUtil dbUtil;
    private Button mInsertBtn;
    private Button mDeleteBtn;
    private Button mQueryBtn;
    private Button mJoinBtn;

    List<Clazz> clazzList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mDeleteBtn = findViewById(R.id.delete_btn);
        mInsertBtn = findViewById(R.id.insert_btn);
        mQueryBtn = findViewById(R.id.query_btn);
        mJoinBtn = findViewById(R.id.join_btn);
        mQueryBtn.setOnClickListener(this);
        mInsertBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mJoinBtn.setOnClickListener(this);
    }

    private void initData() {

        dbUtil = DbUtil.getInstance(getApplication());

        int studenId = 10;
        long id = 0;
        for (int i = 1; i < 4; i++) {
            Clazz clazz = new Clazz();
            clazz.setName(i + "班");
            clazz.setId(id++);
            for (int j = 0; j < 5; j++) {
                Student student = new Student();
                student.setAge(18 + j);
                student.setId(id++);
                student.setStudentID(studenId++);
                student.setName("Ralf");
                student.setClazID(clazz.getId());
                studentList.add(student);
            }
            clazzList.add(clazz);

        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.delete_btn:

                if (dbUtil.count(Clazz.class) > 0) {
                    dbUtil.deleteAll(Clazz.class);
                }

                break;

            case R.id.insert_btn:

                dbUtil.insertOrReplaceInTx(clazzList);
                dbUtil.insertOrReplaceInTx(studentList);
                break;

            case R.id.query_btn:
                // 单条查询
                Clazz cls = dbUtil.queryById(Clazz.class,1);
                // 查询所有的班级
                List<Clazz> clazList = dbUtil.queryAll(Clazz.class);
                for (Clazz clazz : clazList) {
                    Log.e(TAG, "[1] " + clazz.toString());
                }

                List<Student> studentList = dbUtil.queryAll(Student.class);
                for (Student student : studentList) {
                    Log.e(TAG, "[2] " + student.toString());
                }
                //条件查询
                final List<Student> student = dbUtil.queryRaw(Student.class, "where studentID = ?", new String[]{"12"});
                Log.e(TAG, "[3] " + student.get(0).toString());

                // 异步查询
                DbCallBack<Student> studentDbCallBack = new DbCallBack<Student>() {
                    @Override
                    public void onSuccess(List<Student> result) {
                        if (result.size() > 0) {
                            for (Student student1 : result) {
                                Log.e(TAG, "[4] " + student1.toString());
                            }
                        }
                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onNotification(boolean result) {

                    }
                };

                dbUtil.setDbCallBack(studentDbCallBack)
                        .queryAsync(Student.class, StudentDao.Properties.StudentID.eq(19));

                // 批量异步条件查询
                QueryBuilder<Student> builder = dbUtil.getQueryBuilder(Student.class)
                        .where(StudentDao.Properties.StudentID.between(12, 16));

                dbUtil.setDbCallBack(studentDbCallBack)
                        .queryAsyncAll(Student.class, builder);

                // 批量同步条件查询
                QueryBuilder<Student> builder1 = dbUtil.getQueryBuilder(Student.class)
                        .limit(3)
                        .distinct()
                        .offset(2);

                List<Student> list = dbUtil.queryAll(Student.class, builder1);
                for (Student student1 : list) {
                    Log.e(TAG, "[4] " + student1.toString());
                }

                break;

            case R.id.join_btn:

                QueryBuilder<Student> builder2 = dbUtil.getQueryBuilder(Student.class);
                builder2.join(StudentDao.Properties.ClazID, Clazz.class)
                        .where(ClazzDao.Properties.Id.eq(1));
                List<Student> studentList1 = builder2.list();
                for (Student student2 : studentList1) {
                    Log.e(TAG, "" + student2.toString());
                }
                break;

        }
    }
}
