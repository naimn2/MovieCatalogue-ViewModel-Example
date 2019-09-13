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

import com.muflihun.moviecatalogue3.R;
import com.muflihun.moviecatalogue3.activities.DetailMovie;
import com.muflihun.moviecatalogue3.adapters.ListItemAdapter;
import com.muflihun.moviecatalogue3.callbacks.OnItemClickCallback;
import com.muflihun.moviecatalogue3.models.Item;
import com.muflihun.moviecatalogue3.viewmodels.ItemViewModels;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment implements OnItemClickCallback {
    private RecyclerView rvTv;
    private ProgressBar pbTv;

    private ListItemAdapter adapter;
    private ItemViewModels tvViewModel;

    private Observer<ArrayList<Item>> tvObserver = new Observer<ArrayList<Item>>() {
        @Override
        public void onChanged(ArrayList<Item> items) {
            if (items != null){
                adapter.setData(items);
                showLoading(false);
            }
        }
    };

    public TVShowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTv = view.findViewById(R.id.rv_tv);
        pbTv = view.findViewById(R.id.pb_tv);

        adapter = new ListItemAdapter(getContext());
        adapter.setOnClickCallback(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTv.setLayoutManager(layoutManager);
        rvTv.setAdapter(adapter);

        tvViewModel = ViewModelProviders.of(this).get(ItemViewModels.class);
        tvViewModel.getListItem().observe(this, tvObserver);
        tvViewModel.setItem(ItemViewModels.ITEM_TVSHOW);
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
            pbTv.setVisibility(View.VISIBLE);
        } else {
            pbTv.setVisibility(View.GONE);
        }
    }
}
