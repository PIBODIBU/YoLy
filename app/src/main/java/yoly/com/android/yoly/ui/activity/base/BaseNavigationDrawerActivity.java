package yoly.com.android.yoly.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ContainerDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.AccountActivity;
import yoly.com.android.yoly.ui.activity.LikedActivity;
import yoly.com.android.yoly.ui.activity.MyLookPhotoUploadActivity;
import yoly.com.android.yoly.ui.activity.NewsListActivity;
import yoly.com.android.yoly.ui.activity.ProLooksActivity;

public abstract class BaseNavigationDrawerActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    protected DrawerBuilder drawerBuilder = null;
    protected Drawer drawer = null;
    private InputMethodManager inputMethodManager = null;
    private boolean wasInputActive = false;

    public BaseNavigationDrawerActivity() {
    }

    public void getCurrentSelection() {
        String basename = BaseNavigationDrawerActivity.this.getClass().getSimpleName();

        if (drawer == null) {
            return;
        }
    }

    protected void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void removeToolBarTitle() {
        getSupportActionBar().setTitle("");
    }

    /**
     * Init base NavigationDrawer
     */
    public void getDrawer() {
        // Creating DrawerBuilder
        setUpDrawerBuilder();

        // Creating Drawer from DrawerBuilder
        setUpDrawer();

        // Getting current Drawer selection
        getCurrentSelection();
    }

    /**
     * Check if user is logged in. If true -> add new DrawerItem to NavigationDrawer (My page)
     * Building NavigationDrawer from Drawer.Builder
     */
    private void setUpDrawer() {
        if (drawerBuilder != null) {
            Log.d(TAG, "setUpDrawer() -> Building Drawer from Drawer.Builder");

            drawer = drawerBuilder.build(); // Building Drawer
            //drawer.getRecyclerView().setVerticalScrollBarEnabled(false); // remove ScrollBar from RecyclerView
        } else {
            Log.e(TAG, "setUpDrawer() -> DrawerBuilder is null");
        }
    }

    public void setUpDrawerBuilder() {
        // Инициализируем Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final View tryLookView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) tryLookView).setText(getResources().getString(R.string.drawer_item_try_look));
        tryLookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseNavigationDrawerActivity.this, MyLookPhotoUploadActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        final ContainerDrawerItem tryLook = new ContainerDrawerItem()
                .withView(tryLookView)
                .withDivider(false)
                .withIdentifier(DrawerItems.TryLookActivity.ordinal());

        final View popularLooksView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) popularLooksView).setText(getResources().getString(R.string.drawer_item_popular_looks));
        popularLooksView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseNavigationDrawerActivity.this, ProLooksActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
        final ContainerDrawerItem popularLooks = new ContainerDrawerItem()
                .withView(popularLooksView)
                .withDivider(false)
                .withIdentifier(DrawerItems.PopularLooksActivity.ordinal());

        final View deleteLookView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) deleteLookView).setText(getResources().getString(R.string.drawer_item_delete_look));
        final ContainerDrawerItem deleteLook = new ContainerDrawerItem()
                .withView(deleteLookView)
                .withDivider(false)
                .withIdentifier(DrawerItems.DeleteLookActivity.ordinal());

        final View toLikedView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) toLikedView).setText(getResources().getString(R.string.drawer_item_to_liked));
        toLikedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseNavigationDrawerActivity.this, LikedActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
        final ContainerDrawerItem toLiked = new ContainerDrawerItem()
                .withView(toLikedView)
                .withDivider(false)
                .withIdentifier(DrawerItems.LikedActivity.ordinal());

        final View newsView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) newsView).setText(getResources().getString(R.string.drawer_item_news));
        newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseNavigationDrawerActivity.this, NewsListActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
        final ContainerDrawerItem news = new ContainerDrawerItem()
                .withView(newsView)
                .withDivider(false)
                .withIdentifier(DrawerItems.NewsActivity.ordinal());

        final View chatView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) chatView).setText(getResources().getString(R.string.drawer_item_chat));
        final ContainerDrawerItem chat = new ContainerDrawerItem()
                .withView(chatView)
                .withDivider(false)
                .withIdentifier(DrawerItems.ChatActivity.ordinal());

        final View accountView = getLayoutInflater().inflate(R.layout.drawer_item, null);
        ((TextView) accountView).setText(getResources().getString(R.string.drawer_item_account));
        accountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseNavigationDrawerActivity.this, AccountActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
        final ContainerDrawerItem account = new ContainerDrawerItem()
                .withView(accountView)
                .withDivider(false)
                .withIdentifier(DrawerItems.AccountActivity.ordinal());

        final View headView = getLayoutInflater().inflate(R.layout.drawer_item_back_arrow, null);
        headView.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer();
            }
        });
        final ContainerDrawerItem head = new ContainerDrawerItem()
                .withView(headView)
                .withDivider(false);

        final View bottomView = getLayoutInflater().inflate(R.layout.drawer_item_share, null);
        final ContainerDrawerItem bottom = new ContainerDrawerItem()
                .withView(bottomView)
                .withDivider(false);

        /**
         * Implementing DrawerBuilder
         */
        // Get device's screen width
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        drawerBuilder = new DrawerBuilder()
                .withActivity(this)
//                .withToolbar(toolbar)
                .withActionBarDrawerToggle(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerWidthPx(metrics.widthPixels)
                .withHeaderDivider(false)
                .addDrawerItems(
                        head,
                        tryLook,
                        popularLooks,
                        deleteLook,
                        toLiked,
                        news,
                        chat,
                        account,
                        bottom
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        if (inputMethodManager.isAcceptingText()) {
                            if (getCurrentFocus() != null) {
                                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                            }
                            wasInputActive = true;
                        } else {
                            wasInputActive = false;
                        }
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Показать клавиатуру при закрытии Navigation Drawer, если она была открыта
                        if (wasInputActive)
                            inputMethodManager.showSoftInput(getCurrentFocus(), 0);
                    }

                    @Override
                    public void onDrawerSlide(View view, float v) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        try {
                            String currentClass = BaseNavigationDrawerActivity.this.getClass().getSimpleName();
                            DrawerItems drawerItems = DrawerItems.values()[(int) drawerItem.getIdentifier()];

                            Log.d(TAG, "onItemClick()-> currentClass: " + currentClass);

                            switch (drawerItems) {
                                case NewsActivity: {
                                    if (currentClass.equals(NewsListActivity.class.getSimpleName())) {
                                        break;
                                    } else {
                                        startActivity(new Intent(BaseNavigationDrawerActivity.this, NewsListActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        finish();
                                        break;
                                    }
                                }
                                default: {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown():\nkeyCode: " + Integer.toString(keyCode) + "\nkeyEvent: " + event);
        try {
            switch (keyCode) {
                case KeyEvent.KEYCODE_MENU: {
                    if (drawer.isDrawerOpen())
                        drawer.closeDrawer();
                    else
                        drawer.openDrawer();
                    break;
                }
                case KeyEvent.KEYCODE_BACK: {
                    if (drawer.isDrawerOpen()) { // Check if Drawer is opened
                        drawer.closeDrawer();
                    } else {
                        super.onBackPressed();
                    }

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onPostResume() {
        getCurrentSelection();
        super.onPostResume();
    }

    public enum DrawerItems {
        NewsActivity,
        TryLookActivity,
        PopularLooksActivity,
        DeleteLookActivity,
        LikedActivity,
        ChatActivity,
        AccountActivity,
    }
}