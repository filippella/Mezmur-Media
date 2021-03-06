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

package org.dalol.orthodoxmezmurmedia.modules.churches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.dalol.model.churches.Church;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerChurchesComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ChurchesModule;
import org.dalol.orthodoxmezmurmedia.modules.churches.adapter.ChurchListAdapter;
import org.dalol.orthodoxmezmurmedia.utilities.custom.RecyclerListItemMarginDecorator;
import org.dalol.orthodoxmezmurmedia.utilities.widgets.AmharicKeyboardView;
import org.dalol.presenter.business.churches.ChurchesPresenter;
import org.dalol.presenter.presentation.churches.ChurchesView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/14/2016
 */
public class ChurchListActivity extends BaseActivity<ChurchesPresenter> implements ChurchesView {

    @BindView(R.id.recycler_view_church_location_list) protected RecyclerView mChurchLocationList;
    @BindView(R.id.editText_church_search_query) protected EditText mChurchSearchFileld;
    @BindView(R.id.church_change_keyboard_input) protected ImageView mChangeInput;

    private EditText searchEditText;
    private ChurchListAdapter mChurchesAdapter;
    private AmharicKeyboardView myKeyboardView;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        setTitle("Church Lists");
        showHome();
        mChurchLocationList.setHasFixedSize(true);
        mChurchLocationList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChurchesAdapter = new ChurchListAdapter(getLayoutInflater());
        mChurchLocationList.setAdapter(mChurchesAdapter);
        mChurchLocationList.addItemDecoration(new RecyclerListItemMarginDecorator(getResources().getDimensionPixelSize(R.dimen.church_list_item_margin_size)));
        getPresenter().onViewReady();
        mChurchSearchFileld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mChurchesAdapter.filter(s.toString());
            }
        });
        myKeyboardView = new AmharicKeyboardView(this);
        myKeyboardView.handleEditText(mChurchSearchFileld);
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        boolean keyBoardStatus = myKeyboardView.dispatchKeyEvent(event);
//        return keyBoardStatus ? keyBoardStatus : super.dispatchKeyEvent(event);
//    }

    @Override
    protected void resolveDependency() {
        super.resolveDependency();
        DaggerChurchesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .churchesModule(new ChurchesModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_nearest_church:
                myKeyboardView.clearControl();
                Toast.makeText(ChurchListActivity.this, "Nearest Location..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_church_change_keyboard:
                myKeyboardView.gainControl();
//                if(myKeyboardView.isShowing()) {
//                    myKeyboardView.hideCustomKeyboard();
//                } else {
//                    myKeyboardView.showCustomKeyboard();
//                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_church_list;
    }

    @OnClick(R.id.church_change_keyboard_input)
    void onChangeKeyboardClick() {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.mezmur_list_menu, menu);
//
//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//
//        searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//        searchEditText.setText("Filippo");
//        searchEditText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                EditText edittext = (EditText) v;
//                edittext.onTouchEvent(event);
//                hideKeboard(edittext);
//                return true;
//            }
//        });
//        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                EditText edittext = (EditText) view;
//                hideKeboard(edittext);
//            }
//        });
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
//
//        MenuItemCompat.setOnActionExpandListener(item,
//                new MenuItemCompat.OnActionExpandListener() {
//                    @Override
//                    public boolean onMenuItemActionCollapse(MenuItem item) {
//                        // Do something when collapsed
//                        //adapter.setFilter(mCountryModel);
//                        return true; // Return true to collapse action view
//                    }
//
//                    @Override
//                    public boolean onMenuItemActionExpand(MenuItem item) {
//                        // Do something when expanded
//                        return true; // Return true to expand action view
//                    }
//                });
//        return super.onCreateOptionsMenu(menu);
//    }


    private void hideKeboard(EditText v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.setCursorVisible(true);
        }
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public void onLoadChurches(Church[] churches) {
        mChurchesAdapter.addChurches(Arrays.asList(churches));
    }

    @Override
    public void onBackPressed() {
        if(myKeyboardView.isShowing()) {
            myKeyboardView.hideCustomKeyboard();
        } else {
            super.onBackPressed();
        }
    }
}
