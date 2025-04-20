package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.myapplication.adapter.MovieAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.core.widget.NestedScrollView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // ======= RECYCLER VIEW =======
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<String> movieList;

    // ======= MINI PLAYER =======
    private View miniPlayer;
    private ImageView miniClose;

    // ======= USER MENU DROPDOWN =======
    private ImageView avatarBtn;
    private LinearLayout dropdownMenu;

    // ======= COMMON CONTROLS =======
    private TextView commonText;
    private EditText commonEdit;
    private WebView commonWeb;
    private RadioGroup commonRadioGroup;
    private CheckBox commonCheck;
    private Button commonBtn;

    // ======= NÚT MỞ YOUTUBE APP =======
    private Button openYoutubeBtn;

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

        avatarBtn.setOnClickListener(v -> {
            if (dropdownMenu.getVisibility() == View.GONE) {
                dropdownMenu.setVisibility(View.VISIBLE);
            } else {
                dropdownMenu.setVisibility(View.GONE);
            }
        });

        // ======= MINI PLAYER =======
        miniPlayer = findViewById(R.id.miniPlayer);
        miniClose = findViewById(R.id.miniClose);

        miniClose.setOnClickListener(v -> miniPlayer.setVisibility(View.GONE));

        // ======= COMMON CONTROLS =======
        commonText = findViewById(R.id.commonText);
        commonEdit = findViewById(R.id.commonEdit);
        commonWeb = findViewById(R.id.commonWeb);
        commonRadioGroup = findViewById(R.id.commonRadioGroup);
        commonCheck = findViewById(R.id.commonCheck);
        commonBtn = findViewById(R.id.commonBtn);

        // ======= WEBVIEW - CHẠY LINK KHÔNG BỊ CHẶN =======
        WebSettings webSettings = commonWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        commonWeb.loadUrl("https://www.google.com"); // Tạm thay vì YouTube

        // ======= NÚT GỬI ĐÁNH GIÁ =======
        commonBtn.setOnClickListener(v -> {
            String comment = commonEdit.getText().toString();
            boolean isFavorite = commonCheck.isChecked();

            int selectedId = commonRadioGroup.getCheckedRadioButtonId();
            String rating = "Chưa chọn";

            if (selectedId != -1) {
                RadioButton selectedRadio = findViewById(selectedId);
                rating = selectedRadio.getText().toString();
            }

            String message = "Đánh giá: " + rating +
                    "\nYêu thích: " + (isFavorite ? "Có" : "Không") +
                    "\nNhận xét: " + comment;

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        });

        // ======= NÚT MỞ VIDEO TRỰC TIẾP TRÊN YOUTUBE =======
        openYoutubeBtn = new Button(this);
        openYoutubeBtn.setText("Mở trailer trên YouTube");
        ((LinearLayout) findViewById(R.id.categoryList)).addView(openYoutubeBtn); // Thêm vào màn hình

        openYoutubeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Xu_OuA3b7MI"));
            startActivity(intent);
        });

        NestedScrollView scrollView = findViewById(R.id.rootScrollView);

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // Lăn xuống
                    Toast.makeText(MainActivity.this, "Đang lăn xuống", Toast.LENGTH_SHORT).show();
                } else if (scrollY < oldScrollY) {
                    // Lăn lên
                    Toast.makeText(MainActivity.this, "Đang lăn lên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
