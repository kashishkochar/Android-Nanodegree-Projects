package com.example.kochar.habbittracker.habbit;

import android.provider.BaseColumns;

/**
 * Created by kochar on 6/23/2018.
 */

public final class HabbitContract {
    private HabbitContract(){}

    public static final class HabbitEntry implements BaseColumns{
        public final static String TABLE_NAME = "habbits";
        public final static String HABBIT_NAME = "habbit name";
        public final static String _ID = BaseColumns._ID;
        public final static String monday = "MONDAY";
        public final static String tuesday = "TUESDAY";
        public final static String wednesday = "WEDNESDAY";
        public final static String thursday = "THURSDAY";
        public final static String friday = "FRIDAY";
        public final static String saturday = "SATURDAY";
        public final static String sunday = "SUNDAY";
        public static final int doing = 1;
        public static final int not_doing = 0;
    }
}
