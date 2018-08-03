package cc.icourt.www.db_library.util;

import android.app.Application;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cc.icourt.www.db_library.db.DaoMaster;
import cc.icourt.www.db_library.db.DaoSession;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DbUtil
 * @email wanglixin@icourt.cc
 * @date 2018/06/11 下午9:15
 **/
public class DbUtil {

    private static final String DEFAULT_DATABASE_NAME = "Database.db";
    private static final String DEFAULT_DATABASE_PASSWORD = "hahaha";
    private DaoSession mDaoSession;
    private DaoMaster mDaoMaster;
    private AbstractDao mAbstractDao;
    private static DbUtil mDbUtils;
    private DaoMaster.DevOpenHelper mHelper;
    private DbCallBack mCallBack;

    private DbUtil(Application application, String dbName, String passWord) {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(application, dbName);
        }
        if (passWord == null || passWord.isEmpty()) {
            mDaoMaster = new DaoMaster(mHelper.getWritableDb());
        } else {
            mDaoMaster = new DaoMaster(mHelper.getEncryptedReadableDb(passWord));
        }
        mDaoSession = mDaoMaster.newSession();

    }

    public static DbUtil getInstance(Application application) {
        return getInstance(application, DEFAULT_DATABASE_NAME);
    }

    public static DbUtil getInstance(Application application, String dbName) {

        return getInstance(application, dbName, "");
    }

    public static DbUtil getInstance(Application application, String dbName, String passWord) {

        if (mDbUtils == null) {
            synchronized (DbUtil.class) {
                if (mDbUtils == null) {
                    mDbUtils = new DbUtil(application, dbName, passWord);
                }
            }
        }

        return mDbUtils;
    }

    public <T> Query<T> getQuery(Class<T> claz){
        return getQueryBuilder(claz).build();
    }

    public <T> QueryBuilder<T> getQueryBuilder(Class<T> claz){
        return mDaoSession.queryBuilder(claz);
    }
    /**
     * 获取数据库Item的数量
     *
     * @return
     */
    public <T> Long count(Class<T> entityClaz) {
        setCurrentDao(entityClaz);
        return mAbstractDao.count();
    }

    /**
     * 插入一条数据
     *
     * @param dbEntity
     */
    public <T> void insert(T dbEntity) {
        setCurrentDao(dbEntity.getClass());
        mAbstractDao.insert(dbEntity);
    }

    /**
     * 插入一条数据
     *
     * @param dbEntity
     */
    public <T> void insertOrReplace(T dbEntity) {
        setCurrentDao(dbEntity.getClass());
        mAbstractDao.insertOrReplace(dbEntity);
    }

    /**
     * 插入多条数据
     *
     * @param entities
     */
    public <T> void insertTx(List<T> entities) {

        if (entities == null || entities.size() < 1) {
            return;
        }
        setCurrentDaoOfList(entities);
        mAbstractDao.insertInTx(entities);
    }

    public <T> void insertOrReplaceInTx(List<T> entities) {
        if (entities == null || entities.size() < 1) {
            return;
        }
        setCurrentDaoOfList(entities);
        mAbstractDao.insertOrReplaceInTx(entities);
    }

    /**
     * 删除单条数据
     *
     * @param entity
     */
    public <T> void delete(T entity) {
        setCurrentDao(entity.getClass());
        mAbstractDao.delete(entity);
    }

    /**
     * 删除特定ID的数据
     *
     * @param id
     */
    public <T> void deleteById(Class<T> entityClaz, long id) {
        setCurrentDao(entityClaz);
        mAbstractDao.deleteByKey(id);
    }

    /**
     * 删除多条数据
     *
     * @param entities
     */
    public <T> void deleteList(List<T> entities) {
        setCurrentDaoOfList(entities);
        mAbstractDao.deleteInTx(entities);
    }

    /**
     * 全部删除
     */
    public <T> void deleteAll(Class<T> claz) {
        setCurrentDao(claz);
        mAbstractDao.deleteAll();
    }

    /**
     * 更新单条数据
     *
     * @param entity
     */
    public <T> void updateData(final T entity) {
        setCurrentDao(entity.getClass());
        mAbstractDao.update(entity);
    }

    /**
     * 更新多条数据
     *
     * @param entities
     */
    public <T> void updateListData(Collection<T> entities) {
        setCurrentDaoOfList(entities);
        mAbstractDao.updateInTx(entities);
    }

    /**
     * 查询特定ID的数据
     *
     * @param id
     * @return
     */
    public <T> T queryById(Class<T> claz, long id) {
        setCurrentDao(claz);
        return (T) mAbstractDao.load(id);
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    public <T> List<T> queryAll(Class<T> claz) {
        setCurrentDao(claz);
        return mAbstractDao.loadAll();
    }

    public <T> List<T> queryAll(Class<T> claz,WhereCondition whereCondition) {
        setCurrentDao(claz);
        return mDaoSession.queryBuilder(claz)
                .where(whereCondition)
                .list();
    }

    public <T> List<T> queryAll(Class<T> claz,QueryBuilder<T> queryBuilder) {
        setCurrentDao(claz);
        return mDaoSession.queryBuilder(claz).list();
    }

    public <T> List<T> queryAll(Class<T> claz,Query<T> query) {
        setCurrentDao(claz);
       return query.list();
    }


    /**
     * 原生查询
     *
     * @param claz
     * @param whereString
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> queryRaw(Class<T> claz, String whereString, String[] params) {
        setCurrentDao(claz);
        return mAbstractDao.queryRaw(whereString, params);
    }

    /**
     * 异步操作的回调设置
     *
     * @param callBack
     * @param <T>
     * @return
     */
    public <T> DbUtil setDbCallBack(DbCallBack<T> callBack) {
        mCallBack = callBack;
        return this;
    }

    /**
     * 条件查询数据
     *
     * @param cls
     * @return
     */
    public <T> void queryAsync(Class<T> cls, WhereCondition whereCondition) {
        setCurrentDao(cls);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    List<T> list = new ArrayList<>();
                    list.add(((T) operation.getResult()));
                    mCallBack.onSuccess(list);
                } else if (operation.isFailed()) {
                    mCallBack.onFailed();
                }
            }
        });
        Query query = mDaoSession.queryBuilder(cls).where(whereCondition).build();
        asyncSession.queryUnique(query);
    }

    /**
     * 异步条件查询，通过使用 QueryBuilder 构造 Query
     * @param claz
     * @param builder
     * @param <T>
     */
    public <T> void queryAsyncAll(Class<T> claz, QueryBuilder<T> builder) {
        setCurrentDao(claz);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompleted() && mCallBack != null) {
                    List<T> result = (List<T>) operation.getResult();
                    mCallBack.onSuccess(result);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onFailed();
                }
            }
        });
        if (builder == null || builder.build() == null) {
            asyncSession.loadAll(claz);
        } else {
            asyncSession.queryList(builder.build());
        }
    }

    /**
     * 删除
     */
    public <T> void deleteAsyncSingle(T entry) {
        setCurrentDao(entry.getClass());
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.delete(entry);
    }

    /**
     * 批量删除
     */
    public <T> void deleteAsyncBatch(Class<T> cls, final List<T> list) {
        setCurrentDao(cls);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.deleteInTx(cls, list);
    }


    /**
     * 根据Id批量删除
     */
    public <T> void deleteByIdBatch(Class<T> claz, List<Long> longList) {
        setCurrentDao(claz);
        mAbstractDao.deleteByKeyInTx(longList);
    }

    /**
     * 删除所有数据
     */
    public <T> void deleteAsyncAll(Class<T> cls) {
        setCurrentDao(cls);
        final AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.deleteAll(cls);
    }

    /**
     * 插入一条数据
     */
    public <T> void insertAsyncSingle(final T entity) {
        setCurrentDao(entity.getClass());
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                mDaoSession.insert(entity);
            }
        });
    }

    /**
     * 批量插入
     */
    public <T> void insertAsyncBatch(final Class<T> cls, final List<T> userList) {
        setCurrentDao(cls);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                for (T object : userList) {
                    mDaoSession.insertOrReplace(object);
                }
            }
        });
    }

    /**
     * 更新一个数据
     */
    public <T> void updateAsyncSingle(Class<T> cls, T entry) {
        setCurrentDao(cls);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.update(entry);
    }

    /**
     * 批量更新数据
     */
    public <T> void updateAsyncBatch(final Class<T> cls, final List<T> tList) {
        setCurrentDao(cls);
        AsyncSession asyncSession = mDaoSession.startAsyncSession();
        asyncSession.setListenerMainThread(new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                if (operation.isCompletedSucessfully() && mCallBack != null) {
                    mCallBack.onNotification(true);
                } else if (operation.isFailed() && mCallBack != null) {
                    mCallBack.onNotification(false);
                }
            }
        });
        asyncSession.updateInTx(cls, tList);
    }

    /**
     * 关闭DaoSession
     */
    private void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    /**
     * 关闭Helper
     */
    private void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    /**
     * 关闭所有的操作
     */
    public void closeConnection() {
        closeDaoSession();
        closeHelper();
    }

    /**
     * 数据库不加密
     *
     * @param entityClass 根据 entityClass 获取相应的 xxDao
     * @return mDbUtils
     */
    @Deprecated
    public DbUtil create(Class<?> entityClass) {

        if (mHelper == null) {
            throw new NullPointerException("You need to init mHelper first!");
        }

        mAbstractDao = mDaoSession.getDao(entityClass);
        return mDbUtils;
    }

    private <T> void setCurrentDao(Class<T> entityClass) {
        if (mHelper == null) {
            throw new NullPointerException("You need to init mHelper first!");
        }
        mAbstractDao = mDaoSession.getDao(entityClass);
    }

    private <T> void setCurrentDaoOfList(Collection<T> entities) {
        if (entities != null && entities.size() > 1) {
            Iterator<T> iterator = entities.iterator();
            T next = iterator.next();
            setCurrentDao(next.getClass());
        }
    }

