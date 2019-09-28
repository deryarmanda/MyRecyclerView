package com.dercoding.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();
    private String title = "Mode List";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerList();

    }

    // LIST

    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallBack(new ListHeroAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    //GRID

    private void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallBack(new GridHeroAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);

            }
        });
    }

    //Recycler CARD VIEW
    private void showRecyclerCardView(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHeroes.setAdapter(cardViewHeroAdapter);
    }


    // MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }


    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                // MEMANGGIL LIST METOD
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                // MEMANGGIL GRID METOD
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                //MEMANGGIL CARD VIEW METOD
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        setActionBarTitle(title);
    }

    private void setActionBarTitle(String title)    {
        if (getSupportActionBar() !=null)   {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(Hero hero)    {
        Toast.makeText(this, "Kamu Memilih " + hero.getName(), Toast.LENGTH_SHORT).show();
    }
}
