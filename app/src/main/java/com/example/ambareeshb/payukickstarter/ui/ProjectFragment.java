package com.example.ambareeshb.payukickstarter.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.databinding.FragmentProjectBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment {
    private static String WEB_VIEW_PATH ;
    private FragmentProjectBinding fragmentProjectBinding;

    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of


     * @return A new instance of fragment ProjectFragment.
     */

    public static ProjectFragment newInstance(String path) {
        Bundle bundle = new Bundle();
        bundle.putString(WEB_VIEW_PATH,path);
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProjectBinding =
                DataBindingUtil.inflate(inflater,R.layout.fragment_project,container,false);
        return fragmentProjectBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentProjectBinding.webView.loadUrl(getArguments().getString(WEB_VIEW_PATH));
    }
}
