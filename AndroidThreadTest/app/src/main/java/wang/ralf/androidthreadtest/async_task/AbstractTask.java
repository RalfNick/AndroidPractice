package wang.ralf.androidthreadtest.async_task;

import android.os.AsyncTask;

/**
 * @author Ralf(lixin)
 * DESCRIPTION
 * @name AbstractTask
 * @email -
 * @date 2019/03/18 下午3:05
 **/
public abstract class AbstractTask<P, T, R> extends AsyncTask<P, T, R> {

    private MyTaskListener<T, R> mListener;

    public AbstractTask() {
    }

    public AbstractTask(MyTaskListener<T, R> listener) {
        mListener = listener;
    }

    public void setListener(MyTaskListener<T, R> listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mListener != null) {
            mListener.onTaskStart();
        }
    }

    @Override
    protected void onPostExecute(R result) {
        super.onPostExecute(result);
        if (mListener != null) {
            mListener.onTaskFinished(result);
        }
    }

    @Override
    protected void onProgressUpdate(T... values) {
        super.onProgressUpdate(values);
        if (mListener != null) {
            mListener.onTaskUpdate(values);
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (mListener != null) {
            mListener.onTaskCancelled();
        }
    }

    /**
     * Task 任务不在 Activity 中内部类，外部使用时，设置接口
     */
    public interface MyTaskListener<T, R> {

        /**
         * 任务开始时回调
         */
        void onTaskStart();

        /**
         * 任务结束时回调
         *
         * @param result 结果
         */
        void onTaskFinished(R result);

        /**
         * 任务取消时回调
         */
        void onTaskCancelled();

        /**
         * 执行过程中更新 UI 回调
         *
         * @param values 过程中的值，如进度条等
         */
        void onTaskUpdate(T... values);
    }
}
