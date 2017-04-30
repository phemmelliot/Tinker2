package com.example.toyin.tinker.Data;

import android.provider.BaseColumns;

/**
 * Created by Toyin on 09/04/2017.
 */
public final class Tinker {
    private Tinker(){}

    public static final class TinkerEntry implements BaseColumns{

        public final static String TABLE_NAME = "tinker";
        public final static String ID = BaseColumns._ID;
        public final static String Column_name = "name";
        public final static String Column_score = "score";
    }

}
