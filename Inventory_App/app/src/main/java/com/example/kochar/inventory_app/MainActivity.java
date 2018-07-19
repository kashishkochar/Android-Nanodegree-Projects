package com.example.kochar.inventory_app;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
//import android.support.design.widget.FloatingActionButton;

import com.example.kochar.inventory_app.data.ProductAdapter;
import com.example.kochar.inventory_app.data.ProductContractor;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Editor = (Button) findViewById(R.id.fab);
        Editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                startActivity(intent);

            }
        });
        ListView petListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);
        mProductAdapter = new ProductAdapter(this,null);

        petListView.setAdapter(mProductAdapter);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                Uri URIS = ContentUris.withAppendedId(ProductContractor.ProductEntry.CONTENT_URI,id);
                intent.setData(URIS);
                startActivity(intent);

            }
        });

        getLoaderManager().initLoader(0,null,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.catalogactivity,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.delete_All_Pets:
                deleteAllProducts();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                ProductContractor.ProductEntry._ID,
                ProductContractor.ProductEntry.NAME_OF_PRODUCT,
                ProductContractor.ProductEntry.COST_OF_PRODUCT,
                ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT

        };

        return new CursorLoader(this,
                ProductContractor.ProductEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mProductAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mProductAdapter.swapCursor(null);

    }

    private void deleteAllProducts()
    {
        int rowsDeleted = getContentResolver().delete(ProductContractor.ProductEntry.CONTENT_URI,null,null);
        Log.v("catalogactivity", rowsDeleted + "rows deleted from pet database");
    }
}
