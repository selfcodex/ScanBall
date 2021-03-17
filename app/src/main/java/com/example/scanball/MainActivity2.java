package com.example.scanball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scanball.utils.ConnectionDec;
import com.example.scanball.utils.Custom_ArrayAdapter;
import com.example.scanball.utils.ListItemClass;
import com.example.scanball.utils.MenuFragmentList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//главное окно
    public class MainActivity2 extends AppCompatActivity implements NavItemSelectedListener {

        private Document doc;
        private Thread secondTread;
        private Runnable runnable;
        private ListView listView;
        private Custom_ArrayAdapter adapter;
        private Short[] array = new Short[13];
        private List<ListItemClass> listItemmain;
        SwipeRefreshLayout swipeRefreshLayout;
        private int ref = 13;

        ConnectionDec cd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_main2);
            setupMenu();
            Init(ref);

            cd = new ConnectionDec(this);
            if (!cd.isConnected()) {
                openActivity1();
            }
            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.Swipe);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (!cd.isConnected()) {
                        Context context = getApplicationContext();
                        CharSequence text = "Интернет соединение потяряно!";  //вывод сообщения об отсутчтвии интернет соединения
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Toast.makeText(context, text, duration).show();
                    } else {
                        Init(ref);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 400);
                }
            });


        }
        //инициализария
        private void Init(final int table_num)
        {
            listView = findViewById(R.id.listView);
            listItemmain = new ArrayList<>();
            adapter = new Custom_ArrayAdapter(this, R.layout.list_view_item,listItemmain ,getLayoutInflater());
            listView.setAdapter( adapter );
            runnable = new Runnable() {
                @Override
                public void run() {
                    getWeb(table_num);
                }
            };


            secondTread = new Thread(runnable);
            secondTread.start();



        }
        //поиск нужной таблицы
        private void getWeb(int numer)
        {
            try {
                doc = Jsoup.connect("https://www.euro-football.ru/tables/").get();
                Elements Tables = doc.getElementsByTag("tbody");
                Element Country_table = Tables.get(numer);  //begin from 13, end 27
                for (int i = 0; i < Country_table.childrenSize(); i++) {
                    ListItemClass items = new ListItemClass();
                    items.setNumber_place(Country_table.children().get(i).child(0).text());
                    items.setName_club(Country_table.children().get(i).child(1).text());
                    items.setKolvo_games(Country_table.children().get(i).child(2).text());
                    items.setKolvo_scores(Country_table.children().get(i).child(3).text());
                    listItemmain.add(items);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        private void setupMenu() {
            FragmentManager fm = getSupportFragmentManager();
            MenuFragmentList mMenuFragment = (MenuFragmentList) fm.findFragmentById(R.id.id_container_menu);
            if (mMenuFragment == null) {
                mMenuFragment = new MenuFragmentList();
                mMenuFragment.setNavItemSelectedListener(this);
                fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
            }
        }
        //выбор таблицы
        @Override
        public void OnNavItemSelectedListener(MenuItem item) {
            if (!cd.isConnected()) {
                openActivity1();
            } else {
            int i = 0;
            for (short j = 13; j<26; j++) {
                array[i] = j;
                i++;
            }
            switch(item.getItemId()){
                case R.id.id_RPL:
                    Init(array[0]);
                    ref=array[0];
                    break;
                case R.id.id_APL:
                    Init(array[1]);
                    ref=array[1];
                    break;
                case R.id.id_T3:
                    Init(array[2]);
                    ref=array[2];
                    break;
                case R.id.id_T4:
                    Init(array[3]);
                    ref=array[3];
                    break;
                case R.id.id_T5:
                    Init(array[4]);
                    ref=array[4];
                    break;
                case R.id.id_T6:
                    Init(array[5]);
                    ref=array[5];
                    break;
                case R.id.id_T7:
                    Init(array[6]);
                    ref=array[6];
                    break;
                case R.id.id_T8:
                    Init(array[7]);
                    ref=array[7];
                    break;
                case R.id.id_T9:
                    Init(array[8]);
                    ref=array[8];
                    break;
                case R.id.id_T10_:
                    Init(array[9]);
                    ref=array[9];
                    break;
                case R.id.id_T11:
                    Init(array[10]);
                    ref=array[10];
                    break;
                case R.id.id_T12:
                    Init(array[11]);
                    ref=array[11];
                    break;
                case R.id.id_T13:
                    Init(array[12]);
                    ref=array[12];
                    break;
            }
        }
        }
        //открытие окна ошибки
        public void openActivity1(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}

