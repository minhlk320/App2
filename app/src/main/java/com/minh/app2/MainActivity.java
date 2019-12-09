package com.minh.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText_maso, editText_tieude, editText_masotacgia;
    Button button_select, button_save, button_update, button_delete, button_exit;
    GridView gridView_display;
    private ArrayAdapter<String> adapter;

    static final String AUTHORITY = "book";
    static final String CONTENT_PATH = "bookdata";
    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map();

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] id ={editText_maso.getText().toString()};
                ArrayList<String> list_string = new ArrayList<>();
                String uri = "content://book/bookdata";
                Uri book = Uri.parse(uri);

                if(!editText_maso.getText().toString().isEmpty()){
                    Cursor cursor = getContentResolver().query(book, null,"id_book=?",id, "title");
                    if (cursor != null) {
                        cursor.moveToFirst();
                        do {
                            try{
                                list_string.add(cursor.getInt(0) + "");
                                list_string.add(cursor.getString(1) + "");
                                list_string.add(cursor.getInt(2) + "");
                            }catch(Exception e){
                                list_string.add("Không có dữ liệu");
                            }
                        } while (cursor.moveToNext());
                        adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, list_string);
                        gridView_display.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Không có kết quả !", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (editText_maso.getText().toString().isEmpty()){
                    Cursor cursor = getContentResolver().query(book, null,null, null, "title");
                    if (cursor != null) {
                        cursor.moveToFirst();
                        do {
                            try{
                                list_string.add(cursor.getInt(0) + "");
                                list_string.add(cursor.getString(1) + "");
                                list_string.add(cursor.getInt(2) + "");
                            }catch(Exception e){
                                list_string.add("Không có dữ liệu");
                            }

                        } while (cursor.moveToNext());
                        adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, list_string);
                        gridView_display.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Không có kết quả !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void map(){
        editText_maso = (EditText) findViewById(R.id.editText_maso);
        editText_tieude = (EditText) findViewById(R.id.editText_tieude);
        editText_masotacgia = (EditText) findViewById(R.id.editText_masotacgia);

        button_select = (Button) findViewById(R.id.button_select);
        button_save = (Button) findViewById(R.id.button_save);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_exit = (Button) findViewById(R.id.button_exit);

        gridView_display = (GridView) findViewById(R.id.gridView_display);
    }

}
