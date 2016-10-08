/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.mezmurmedia;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dalol.mezmurmedia.mvp.model.mezmur.Mezmur;
import org.dalol.mezmurmedia.utilities.helpers.RecyclerViewFastIndexer;

import java.util.Arrays;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/21/2016
 */
public class TestTab extends Fragment implements MezmurSubsriber {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;

    public TestTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        setUpView(view);

        //registerEditText(R.id.ttttt, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //((MezmurListsActivity) getActivity()).subscribe(this);
    }

    private void setUpView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getLayoutInflater(getArguments()), Arrays.asList(Cheeses.sCheeseStrings));
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);

                RecyclerAdapter adapter = (RecyclerAdapter) parent.getAdapter();

                if (adapter != null && adapter.getItemCount() > 1) {

                    View topView = parent.getChildAt(0);
                    View secondView = parent.getChildAt(1);

                    TextView firstRowIndex = (TextView) topView.findViewById(R.id.indicatorCharacter);
                    TextView secondRowIndex = (TextView) secondView.findViewById(R.id.indicatorCharacter);



                    View headerView = getHeaderOf(parent, topView);

                    if (headerView == null) {
                        return;
                    }

                    if (StickyHeaderUtils.isHeaderView(parent, adapter, secondView)) {
                        if (secondView.getTop() <= headerView.getHeight()) {
                            c.translate(0, secondView.getTop() - headerView.getHeight());
                        }
                    }
                    //if (headerView != null) {
                    headerView.findViewById(R.id.stickyContainer).draw(c);
                    //}
                    //headerView.draw(c);
                    c.save();
                    c.translate(0, 0);
                }
            }
        });

        final RecyclerViewFastIndexer fastScroller = (RecyclerViewFastIndexer) view.findViewById(R.id.fastscroller);
        fastScroller.initWithRecyclerView(mRecyclerView);
        fastScroller.setColorHandle();
    }

        private View getHeaderOf(RecyclerView parent, View view) {
            if (StickyHeaderUtils.isHeaderView(parent, adapter, view)) {
                return view;
            } else {
                int position = parent.getChildPosition(view);
                RecyclerAdapter adapter = (RecyclerAdapter) parent.getAdapter();
                for (int i = position; i >= 0; i--) {
                    if (adapter.shouldStartTranslate(i)) {
                        RecyclerAdapter.Holder viewHolder = adapter.onCreateViewHolder(parent, 0);
                        adapter.onBindViewHolder(viewHolder, i);

                        View header = viewHolder.itemView;

                        if (header.getLayoutParams() == null) {
                            header.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
                        }

                        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
                        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);

                        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
                        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);
                        header.measure(childWidth, childHeight);
                        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
                        return header;
                    }
                }
            }
            return null;
        }

    @Override
    public void notifyMezmur(Mezmur mezmur) {
        adapter.appen(mezmur);
    }
}
