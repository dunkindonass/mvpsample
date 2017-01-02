package kr.co.mvp.dagger2.sample.mvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;

/**
 * Created by my on 2016-12-22.
 */

public class DataHelper extends OrmLiteSqliteOpenHelper {

    Dao<UserInfo, Integer> userDao;
    private static final String DATABASE_NAME = "sample.db";
    private static final int DATABASE_VERSION = 2;


    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    public DataHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserInfo.class);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserInfo.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(),
                    "Unable to upgrade database from version " + oldVersion + " to new " + newVersion, e);
        }
    }


    public Dao<UserInfo, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(UserInfo.class);
        }
        return userDao;
    }
}
