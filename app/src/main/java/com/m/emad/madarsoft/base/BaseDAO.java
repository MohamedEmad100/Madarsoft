package com.m.emad.madarsoft.base;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.m.emad.madarsoft.manager.DataBaseManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;


public abstract class BaseDAO <T extends Object> {

    protected Dao<T,String> mDao;

    public BaseDAO(Class<T> classType) {
        try {
            mDao = DataBaseManager.getInstance().getDataBaseHelper().getDao(classType);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int create(T Obj){
        try {
            return mDao.create(Obj);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public int createAll(List<T> Obj){
        try {
            return mDao.create(Obj);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public List<T> getAll() throws SQLException{
        return mDao.queryForAll();
    }

    public T getOne(String id) throws SQLException{
        return mDao.queryForId(id);
    }


    public int deleteOne(T obj) throws SQLException{
        return mDao.delete(obj);
    }

    public int deleteAll() throws SQLException{
        DeleteBuilder<T,String> deleteBuilder = mDao.deleteBuilder();
        return deleteBuilder.delete();
    }

    public int saveAll(final List<T> ObjList) throws Exception{
        mDao.callBatchTasks(new Callable<T>() {
            @Override
            public T call() throws Exception {
                for (T Obj : ObjList){
                    mDao.createOrUpdate(Obj);
                }
                return null;
            }
        });
        return ObjList.size();
    }

    public long countAll() throws SQLException {
        QueryBuilder queryBuilder = mDao.queryBuilder();
        queryBuilder.setCountOf(true);
        return mDao.countOf(queryBuilder.prepare());
    }


}
