package com.prashant.shortnews.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prashant.shortnews.Activities.LaunchActivity;
import com.prashant.shortnews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLaunch extends Fragment {

    public LaunchActivity mActivity;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public FragmentLaunch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_launch, container, false);

        mActivity = (LaunchActivity) getActivity();


        startOperation();

        return rootView;
    }


    private class AsyncClass extends AsyncTask<String, Void, String> {
        @Nullable
        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();


            FragmentVeticalViewPager fragmentVeticalViewPager = new FragmentVeticalViewPager();
            fragmentTransaction.replace(R.id.activity_main_frame_layout, fragmentVeticalViewPager, "fragmentVeticalViewPager");
            fragmentTransaction.addToBackStack(fragmentVeticalViewPager.getClass().toString());

            fragmentTransaction.commit();

        }

        @Override
        protected void onPreExecute() {/* Do Nothing */}

        @Override
        protected void onProgressUpdate(Void... values) {/* Do Nothing */}
    }

    private void startOperation() {
        new AsyncClass().execute();
    }

}
