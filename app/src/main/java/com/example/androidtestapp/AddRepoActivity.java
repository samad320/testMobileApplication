package com.example.androidtestapp;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class AddRepoActivity extends AppCompatActivity {

    private EditText repoNameEditText;
    private EditText repoUrlEditText;
    private Button addRepoButton;
    private RepoManager repoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repo);

        repoManager = RepoManager.getInstance();

        repoNameEditText = findViewById(R.id.repoNameEditText);
        repoUrlEditText = findViewById(R.id.repoUrlEditText);
        addRepoButton = findViewById(R.id.addRepoButton);

        addRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = repoNameEditText.getText().toString().trim();
                String url = repoUrlEditText.getText().toString().trim();

                if (!name.isEmpty() && !url.isEmpty()) {
                    Repo newRepo = new Repo(name, "", url);
                    repoManager.addRepo(newRepo);
                    finish();
                }
            }
        });
    }
}

