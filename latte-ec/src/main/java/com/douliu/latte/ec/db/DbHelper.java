package com.douliu.latte.ec.db;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.Arrays;

/**
 * Created by douliu on 2017/8/15.
 */

public class DbHelper {


    /**
     * 通用的数据库保存操作
     *
     * @param clazz   数据的类型,操作的表名
     * @param models  需要保存的数据数组
     * @param <Model> 数据模型
     */
    @SuppressWarnings("unchecked")
    public static <Model extends BaseModel> void save(final Class<Model> clazz, final Model... models) {
        if (models == null || models.length == 0) {
            return;
        }
        //保存到数据库
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        //开启线程池执行保存操作
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clazz)
                        .saveAll(Arrays.asList(models));
            }
        }).build().execute();
    }

}
