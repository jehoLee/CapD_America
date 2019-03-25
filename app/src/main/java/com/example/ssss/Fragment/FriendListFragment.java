package com.example.ssss.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ssss.R;

public class FriendListFragment extends Fragment {
    SQLiteDatabase database;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_friend_list,container,false);
        get_list(v);
        return v;
    }


    public void get_list(View v){
        String[] items = new String[] {"철수", "영희", "바둑이","철구"};   //DB의 값들을 읽어옴.
        ListAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
        ListView listView = v.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = String.valueOf(parent.getItemAtPosition(position));

                    }
                }
        );
    }


    public void selectData(String tableName){
        if(database != null){
            String sql = "select name, age, mobile from " + tableName;
            Cursor cursor = database.rawQuery(sql, null); //표준 자바에 접근하기 위해 result set과 유사
            println("조회된 데이터 개수 : " + cursor.getCount());

            for (int i = 0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -> " + name + ", "+ age + ", " + mobile);
            }
            cursor.close();
        }
    }
    public void println(String data){
        textView.append(data + "\n");
    }
}
