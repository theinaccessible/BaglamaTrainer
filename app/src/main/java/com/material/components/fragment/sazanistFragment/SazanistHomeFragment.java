package com.material.components.fragment.sazanistFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.material.components.R;
import com.material.components.adapter.AdapterGridSazCategory;
import com.material.components.data.DataGenerator;
import com.material.components.model.SazCategory;
import com.material.components.utils.Tools;
import com.material.components.widget.SpacingItemDecoration;

import java.util.List;

public class SazanistHomeFragment extends Fragment{

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridSazCategory mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sazanist_home, container, false);
        parent_view = view.findViewById(R.id.parent_view);

        recyclerView = view.findViewById(R.id.recyclerView_sazanist_home_fragment);
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        //List<SazCategory> items = DataGenerator.getSazCategory(this);

        //set data and list adapter
        // mAdapter = new AdapterGridSazCategory(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
//        mAdapter.setOnItemClickListener(new AdapterGridSazCategory.OnItemClickListener() {
  //          @Override
    //        public void onItemClick(View view, SazCategory obj, int position) {
      //          //Todo: From here make the navigation of educational pages
        //        Snackbar.make(parent_view, "Item " + obj.title + " clicked", Snackbar.LENGTH_SHORT).show();
          //  }
        //      });

        return view;
    }

}
