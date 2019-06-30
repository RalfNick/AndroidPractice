package wang.ralf.asynctasktest;

import android.os.AsyncTask;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyAsyncTask
 * @email -
 * @date 2019/06/28 20:41
 **/
public class MyAsyncTask extends AsyncTask<String, Float, String> {

    private static final int COUNT = 1000;
    private TaskCallBack mCallBack;

    public static MyAsyncTask newTask() {
        return new MyAsyncTask();
    }

    private MyAsyncTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallBack.onPrepare();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mCallBack.onFinished(s);
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);
        mCallBack.onUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        mCallBack.onCancelled(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        int i = 1;
        while (i < COUNT) {
            publishProgress(i * 1.0f / COUNT);
            if (i % 100 == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return "未下载完成 - interrupt - " + i * 1.0f / COUNT;
                }
            }
            i++;
            if (isCancelled()) {
                return "未下载完成 - " + i * 1.0f / COUNT;
            }
        }
        return "下载完成";
    }

    public void setCallBack(TaskCallBack callBack) {
        mCallBack = callBack;
    }

    public interface TaskCallBack {

        void onPrepare();

        void onUpdate(Float[] values);

        void onCancelled(String s);

        void onFinished(String s);
    }
}
