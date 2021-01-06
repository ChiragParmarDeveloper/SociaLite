package com.ap.SociaLite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.CameraActivity;
import com.ap.SociaLite.Adapter.InterestPostAdapter;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.InterestFragmentPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterestFragment extends Fragment {

    @BindView(R.id.rv_interestlist)
    public RecyclerView rv_interestlist;

    @BindView(R.id.camera)
    ImageView camera;

    @BindView(R.id.intrest_post_recycler)
    RecyclerView intrest_post_recycler;

    @BindView(R.id.camera_constrain)
    ConstraintLayout camera_constrain;

    private RecyclerView.LayoutManager layoutManager;

    private InterestPostAdapter interestPostAdapter;

    ArrayList Name = new ArrayList<>(Arrays.asList("Name", "Name", "Name", "Name", "Name"));
public String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interest, container, false);
        ButterKnife.bind(this, view);
        Session session = new Session(getActivity());
        user_id=session.getUser_id();
        //-----------------------------for post----------------------------------------
        intrest_post_recycler = view.findViewById(R.id.intrest_post_recycler);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        //recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        intrest_post_recycler.setLayoutManager(layoutManager);
        interestPostAdapter = new InterestPostAdapter(Name, getActivity());
        intrest_post_recycler.setAdapter(interestPostAdapter);

//        LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerview_categorylist);

        //This is used to center first and last item on screen
//        recyclerview_categorylist.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                int position = parent.getChildViewHolder(view).getAdapterPosition();
//
//                if (position == 0 || position == state.getItemCount() - 1) {
//
//                    int elementWidth = (int)getResources().getDimension(R.dimen.pv_pin_view_item_size);
//                    int elementMargin = (int)getResources().getDimension(R.dimen.pv_pin_view_item_size);
//
//                    int padding = Resources.getSystem().getDisplayMetrics().widthPixels / 2 - elementWidth - elementMargin/2;
//
//                    if (position == 0) {
//                        outRect.left = padding;
//
//                    } else {
//                        outRect.right = padding;
//                    }
//                }
//            }
//        });


        //----------------------------------------------for category------------------------------------------------
//        Objects.requireNonNull(recyclerview_categorylist.getLayoutManager()).scrollToPosition(Integer.MAX_VALUE / 2);
//        recyclerview_categorylist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
//        // recycler_brand_icon.addItemDecoration(new CenterZoomLayoutManager());
//        categoryListAdapter = new CategoryListAdapter(getActivity(), CategoryNames, CategoryImages);
//        recyclerview_categorylist.setAdapter(categoryListAdapter); // set the Adapter to RecyclerVie
//


        //indicator.addOnAttachStateChangeListener(recyclerview_categorylist);
        // ScrollingPagerIndicator recyclerIndicator = findViewById(R.id.indicator);
        // indicator.addOnAttachStateChangeListener((View.OnAttachStateChangeListener) recyclerview_categorylist);

     //   new InterestFragmentPresenter(getActivity(), this).fetch_all_intrest(user_id);

        new InterestFragmentPresenter(getActivity(), this).fetch_my_intrest(user_id);

        return view;
    }

    @OnClick({R.id.camera_constrain})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.camera_constrain:
                startActivity(new Intent(getActivity(), CameraActivity.class));
                break;
        }
    }


//    public class CenterZoomLayoutManager extends LinearLayoutManager
//    {
//        private final float mShrinkAmount = 0.15f;
//        private final float mShrinkDistance = 0.9f;
//
//        public CenterZoomLayoutManager(Context context) {
//            super(context);
//        }
//
//        public CenterZoomLayoutManager(Context context, int orientation, boolean reverseLayout) {
//            super(context, orientation, reverseLayout);
//        }
//
//        @Override
//        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//            int orientation = getOrientation();
//            if (orientation == VERTICAL) {
//                int scrolled = super.scrollVerticallyBy(dy, recycler, state);
//                float midpoint = getHeight() / 2.f;
//                float d0 = 0.f;
//                float d1 = mShrinkDistance * midpoint;
//                float s0 = 1.f;
//                float s1 = 1.f - mShrinkAmount;
//                for (int i = 0; i < getChildCount(); i++) {
//                    View child = getChildAt(i);
//                    float childMidpoint =
//                            (getDecoratedBottom(child) + getDecoratedTop(child)) / 2.f;
//                    float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
//                    float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
//                    child.setScaleX(scale);
//                    child.setScaleY(scale);
//                }
//                return scrolled;
//            } else {
//                return 0;
//            }
//        }
//
//        @Override
//        public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
//            int orientation = getOrientation();
//            if (orientation == HORIZONTAL) {
//                int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
//
//                float midpoint = getWidth() / 2.f;
//                float d0 = 0.f;
//                float d1 = mShrinkDistance * midpoint;
//                float s0 = 1.f;
//                float s1 = 1.f - mShrinkAmount;
//                for (int i = 0; i < getChildCount(); i++) {
//                    View child = getChildAt(i);
//                    float childMidpoint =
//                            (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.f;
//                    float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
//                    float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
//                    child.setScaleX(scale);
//                    child.setScaleY(scale);
//                }
//                return scrolled;
//            } else {
//                return 0;
//            }
//
//        }
//
//
//    }
}