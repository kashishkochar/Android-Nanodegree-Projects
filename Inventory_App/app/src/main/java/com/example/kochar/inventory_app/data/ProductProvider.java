package com.example.kochar.inventory_app.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;

import static com.example.kochar.inventory_app.data.ProductContractor.*;

/**
 * Created by kochar on 7/5/2018.
 */

public class ProductProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int PRODUCT = 100;
    private static final int PRODUCT_ID = 101;

    static {
        sUriMatcher.addURI(CONTENY_AUTHORITY,PATH_PRODUCT,PRODUCT);
        sUriMatcher.addURI(CONTENY_AUTHORITY,PATH_PRODUCT + "/#" , PRODUCT_ID);
    }
    public ProductDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new ProductDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //return null;
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);

        switch (match){
            case PRODUCT:
                cursor = database.query(ProductEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case PRODUCT_ID:
                selection = ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(ProductEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

                default:
                    throw new IllegalArgumentException("Canot query unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        final int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCT:
                return insertpet(uri,values);

                default:
                    throw new IllegalArgumentException("Insertion is not supported for "+ uri);
        }

    }

    private Uri insertpet(Uri uri, ContentValues values)
    {

        String name = values.getAsString(ProductEntry.NAME_OF_PRODUCT);
        if(name == null)
            throw new IllegalArgumentException("requires a name");

        Integer Cost = values.getAsInteger(ProductEntry.COST_OF_PRODUCT);
        if(Cost == null || Cost<0)
        {
            throw new IllegalArgumentException("not valid");
        }

        Integer quantity = values.getAsInteger(ProductEntry.QUANTITY_OF_PRODUCT);
        if(quantity == null || quantity<0)
        {
            throw new IllegalArgumentException("not valid");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(ProductEntry.TABLE_NAME,null,values);

        if(id ==-1)
        {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  ContentUris.withAppendedId(uri,id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
       // return 0;

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match){
            case PRODUCT:
                rowsDeleted = database.delete(ProductEntry.TABLE_NAME,selection,selectionArgs);
                break;

            case PRODUCT_ID:
                selection = ProductEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(ProductEntry.TABLE_NAME,selection,selectionArgs);
                break;

                default:
                    throw new IllegalArgumentException("Dellition is not possiblr" + uri);
        }

        if(rowsDeleted !=0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {


        if(values.containsKey(ProductEntry.NAME_OF_PRODUCT)){
            String name = values.getAsString(ProductEntry.NAME_OF_PRODUCT);
            if(name == null)
            {
                throw new IllegalArgumentException("pet require a name");
            }
        }

        if(values.containsKey(ProductEntry.QUANTITY_OF_PRODUCT)){
            Integer weight = values.getAsInteger(ProductEntry.QUANTITY_OF_PRODUCT);
            if(weight != null && weight<0)
            {
                throw new IllegalArgumentException("pet require valid weight");
            }
        }

        if(values.containsKey(ProductEntry.COST_OF_PRODUCT)){

            Integer cost = values.getAsInteger(ProductEntry.COST_OF_PRODUCT);
            if(cost != null && cost<0){
                throw new IllegalArgumentException("pet require valid name");
            }
        }

        if(values.size() == 0)
        {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        int rowsupdated = database.update(ProductEntry.TABLE_NAME,values,selection,selectionArgs);
        if(rowsupdated!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsupdated;
    }
}
