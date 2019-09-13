package com.muflihun.moviecatalogue3.callbacks;

import android.view.View;

import com.muflihun.moviecatalogue3.models.Item;

public interface OnItemClickCallback {
    void onClicked(View v, Item item, int position);
}
