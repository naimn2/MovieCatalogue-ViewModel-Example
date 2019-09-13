package com.muflihun.moviecatalogue3.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Parcelable {
    private int id;
    private String title, overview, release, language, poster, backdrop;
    private double vote, popularity;

    public static final String[] MOVIE_DATA_KEYS = new String[]{"id", "title", "poster_path", "overview", "vote_average",
            "popularity", "release_date", "original_language", "backdrop_path"};

    public static final String[] TVSHOW_DATA_KEYS = new String[]{"id", "name", "poster_path", "overview", "vote_average",
            "popularity", "first_air_date", "original_language", "backdrop_path"};

    public Item() {
    }

    public Item(JSONObject jObject, String[] keys) {
        try {
            id = jObject.getInt(keys[0]);
            title = jObject.getString(keys[1]);
            poster = jObject.getString(keys[2]);
            overview = jObject.getString(keys[3]);
            vote = jObject.getDouble(keys[4]);
            popularity = jObject.getDouble(keys[5]);
            release = jObject.getString(keys[6]);
            language = jObject.getString(keys[7]);
            backdrop = jObject.getString(keys[8]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Item(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster = in.readString();
        overview = in.readString();
        vote = in.readDouble();
        popularity = in.readDouble();
        release = in.readString();
        language = in.readString();
        backdrop = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(overview);
        parcel.writeDouble(vote);
        parcel.writeDouble(popularity);
        parcel.writeString(release);
        parcel.writeString(language);
        parcel.writeString(backdrop);
    }
}