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

package org.dalol.orthodoxmezmurmedia.modules.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.OtherMenusAdapter;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.basic.binders.OtherMenusInfo;
import org.dalol.model.expandable.ExpandableMenu;
import org.dalol.model.expandable.ExpandableType;
import org.dalol.presenter.business.dashboard.RecentMezmurFragmentPresenter;
import org.dalol.presenter.presentation.dashboard.OtherMenusFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/1/2016
 */
public class OtherMenusFragment extends BaseFragment<RecentMezmurFragmentPresenter> implements OtherMenusFragmentView {

    @BindView(R.id.recycler_view_List) protected RecyclerView mRecentList;
    @BindView(R.id.checkboxModeChange) protected CheckBox mModeChange;
    private OtherMenusAdapter adapter;

    public static OtherMenusFragment newInstance() {
        OtherMenusFragment fragment = new OtherMenusFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getPresenter().onViewReady();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecentList.setHasFixedSize(true);
        mRecentList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new OtherMenusAdapter(getContext());

        List<ExpandableMenu> expandableMenuList = new ArrayList<>();

        ExpandableMenu<String> expandableMenu = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        expandableMenu.addMenuInfo("Church Information");

        ExpandableMenu<Integer> churchListMenu = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        churchListMenu.addMenuInfo(OtherMenusInfo.CHURCH_LIST.getId());
        expandableMenu.addSubMenu(churchListMenu);
        expandableMenuList.add(expandableMenu);


        ExpandableMenu expandableMenu2 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        expandableMenu2.addMenuInfo("Ye Tselot Metshaf");


        ExpandableMenu<Integer> subMenu2 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu2.addMenuInfo(OtherMenusInfo.ANQUETSE_BIRHAN.getId());
        ExpandableMenu<Integer> subMenu3 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu3.addMenuInfo(OtherMenusInfo.DIRSANE_MICHAEL.getId());
        ExpandableMenu<Integer> subMenu4 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu4.addMenuInfo(OtherMenusInfo.MELKEA_IYESUS.getId());
        ExpandableMenu<Integer> subMenu5 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu5.addMenuInfo(OtherMenusInfo.MELKEA_MARIAM.getId());
        ExpandableMenu<Integer> subMenu6 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu6.addMenuInfo(OtherMenusInfo.WUDASSIE_AMLAK.getId());
        ExpandableMenu<Integer> subMenu7 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu7.addMenuInfo(OtherMenusInfo.WUDASSIE_MARIAM.getId());


        expandableMenu2.addSubMenu(subMenu2);
        expandableMenu2.addSubMenu(subMenu3);
        expandableMenu2.addSubMenu(subMenu4);
        expandableMenu2.addSubMenu(subMenu5);
        expandableMenu2.addSubMenu(subMenu6);
        expandableMenu2.addSubMenu(subMenu7);
        expandableMenuList.add(expandableMenu2);

        ExpandableMenu expandableMenu3 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        expandableMenu3.addMenuInfo("Amde Haymanot");

        ExpandableMenu<Integer> subMenu1 = new ExpandableMenu(ExpandableType.TYPE_CHILD);
        subMenu1.addMenuInfo(OtherMenusInfo.AMADE_MISTIRAT.getId());
        expandableMenu3.addSubMenu(subMenu1);
        expandableMenuList.add(expandableMenu3);

        adapter.setExpandableMenuList(expandableMenuList);
        mModeChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAccordionStyle(isChecked);
            }
        });
        mRecentList.setAdapter(adapter);
    }

    @Override
    protected void bindView(View view) {
        //mRecentList.setRecycledViewPool(new RecyclerView.RecycledViewPool());

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_more_menus_layout;
    }
}