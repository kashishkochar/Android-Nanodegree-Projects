package com.example.kochar.inventory_app;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kochar.inventory_app.data.ProductContractor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * Created by kochar on 6/27/2018.
 */

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    Uri CurrentProductUri;
    public TextView Quantity_Set;
    public EditText NAME;
    EditText COST;

    Button INCREMENT;
    Button DECREMENT;
    Button SUBMIT;

    private boolean mPetHasChanged = false;
    ImageView mImageView;
    Bitmap imageBitMap;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.editor_activity);

        NAME = (EditText) findViewById(R.id.name);
        COST = (EditText) findViewById(R.id.cost);
        INCREMENT = (Button) findViewById(R.id.increment);
        DECREMENT = (Button) findViewById(R.id.decrement);
        Quantity_Set = (TextView) findViewById(R.id.quantity_set);

        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPetHasChanged = true;
                return false;
            }
        };

        mImageView = (ImageView) findViewById(R.id.Image);
        Button Gallery = (Button) findViewById(R.id.Gallery);

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });

        INCREMENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = "";
                Text = Quantity_Set.getText().toString().trim();
                int value = Integer.parseInt(Text);
                if(value<1000)
                {
                    value++;
                }
                else
                {
                    Toast.makeText(EditorActivity.this,"OUT OF BOUND" , Toast.LENGTH_SHORT);
                }
                displayvalue(value);
            }
        });

        DECREMENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = Quantity_Set.getText().toString().trim();
                int value = Integer.parseInt(Text);

                if(value>0)
                {
                    value--;
                }
                else
                {
                    Toast.makeText(EditorActivity.this,"NO ITEM",Toast.LENGTH_SHORT);
                }
                displayvalue(value);
            }
        });

        Intent intent = getIntent();
        CurrentProductUri = intent.getData();
        if(CurrentProductUri == null)
        {
            setTitle("PRODUCT ADD");
        }
        else
        {
            setTitle("CHANGE PRODUCT");
            getLoaderManager().initLoader(0,null,this);
        }

        NAME.setOnTouchListener(mTouchListener);
        COST.setOnTouchListener(mTouchListener);
        Quantity_Set.setOnTouchListener(mTouchListener);

    }

    private void selectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    mImageView = (ImageView) findViewById(R.id.Image);
                    mImageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayvalue(int num)
    {

        Quantity_Set.setText("" + num);

    }

    public void InsertProduct()
    {

        String mName = NAME.getText().toString();
        String mCost = COST.getText().toString();
        String mQuantity = Quantity_Set.getText().toString();

        byte[] imageByteArray = new byte[0];
        if(mImageView.getDrawable() == null)
        {
            Toast.makeText(this,"please insert image" , Toast.LENGTH_SHORT).show();
        }
        else if(mName.length() == 0)
        {
            Toast.makeText(this,"please Enter Name" , Toast.LENGTH_SHORT).show();
        }
        else if(mCost.length() == 0)
        {
            Toast.makeText(this,"please Enter Cost" , Toast.LENGTH_SHORT).show();
        }
        else if(mQuantity.length() == 0)
        {
            Toast.makeText(this,"please Enter Quantity" , Toast.LENGTH_SHORT).show();
        }
        else
        {

            imageBitMap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitMap.compress(Bitmap.CompressFormat.PNG,100,bos);
            imageByteArray = bos.toByteArray();

            ContentValues values = new ContentValues();

            values.put(ProductContractor.ProductEntry.NAME_OF_PRODUCT,mName);
            values.put(ProductContractor.ProductEntry.COST_OF_PRODUCT,Integer.parseInt(mCost));
            values.put(ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT,mQuantity);
            values.put(ProductContractor.ProductEntry.IMAGE,imageByteArray);

            Uri newuri = getContentResolver().insert(ProductContractor.ProductEntry.CONTENT_URI, values);

            finish();

        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                ProductContractor.ProductEntry._ID,
                ProductContractor.ProductEntry.NAME_OF_PRODUCT,
                ProductContractor.ProductEntry.COST_OF_PRODUCT,
                ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT,
                ProductContractor.ProductEntry.IMAGE

        };

        return new CursorLoader(this,
              CurrentProductUri,
                projection,
                null,
                null,
                null
        );

    }

    @Override
    public void onBackPressed() {
        if(!mPetHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        };

        showUnsavedChangesDialog(discardButtonClickListener);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if(cursor == null || cursor.getCount()<1)
        {
            return;
        }

        if(cursor.moveToFirst())
        {

            int nameColumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.NAME_OF_PRODUCT);
            int costColumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.COST_OF_PRODUCT);
            int quantityColumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT);
            int imageColumnIndex = cursor.getColumnIndex(ProductContractor.ProductEntry.IMAGE);

            String name = cursor.getString(nameColumnIndex);
            String cost = cursor.getString(costColumnIndex);
            String quantityx = cursor.getString(quantityColumnIndex);
            byte[] imagebyteArray = cursor.getBlob(imageColumnIndex);

            NAME.setText(name);
            COST.setText(cost);
            Quantity_Set.setText(quantityx);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyteArray,0,imagebyteArray.length);
            mImageView.setImageBitmap(bitmap);


        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editoractivity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Save_new_data:
                InsertProduct();
                return true;

            case R.id.Update_data:
                saveData();
                return true;

            case R.id.action_delete_curent:
                showDeleteconfirmationDialog();
                return true;

            case R.id.Email_intent:
                EmailIntent();
                return true;

            case android.R.id.home:
                if(!mPetHasChanged){
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }
                };
                showUnsavedChangesDialog(discardButtonClickListner);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(CurrentProductUri == null){
            MenuItem menuItem = menu.findItem(R.id.action_delete_curent);
            MenuItem menuItem1 = menu.findItem(R.id.Update_data);
            MenuItem menuItem2 = menu.findItem(R.id.Email_intent);

            menuItem1.setVisible(false);
            menuItem2.setVisible(false);
            menuItem.setVisible(false);
        }
        return true;
    }

    private void EmailIntent()
    {
        String name = NAME.getText().toString();
        String cost = COST.getText().toString();
        String quan = Quantity_Set.getText().toString().trim();
        String Msg = "Your Product Name is: " + name + "\nYour Product Cost is: " + cost + "\nYour Product Quantity is: " + quan;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Product Detail is: ");
        intent.putExtra(Intent.EXTRA_TEXT,Msg);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void deleteproduct(){

        if(CurrentProductUri != null){

            int rowDeleted = getContentResolver().delete(CurrentProductUri,null,null);

            if(rowDeleted == 0)
            {
                Toast.makeText(this,getString(R.string.editor_delete_pet_failed), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,getString(R.string.editor_delete_pet_succesfull),Toast.LENGTH_SHORT).show();
            }
        }
        finish();

    }

    private void saveData(){

        String nameString = NAME.getText().toString().trim();
        String Cost = COST.getText().toString().trim();
        String Quantity = Quantity_Set.getText().toString().trim();

        if(mImageView.getDrawable() == null){
            Toast.makeText(this,"you must upload an image",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Cost.length() == 0){
            Toast.makeText(this,"Please Insert Valid Cost",Toast.LENGTH_SHORT).show();
        }
        else if(nameString.length() == 0){
            Toast.makeText(this,"Please Insert Valid Name",Toast.LENGTH_SHORT).show();
        }
        else {

            Bitmap imageBitMap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitMap.compress(Bitmap.CompressFormat.PNG,100,bos);
            byte[] imageByteArray = bos.toByteArray();
            if(nameString == null || nameString.equals("")){
                Toast.makeText(this,"Please fill name",Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = new ContentValues();
            values.put(ProductContractor.ProductEntry.NAME_OF_PRODUCT,nameString);
            values.put(ProductContractor.ProductEntry.COST_OF_PRODUCT,Cost);
            values.put(ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT,Quantity);
            values.put(ProductContractor.ProductEntry.IMAGE,imageByteArray);
            finish();

            if(CurrentProductUri == null) {
                Uri newUri = getContentResolver().insert(ProductContractor.ProductEntry.CONTENT_URI, values);

                if (newUri == null) {
                    Toast.makeText(this, getString(R.string.editor_insert_pet_failed), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.editor_delete_pet_succesfull), Toast.LENGTH_SHORT).show();
                }
            }
                else {
                    int rowsAffected = getContentResolver().update(CurrentProductUri,values,null,null);

                    if(rowsAffected == 0)
                    {
                        Toast.makeText(this,getString(R.string.editor_update_pet_failed),Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,getString(R.string.editor_update_pet_succesfull),Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }

        private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListner) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.unsaved_changes_dialog_msg);
            builder.setPositiveButton(R.string.discard,discardButtonClickListner);
            builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(dialog != null)
                    {
                        dialog.dismiss();
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


    }

    private void showDeleteconfirmationDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteproduct();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null)
                {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
