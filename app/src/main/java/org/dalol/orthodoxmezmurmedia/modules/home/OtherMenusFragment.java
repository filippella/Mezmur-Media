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

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.OtherMenusAdapter;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.utilities.custom.RecyclerListItemMarginDecorator;
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
    protected void resolveDependencies() {
//        DaggerRecentMezmurComponent.builder()
//                .build()
//                .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecentList.setHasFixedSize(true);
        mRecentList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //mRecentList.addItemDecoration(new RecyclerListItemMarginDecorator(getActivity().getResources().getDimensionPixelSize(R.dimen.recent_mezmur_item_margin_size)));

        OtherMenusAdapter adapter = new OtherMenusAdapter(getContext());

        List<ExpandableMenu> expandableMenuList = new ArrayList<>();

        ExpandableMenu expandableMenu = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        for(int i = 0; i < 2; i++) {
            ExpandableMenu<String> menuItem = new ExpandableMenu(ExpandableType.TYPE_CHILD);
            menuItem.addMenuInfo("Filippo " + i);
            expandableMenu.addSubMenu(menuItem);
        }
        expandableMenuList.add(expandableMenu);

        ExpandableMenu expandableMenu2 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        for(int i = 0; i < 7; i++) {
            ExpandableMenu<String> menuItem = new ExpandableMenu(ExpandableType.TYPE_CHILD);
            menuItem.addMenuInfo("Marta " + i);
            expandableMenu2.addSubMenu(menuItem);
        }
        expandableMenuList.add(expandableMenu2);

        ExpandableMenu expandableMenu3 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        for(int i = 0; i < 3; i++) {
            ExpandableMenu<String> menuItem = new ExpandableMenu(ExpandableType.TYPE_CHILD);
            menuItem.addMenuInfo("Piyanki " + i);
            expandableMenu3.addSubMenu(menuItem);
        }
        expandableMenuList.add(expandableMenu3);


        ExpandableMenu expandableMenu4 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        for(int i = 0; i < 10; i++) {
            ExpandableMenu<String> menuItem = new ExpandableMenu(ExpandableType.TYPE_CHILD);
            menuItem.addMenuInfo("Piyanki " + i);
            expandableMenu4.addSubMenu(menuItem);
        }
        expandableMenuList.add(expandableMenu4);

        ExpandableMenu expandableMenu5 = new ExpandableMenu(ExpandableType.TYPE_PARENT);
        for(int i = 0; i < 3; i++) {
            ExpandableMenu<String> menuItem = new ExpandableMenu(ExpandableType.TYPE_CHILD);
            menuItem.addMenuInfo("Piyanki " + i);
            expandableMenu5.addSubMenu(menuItem);
        }
        expandableMenuList.add(expandableMenu5);

        //adapter.addMenu(new ExpandableMenu(ExpandableType.TYPE_PARENT).addSubMenus(expandableMenu.getMenuItems()));
//        adapter.addMenu(new ExpandableMenu(ExpandableType.TYPE_MENU).addSubMenus(expandableMenu.getMenuItems()));
//        adapter.addMenu(new ExpandableMenu(ExpandableType.TYPE_MENU).addSubMenus(expandableMenu.getMenuItems()));
        //adapter.setAccordionMode();

        adapter.setExpandableMenuList(expandableMenuList);
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

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }
}