package com.example.kochar.inventory_app.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.id;

import com.example.kochar.inventory_app.R;

/**
 * Created by kochar on 7/5/2018.
 */

public class ProductAdapter extends CursorAdapter {

    public ProductAdapter(Context context, Cursor c)
    {
        super(context , c ,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView tv1 = (TextView) view.findViewById(R.id.name);
        TextView tv2 = (TextView) view.findViewById(R.id.cost);
        TextView tv3 = (TextView) view.findViewById(R.id.quantity);

        int namecolumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.NAME_OF_PRODUCT);
        int costcolumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.COST_OF_PRODUCT);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT);

        String ProductName = "Name: " + cursor.getString(namecolumnIndex);
        String ProductCost = "Cost: $" + cursor.getString(costcolumnIndex);
        String ProductQuantity = cursor.getString(quantityColumnIndex);

        tv1.setText(ProductName);
        tv2.setText(ProductCost);
        tv3.setText(ProductQuantity);

        final Uri currentProducyUri = ContentUris.withAppendedId(ProductContractor.ProductEntry.CONTENT_URI,id);
        final int id = cursor.getInt(cursor.getColumnIndex(ProductContractor.ProductEntry._ID));
        final int quantityItem = Integer.parseInt(ProductQuantity);

        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();

                if(quantityItem > 0)
                {
                    int leftquantity = quantityItem;
                    values.put(ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT,--leftquantity);
                    Uri uri = ContentUris.withAppendedId(ProductContractor.ProductEntry.CONTENT_URI, id);

                    context.getContentResolver().update(uri,values,null,null);
                    context.getContentResolver().notifyChange(currentProducyUri,null);

                }
                else{
                    Toast.makeText(context, "Items Finish", Toast.LENGTH_SHORT).show();
                }
                context.getContentResolver().notifyChange(ProductContractor.ProductEntry.CONTENT_URI,null);

            }
        });

    }
}
