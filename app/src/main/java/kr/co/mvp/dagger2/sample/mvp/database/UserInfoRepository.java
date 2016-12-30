package kr.co.mvp.dagger2.sample.mvp.database;

import android.util.Log;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;
import rx.Completable;
import rx.Observable;

/**
 * Created by my on 2016-12-20.
 */

public class UserInfoRepository implements BaseRepository<UserInfo> {

    @Inject
    DataHelper dataHelper;

    @Inject
    public UserInfoRepository(){}
    @Override
    public List<UserInfo> selectAll() throws SQLException {

        Dao<UserInfo, Integer> userInfoIntegerDao = null;
        userInfoIntegerDao = dataHelper.getUserDao();
        QueryBuilder<UserInfo, Integer> qb = userInfoIntegerDao.queryBuilder();
        return (List<UserInfo>) userInfoIntegerDao.query(qb.prepare());

    }

    @Override
    public void add(List<UserInfo> value) throws SQLException{

        Dao<UserInfo,Integer> userInfoDao=dataHelper.getUserDao();
        for(UserInfo userInfo : value){
            userInfoDao.createOrUpdate(userInfo);
        }
    }

    @Override
    public List<UserInfo> selectWhere(Where where)throws SQLException  {

        Dao<UserInfo, Integer> userInfoIntegerDao = null;
        userInfoIntegerDao = dataHelper.getUserDao();
        QueryBuilder<UserInfo, Integer> qb = userInfoIntegerDao.queryBuilder();
        qb.setWhere(where);
        Log.e("where.getStatement()",where.getStatement());
        return (List<UserInfo>) userInfoIntegerDao.query(qb.prepare());

    }

    @Override
    public boolean update(List<UserInfo> value, Where where) {
        return false;
    }

    @Override
    public boolean delete(List<UserInfo> value, Where where) {
        return false;
    }

    @Override
    public Where<UserInfo, Integer> getWhere() throws SQLException{
        return dataHelper.getUserDao().queryBuilder().where();
    }

}
