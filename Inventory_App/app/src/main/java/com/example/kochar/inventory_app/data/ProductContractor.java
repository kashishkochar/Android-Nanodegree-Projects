package com.example.kochar.inventory_app.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kochar on 7/5/2018.
 */

public class ProductContractor {

    private ProductContractor()
    {

    }

    public static final String CONTENY_AUTHORITY = "com.example.kochar.inventory_app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENY_AUTHORITY);

    public  static final String PATH_PRODUCT = "Product";

    public static final class ProductEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PRODUCT);
        public final static String _ID = BaseColumns._ID;

        public final static String TABLE_NAME = "Product";
        public final static String NAME_OF_PRODUCT = "Name";
        public final static String QUANTITY_OF_PRODUCT = "Quantity";
        public final static String COST_OF_PRODUCT = "Cost";
        public final static String IMAGE = "Image";

    }
}