//    /**
//     * 使用 QueryBuilder 构造查询条件
//     *
//     * @param claz
//     * @param config
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> queryWithAQueryBuilder(Class<T> claz, QueryConfig config) {
//        return getQuery(claz, config).list();
//    }
//
//    /**
//     * 构造 Query
//     *
//     * @param claz
//     * @param config
//     * @param <T>
//     * @return
//     */
//    public <T> Query<T> getQuery(Class<T> claz, QueryConfig config) {
//        QueryBuilder<T> queryBuilder = mDaoSession.queryBuilder(claz);
//        if (config != null) {
//            if (config.distinct) {
//                queryBuilder.distinct();
//            }
//            if (config.limit > 0) {
//                queryBuilder.limit(config.limit);
//            }
//            if (config.offset > 0) {
//                queryBuilder.offset(config.offset);
//            }
//            if (config.orderDescProperties != null) {
//                queryBuilder.orderDesc(config.orderAscProperties);
//            }
//            if (config.orderAscProperties != null) {
//                queryBuilder.orderAsc(config.orderAscProperties);
//            }
//            if (config.whereCondition != null) {
//                queryBuilder.where(config.whereCondition, config.whereConditionsMore);
//            } else if (config.orCondition1 != null && config.orCondition2 != null) {
//                queryBuilder.or(config.orCondition1, config.orCondition2, config.orConditionsMore);
//            } else if (config.andCondition1 != null && config.andCondition2 != null) {
//                queryBuilder.and(config.andCondition1, config.andCondition2, config.andConditionsMore);
//            }
//            //join
//            if (config.sourceProperty != null && config.destinationEntityClass != null) {
//                if (config.destinationProperty == null) {
//                    queryBuilder.join(config.sourceProperty, config.destinationEntityClass)
//                            .where(config.joinCondition, config.joinConditionMore);
//                } else {
//                    queryBuilder.join(config.sourceProperty, config.destinationEntityClass, config.destinationProperty)
//                            .where(config.joinCondition, config.joinConditionMore);
//                }
//            } else if (config.destinationEntityClass != null && config.destinationProperty != null) {
//                queryBuilder.join(config.destinationEntityClass, config.destinationProperty)
//                        .where(config.joinCondition, config.joinConditionMore);
//            }
//
//        }
//
//        return queryBuilder.build();
//    }
//
//    /**
//     * 对 QueryBuilder 又封装了一层，解决分模块时，引包采用 implementation 方式，获取不到 greenDAO 的类
//     */
//    public static class QueryConfig {
//
//        public int limit;
//        // 需要和 limit 配合使用
//        public int offset;
//        public boolean distinct;
//
//        /**
//         * where 查询
//         */
//        public WhereCondition whereCondition;
//        public WhereCondition[] whereConditionsMore;
//
//        /**
//         * or 查询
//         */
//        public WhereCondition orCondition1;
//        public WhereCondition orCondition2;
//        public WhereCondition[] orConditionsMore;
//
//        /**
//         * and 查询
//         */
//        public WhereCondition andCondition1;
//        public WhereCondition andCondition2;
//        public WhereCondition[] andConditionsMore;
//
//        public Property[] orderDescProperties;
//        public Property[] orderAscProperties;
//
//        /**
//         * join 查询
//         */
//        public Property sourceProperty;
//        public Class destinationEntityClass;
//        public Property destinationProperty;
//        public WhereCondition joinCondition;
//        public WhereCondition[] joinConditionMore;
//
//        public QueryConfig() {
//        }
//
//        public QueryConfig limit(int limit) {
//            this.limit = limit;
//            return this;
//        }
//
//        public QueryConfig offset(int offset) {
//            this.offset = offset;
//            return this;
//        }
//
//        public QueryConfig distinct(boolean distinct) {
//            this.distinct = distinct;
//            return this;
//        }
//
//        public QueryConfig where(WhereCondition whereCondition, WhereCondition... conditionMore) {
//            this.whereCondition = whereCondition;
//            this.whereConditionsMore = conditionMore;
//            return this;
//        }
//
//        public QueryConfig or(WhereCondition orCondition1, WhereCondition orCondition2
//                , WhereCondition... conditionMore) {
//            this.orCondition1 = orCondition1;
//            this.orCondition2 = orCondition2;
//            this.orConditionsMore = conditionMore;
//            return this;
//        }
//
//        public QueryConfig and(WhereCondition andCondition1, WhereCondition andCondition2
//                , WhereCondition... conditionMore) {
//            this.andCondition1 = andCondition1;
//            this.andCondition2 = andCondition2;
//            this.andConditionsMore = conditionMore;
//            return this;
//        }
//
//        public QueryConfig orderDesc(Property[] properties) {
//            this.orderDescProperties = properties;
//            return this;
//        }
//
//        public QueryConfig orderAsc(Property[] properties) {
//            this.orderAscProperties = properties;
//            return this;
//        }
//
//
//        /**
//         * 通过联合另一个表扩展查询，sourceProperty
//         * 和需要联合的表中的 destinationEntityClass 的主键匹配
//         *
//         * @param sourceProperty
//         * @param destinationEntityClass
//         * @param joinCondition
//         * @param joinConditionMore
//         * @param <J>
//         * @return
//         */
//        public <J> QueryConfig join(Property sourceProperty, Class<J> destinationEntityClass
//                , WhereCondition joinCondition, WhereCondition... joinConditionMore) {
//            this.sourceProperty = sourceProperty;
//            this.destinationEntityClass = destinationEntityClass;
//            this.joinCondition = joinCondition;
//            this.joinConditionMore = joinConditionMore;
//            return this;
//        }
//
//
//        /**
//         * 和上面的 join 方法类似，多了一个参数 destinationProperty，这个属性相当于自己指定主键；
//         * 也就是说 sourceProperty 和 destinationEntityClass表中的 destinationProperty 字段匹配；
//         * 但不限与destinationEntityClass的主键，也可以是其他字段
//         *
//         * @param sourceProperty
//         * @param destinationEntityClass
//         * @param destinationProperty
//         * @param joinCondition
//         * @param joinConditionMore
//         * @param <J>
//         * @return
//         */
//        public <J> QueryConfig join(Property sourceProperty, Class<J> destinationEntityClass
//                , Property destinationProperty, WhereCondition joinCondition, WhereCondition... joinConditionMore) {
//            this.sourceProperty = sourceProperty;
//            this.destinationEntityClass = destinationEntityClass;
//            this.destinationProperty = destinationProperty;
//            this.joinCondition = joinCondition;
//            this.joinConditionMore = joinConditionMore;
//            return this;
//        }
//
//        /**
//         * 需要查询的表中的主键和联合表中字段匹配，即 destinationProperty
//         *
//         * @param destinationEntityClass
//         * @param destinationProperty
//         * @param joinCondition
//         * @param joinConditionMore
//         * @param <J>
//         * @return
//         */
//        public <J> QueryConfig join(Class<J> destinationEntityClass, Property destinationProperty
//                , WhereCondition joinCondition, WhereCondition... joinConditionMore) {
//            this.destinationProperty = destinationProperty;
//            this.destinationEntityClass = destinationEntityClass;
//            this.joinCondition = joinCondition;
//            this.joinConditionMore = joinConditionMore;
//            return this;
//        }
//
//    }
}
