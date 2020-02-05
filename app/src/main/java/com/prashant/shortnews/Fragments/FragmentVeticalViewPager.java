package com.prashant.shortnews.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prashant.shortnews.Activities.LaunchActivity;
import com.prashant.shortnews.Adapters.VerticlePagerAdapter;
import com.prashant.shortnews.NewsModel;
import com.prashant.shortnews.R;
import com.prashant.shortnews.Utils.VerticalViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVeticalViewPager extends Fragment {

    LaunchActivity mActivity;


    VerticalViewPager verticleViewPager;
    // ArrayList<AndroidVersion> latestnews = new ArrayList<>();

    private VerticlePagerAdapter adapter;


    ArrayList<NewsModel> newsList;






    public FragmentVeticalViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        mActivity = (LaunchActivity) getActivity();

        newsList=new ArrayList<>();

        verticleViewPager = rootView.findViewById(R.id.verticleViewPager);


        //  loadJSON();
        fetchBloodGroup();


        return rootView;
    }


    private void fetchBloodGroup() {

        if (mActivity.customMethods.isNetworkAvailable(mActivity)) {
            //   mActivity.showHideProgressDialog(R.string.show_progress_dialog);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://newsapi.org/v2/top-headlines?country=in&apiKey=bed771fc90f246068376336378fa48cf", new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("lkghldg", response);


                    try {
                        JSONObject mainJsonObject = new JSONObject(response);

                        JSONArray jsonArrayArticles = mainJsonObject.getJSONArray("articles");


                        for (int i = 0; i < jsonArrayArticles.length(); i++) {

                            NewsModel newsModel = new NewsModel();
                            JSONObject jsonObject = jsonArrayArticles.getJSONObject(i);

                            JSONObject jsonSource = jsonObject.getJSONObject("source");


                            newsModel.setSource(jsonSource.getString("name"));


                            newsModel.setAuthor(jsonObject.getString("author"));
                            newsModel.setTime(jsonObject.getString("publishedAt"));


                            newsModel.setTitle(jsonObject.getString("title"));
                            newsModel.setDescription(jsonObject.getString("description"));
                            newsModel.setImage(jsonObject.getString("urlToImage"));
                            newsModel.setUrl(jsonObject.getString("url"));


                            newsList.add(newsModel);


                        }


                        adapter = new VerticlePagerAdapter(mActivity, newsList);
                        verticleViewPager.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

            };

            stringRequest.setShouldCache(false);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2f));
            RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
            requestQueue.add(stringRequest);


        } else {
            //   Snackbar.make(mActivity.coordinatorLayout, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();

            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG);

        }


    }

}
