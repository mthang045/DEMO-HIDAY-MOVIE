package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView; // ✅ thêm dòng này

import com.example.myapplication.adapter.MovieAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<String> movieList;

    // ✅ Biến mới cho menu user
    private ImageView avatarBtn;
    private LinearLayout dropdownMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ======= RECYCLERVIEW =======
        recyclerView = findViewById(R.id.recyclerCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieList = Arrays.asList("Phim 1", "Phim 2", "Phim 3", "Phim 4");
        adapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(adapter);

        // ======= USER MENU DROPDOWN =======
        avatarBtn = findViewById(R.id.userAvatarBtn);
        dropdownMenu = findViewById(R.id.userDropdownMenu);

        avatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropdownMenu.getVisibility() == View.GONE) {
                    dropdownMenu.setVisibility(View.VISIBLE);
                } else {
                    dropdownMenu.setVisibility(View.GONE);
                }
            }
        });
    }
}
