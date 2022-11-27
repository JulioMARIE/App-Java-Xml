package Data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Member;

public class DBHelper extends OrmLiteSqliteOpenHelper{

    public static final String DB_NAME = "presence_manager.db";
    private static final int DB_VERSION = 16;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Member.class);
            android.util.Log.i("DATABASE", "SUCCES DE CREATION DE TABLE");
        } catch (java.sql.SQLException e) {
            android.util.Log.e("DATABASE", "ERREUR DE CREATION DE TABLE");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Member.class, true);
            onCreate(database, connectionSource);
            android.util.Log.i("DATABASE", "SUCCES DE MISE A JOUR");
        } catch (java.sql.SQLException e) {
            android.util.Log.e("DATABASE", "ERREUR DE SUPPRESSION DE TABLE");
            e.printStackTrace();
        }
    }

    public <T> int inserted(T obj) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.create(obj);
    }

    public <T> int updated(T obj) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.update(obj);
    }

//    public <T> int deleted(T obj) throws SQLException, java.sql.SQLException {
//        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
//        return dao.delete(obj);
//    }


    public <T> int getID(Class<T> clazz, String tel) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.executeRaw("SELECT idmember FROM "+ dao.getTableName()+ "WHERE telephone ='"+tel+"';");
    }

    public <T> List<T> getAll(Class<T> clazz) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForAll();
    }

    public <T> List<T> getAllOrdered(Class<T> clazz, String orderBy, boolean ascending) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryBuilder().orderBy(orderBy, ascending).query();
    }

    public <T> void fillObject(Class<T> clazz, T aObj) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        dao.createOrUpdate(aObj);
    }

    public <T> void fillObjects(Class<T> clazz, Collection<T> aObjList) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        for (T obj : aObjList) {
            dao.createOrUpdate(obj);
        }
    }

    public <T> T getById(Class<T> clazz, Object aId) throws SQLException, java.sql.SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.queryForId(aId);
    }

    public <T> List<T> query(Class<T> clazz, Map<String, Object> aMap) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForFieldValues(aMap);
    }

    public <T> List<T> queryNot(Class<T> clazz, String columnName, int value) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);

        return dao.queryBuilder().where().ne(columnName, value).query();
    }

    public <T> T queryFirst(Class<T> clazz, Map<String, Object> aMap) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        List<T> list = dao.queryForFieldValues(aMap);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public <T> Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    public <T> int deleteById(Class<T> clazz, Object aId) throws SQLException, java.sql.SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.deleteById(aId);
    }

    public <T> int deleteObjects(Class<T> clazz, Collection<T> aObjList) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);

        return dao.delete(aObjList);
    }

    public <T> void deleteAll(Class<T> clazz) throws SQLException, java.sql.SQLException {
        Dao<T, ?> dao = getDao(clazz);
        dao.deleteBuilder().delete();
    }

    public static HashMap<String, Object> where(String aVar, Object aValue) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put(aVar, aValue);
        return result;
    }

//    public <T> String getFirstlettter(Class<T> clazz, int idm) throws java.sql.SQLException {
//        Dao<T, ?> dao = getDao(clazz);
//        return dao.queryRaw("SELECT SUBSTR(prenom,1,1) FROM "+ dao.getTableName() +" WHERE id = "+idm).getResults().toString();
//    }
}