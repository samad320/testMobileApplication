package com.example.androidtestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// MainActivity.java
public class MainActivity extends AppCompatActivity {

    private RepoManager repoManager;
    private ListView repoListView;
    private TextView emptyListLabel;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoManager = RepoManager.getInstance();
        repoListView = findViewById(R.id.repoListView);
        emptyListLabel = findViewById(R.id.emptyListLabel);
        addButton = findViewById(R.id.addButton);

        repoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repo selectedRepo = repoManager.getRepoList().get(position);
                openGithubUrl(selectedRepo.getUrl());
            }
        });

        repoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Repo selectedRepo = repoManager.getRepoList().get(position);
                shareRepo(selectedRepo);
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRepoActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ArrayList<Repo> repoList = repoManager.getRepoList();

        if (repoList.isEmpty()) {
            emptyListLabel.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
            repoListView.setVisibility(View.GONE);
        } else {
            emptyListLabel.setVisibility(View.GONE);
            addButton.setVisibility(View.GONE);
            repoListView.setVisibility(View.VISIBLE);

            ArrayAdapter<Repo> adapter = new ArrayAdapter<>(this, R.layout.repo_item, repoList);
            repoListView.setAdapter(adapter);
        }
    }

    private void openGithubUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void shareRepo(Repo repo) {
        String shareText = repo.getName() + "\n" + repo.getUrl();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(intent, "Share Repository"));
    }

    private void openAddRepoActivity() {
        Intent intent = new Intent(this, AddRepoActivity.class);
        startActivity(intent);
    }
}




