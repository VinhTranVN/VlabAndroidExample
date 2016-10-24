package com.mvp.features.main;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mvp.features.contact.ContactFragment;
import com.mvp.features.contact.ContactFragmentTest;
import com.mvp.loaders.MainLoader;

import java.util.ArrayList;
import java.util.List;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;
    private LoaderManager mLoaderManager;
    private MainLoader mLoader;
    private static final int LOADER_ID = 1;

    public MainPresenter(MainContract.View mainView, LoaderManager loaderManager, MainLoader loader) {
        mMainView = checkNotNull(mainView, "RegisterView cannot be null!");
        mLoaderManager = loaderManager;
        mLoader = loader;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
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

    @Override
    public void loadData() {
        System.out.println(">>> MainPresenter -> loadData : ");
        mLoaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int id, Bundle args) {
                System.out.println(">>> MainPresenter -> onCreateLoader : ");
                mMainView.setLoadingIndicator(true);
                return mLoader;
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String data) {
                System.out.println(">>> MainPresenter -> onLoadFinished : ");
                mMainView.setLoadingIndicator(false);
                mMainView.showDataResponse(data);
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {
                System.out.println(">>> MainPresenter -> onLoaderReset : ");
            }
        });
    }
}
