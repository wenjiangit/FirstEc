package com.douliu.latte.ec.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 *
 * Created by douliu on 2017/8/15.
 *
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}
