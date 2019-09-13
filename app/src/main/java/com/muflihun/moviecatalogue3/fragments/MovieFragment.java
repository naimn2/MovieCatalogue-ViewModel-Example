package com.muflihun.moviecatalogue3.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.muflihun.moviecatalogue3.activities.DetailMovie;
import com.muflihun.moviecatalogue3.adapters.ListItemAdapter;
import com.muflihun.moviecatalogue3.callbacks.OnItemClickCallback;
import com.muflihun.moviecatalogue3.R;
import com.muflihun.moviecatalogue3.models.Item;
import com.muflihun.moviecatalogue3.viewmodels.ItemViewModels;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements OnItemClickCallback {
    private RecyclerView rvMovie;
    private ProgressBar pbMovie;

    private ListItemAdapter adapter;
    private ItemViewModels movieViewModel;

    private Observer<ArrayList<Item>> movieObserver = new Observer<ArrayList<Item>>() {
        @Override
        public void onChanged(ArrayList<Item> items) {
            if (items != null){
                adapter.setData(items);
                showLoading(false);
            }
        }
    };

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rv_movie);
        pbMovie = view.findViewById(R.id.pb_movie);

        adapter = new ListItemAdapter(getContext());
        adapter.setOnClickCallback(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(ItemViewModels.class);
        movieViewModel.getListItem().observe(this, movieObserver);
        movieViewModel.setItem(ItemViewModels.ITEM_MOVIE);
        showLoading(true);
    }

    @Override
    public void onClicked(View v, Item item, int position) {
//        Toast.makeText(getContext(), "Item-"+position+" clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), DetailMovie.class);
        intent.putExtra(DetailMovie.EXTRA_ITEM, item);
        startActivity(intent);
    }

    private void showLoading(boolean state){
        if (state){
            pbMovie.setVisibility(View.VISIBLE);
        } else {
            pbMovie.setVisibility(View.GONE);
        }
    }
}
