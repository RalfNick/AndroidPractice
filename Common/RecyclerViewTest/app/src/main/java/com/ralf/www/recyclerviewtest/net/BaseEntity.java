package com.ralf.www.recyclerviewtest.net;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BaseEntity
 * @email wanglixin@icourt.cc
 * @date 2018/09/20 上午12:29
 **/
public class BaseEntity<T> {

    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
