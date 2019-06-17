package com.pallaw.tmassignment.UI.Gallary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.pallaw.tmassignment.Data.DataManager;
import com.pallaw.tmassignment.Data.Models.CompatibilityQuestionsBean;
import com.pallaw.tmassignment.Data.Models.TMResponse;
import com.pallaw.tmassignment.R;
import com.pallaw.tmassignment.Util.CarouselLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment implements GalleryContract.View {

    private GalleryPresenter presenter;
    private RecyclerView recyclerView;
    List<CompatibilityQuestionsBean> compatibilityQuestions = new ArrayList<>();
    private GalleryRecyclerViewAdapter galleryRecyclerViewAdapter;


    public GalleryFragment() {
    }

    @SuppressWarnings("unused")
    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        DataManager dataManager = new DataManager(getActivity());
        presenter = new GalleryPresenter(GalleryFragment.this, dataManager);

        //initialize recyclerview with empty list
        initList();
        
        //Download data from server
        presenter.onDownloadData();
        
        return view;
    }

    private void initList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(getActivity(), compatibilityQuestions, DisplayMode.LIST);
        recyclerView.setAdapter(galleryRecyclerViewAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_list:
                changeLayout(DisplayMode.LIST);
                break;
            case R.id.action_grid:
                changeLayout(DisplayMode.GRID);
                break;
            case R.id.action_carousel:
                changeLayout(DisplayMode.CAROUSEL);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeLayout(DisplayMode displayMode) {
        switch (displayMode) {
            case LIST:
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case GRID:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
            case CAROUSEL:
                recyclerView.setLayoutManager(new CarouselLayoutManager(getContext(), CarouselLayoutManager.HORIZONTAL, false));
                break;
        }
        recyclerView.setAdapter(galleryRecyclerViewAdapter);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showData(TMResponse tmResponse) {
        compatibilityQuestions.clear();
        compatibilityQuestions.addAll(tmResponse.getCompatibilityQuestions());
        galleryRecyclerViewAdapter.notifyDataSetChanged();

    }

}
