/*
 * Copyright (c) 2016 Orthodox Mezmur Media
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.modules.player;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.dalol.model.callback.OnPlayerMenuClickListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;

import java.util.Collections;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/26/2016
 */
public class MezmurListPlayerFragment extends BaseFragment {

    @BindView(R.id.mezmur_player_lists) protected RecyclerView mMezmurPlayerList;
    private MezmurPlayerListAdapter adapter;

    public static MezmurListPlayerFragment newInstance() {

        Bundle args = new Bundle();

        MezmurListPlayerFragment fragment = new MezmurListPlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        setHasOptionsMenu(true);
        mMezmurPlayerList.setHasFixedSize(true);
        mMezmurPlayerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MezmurPlayerListAdapter();

        for(int i = 0; i < 50; i++) {
            adapter.addItem("Filippo"+i);
        }

        adapter.setDragStartListener(new MezmurPlayerListAdapter.OnHolderDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder holder) {
                mTouchHelper.startDrag(holder);
            }
        });

        mMezmurPlayerList.setAdapter(adapter);
        mTouchHelper.attachToRecyclerView(mMezmurPlayerList);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_mezmur_player_lists;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mezmur_player_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_swap_player_option:
                OnPlayerMenuClickListener listener = (OnPlayerMenuClickListener) getActivity();
                listener.onShowMezmurDetailFragment();
                Toast.makeText(getContext(), "Show Mezmur Lyrics!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_player_search_mezmur:
                Toast.makeText(getContext(), "Show Search Mezmur!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ItemTouchHelper mTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Collections.swap(adapter.getCollections(), viewHolder.getAdapterPosition(), target.getAdapterPosition());
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //No Implementation Required
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN | ItemTouchHelper.UP);
        }
    });
}
