package kr.co.mvp.dagger2.sample.mvp.database;

import android.annotation.TargetApi;
import android.os.Build;

import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;

/**
 * Created by my on 2016-12-20.
 */

public interface BaseRepository<T> {

    List<T> selectAll() throws SQLException;

    void add(List<T> value) throws SQLException;

    List<T> selectWhere(Where where) throws SQLException;

    boolean update(List<T> value, Where where);

    boolean delete(List<T> value, Where where);


    Where<T, Integer> getWhere() throws SQLException;


}
