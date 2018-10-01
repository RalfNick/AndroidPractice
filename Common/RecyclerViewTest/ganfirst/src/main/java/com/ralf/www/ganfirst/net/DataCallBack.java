package com.ralf.www.ganfirst.net;

/**
 * @author karasjoker
 * @name DataCallBack
 * DESCRIPTION
 * @date 2018/02/27/下午2:48
 */

public interface DataCallBack<T> {

    void dataStart();

    void dataSuccess(T data);

    void dataError(Throwable e);

    void dataComplete();

    void dataRequestCancel();
}
