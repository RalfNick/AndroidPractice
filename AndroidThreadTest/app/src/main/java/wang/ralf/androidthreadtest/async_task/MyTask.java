package wang.ralf.androidthreadtest.async_task;

import android.util.Log;

import java.util.Arrays;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyTask
 * @email -
 * @date 2019/03/18 下午3:37
 **/
public class MyTask extends AbstractTask<String, Integer, String> {

    private static final String TAG = MyTask.class.getSimpleName();

    @Override
    protected String doInBackground(String... strings) {
        if (strings != null) {
            Log.e(TAG, Arrays.toString(strings));
        }
        try {
            int count = 0;
            int length = 1;
            while (count < 99) {

                count += length;
                // 可调用 publishProgress（）显示进度, 之后将执行 onProgressUpdate（）
                publishProgress(count);
                // 模拟耗时任务
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "下载完毕";
    }
}
