package cc.icourt.www.db_library.util;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DbCallBack
 * @email wanglixin@icourt.cc
 * @date 2018/06/25 下午11:00
 **/
public interface DbCallBack<T> {

    void onSuccess(List<T> result);
    void onFailed();
    void onNotification(boolean result);
}
