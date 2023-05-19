package com.example.androidtestapp;

import java.util.ArrayList;


public class RepoManager {
    private static RepoManager instance;
    private ArrayList<Repo> repoList;

    private RepoManager() {
        repoList = new ArrayList<>();
    }

    public static synchronized RepoManager getInstance() {
        if (instance == null) {
            instance = new RepoManager();
        }
        return instance;
    }

    public ArrayList<Repo> getRepoList() {
        return repoList;
    }

    public void addRepo(Repo repo) {
        repoList.add(repo);
    }
}

