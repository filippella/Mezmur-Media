package org.dalol.mezmurmedia.modules.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.business.base.BaseFragment;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/8/2016
 */
public class MezmurDetailFragment extends BaseFragment {

    @BindView(R.id.text_view_mezmur_detail) protected TextView mMezmurDetail;
    private String mMezmur;

    public static MezmurDetailFragment newInstance(String mezmur) {
        
        Bundle args = new Bundle();
        args.putString("mezmur", mezmur);
        
        MezmurDetailFragment fragment = new MezmurDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getContentView() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMezmur = getArguments().getString("mezmur");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_activity_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
        }
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        mMezmurDetail.setText(mMezmur);
    }
}
