package com.mvp.features.main;

import com.mvp.features.contact.ContactFragment;
import com.mvp.features.contact.ContactFragmentTest;

import java.util.ArrayList;
import java.util.List;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        mMainView = checkNotNull(mainView, "RegisterView cannot be null!");
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        // TODO
    }

    @Override
    public List<TabInfo> getTabs() {
        // TODO update tab list
        List<TabInfo> tabInfos = new ArrayList<>();
        tabInfos.add(new TabInfo("Tab 1", android.R.drawable.sym_contact_card, ContactFragment.class));
        tabInfos.add(new TabInfo("Tab 2", android.R.drawable.ic_delete, ContactFragmentTest.class));
        tabInfos.add(new TabInfo("Tab 3", android.R.drawable.ic_menu_recent_history, ContactFragment.class));
        return tabInfos;
    }
}
