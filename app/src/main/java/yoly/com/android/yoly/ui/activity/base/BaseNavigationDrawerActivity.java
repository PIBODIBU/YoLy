package yoly.com.android.yoly.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.AccountActivity;
import yoly.com.android.yoly.ui.activity.LikedActivity;
import yoly.com.android.yoly.ui.activity.NewsActivity;

public abstract class BaseNavigationDrawerActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    protected DrawerBuilder drawerBuilder = null;
    protected Drawer drawer = null;
    private InputMethodManager inputMethodManager = null;
    private boolean wasInputActive = false;
    private ImageButton hamburger;

    public BaseNavigationDrawerActivity() {
    }

    public void getCurrentSelection() {
        String basename = BaseNavigationDrawerActivity.this.getClass().getSimpleName();

        if (drawer == null) {
            return;
        }

        drawer.setSelection(-1);
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

    private PrimaryDrawerItem prepareDrawerItem(PrimaryDrawerItem drawerItem) {
        drawerItem
                .withIconColorRes(R.color.colorPrimary)
                .withSelectedIconColorRes(R.color.colorPrimary)
                .withSelectedColorRes(android.R.color.transparent)
                .withTextColorRes(R.color.colorTextPrimary)
                .withSelectedTextColorRes(R.color.colorTextPrimary);

        return drawerItem;
    }

    public void setUpDrawerBuilder() {
        // Items
        final View header = getLayoutInflater().inflate(R.layout.drawer_head, null);
        final View footer = getLayoutInflater().inflate(R.layout.drawer_footer, null);
        Glide
                .with(this)
                .load(R.drawable.cat)
                .into(((CircleImageView) ButterKnife.findById(header, R.id.iv_drawer_avatar)));

        final PrimaryDrawerItem tryLook = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_new_look)
                .withIdentifier(DrawerItems.TryLookActivity.ordinal())
                .withName("ПРИМЕРИТЬ LOOK");

        final PrimaryDrawerItem popularLooks = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_fashion)
                .withIdentifier(DrawerItems.PopularLooksActivity.ordinal())
                .withName("FASHION LOOKS");

        final PrimaryDrawerItem liked = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_liked)
                .withIdentifier(DrawerItems.LikedActivity.ordinal())
                .withName("ИЗБРАННОЕ");

        final PrimaryDrawerItem news = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_news)
                .withIdentifier(DrawerItems.NewsActivity.ordinal())
                .withName("НОВОСТИ");

        final PrimaryDrawerItem chat = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_chat)
                .withIdentifier(DrawerItems.ChatActivity.ordinal())
                .withName("ЧАТ");

        final PrimaryDrawerItem buy = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_buy)
                .withIdentifier(DrawerItems.BuyActivity.ordinal())
                .withName("КУПИТЬ");

        final PrimaryDrawerItem account = new PrimaryDrawerItem()
                .withIcon(R.drawable.drawer_item_exit)
                .withIdentifier(DrawerItems.Exit.ordinal())
                .withName("ВЫЙТИ");

        drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColorRes(R.color.colorBackground)
                .withActionBarDrawerToggle(false)
                .withActionBarDrawerToggleAnimated(true)
                .withHeaderDivider(false)
                .withHeader(header)
                .withFooterDivider(false)
                .withFooter(footer)
                .addDrawerItems(
                        prepareDrawerItem(tryLook),
                        prepareDrawerItem(popularLooks),
                        prepareDrawerItem(liked),
                        prepareDrawerItem(news),
                        prepareDrawerItem(chat),
                        prepareDrawerItem(buy),
                        prepareDrawerItem(account)
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
                            String currentClassName = BaseNavigationDrawerActivity.this.getClass().getSimpleName();
                            DrawerItems drawerItems = DrawerItems.values()[(int) drawerItem.getIdentifier()];
                            AppCompatActivity selectedActivity = null;

                            switch (drawerItems) {
                                case NewsActivity: {
                                    selectedActivity = new NewsActivity();
                                    break;
                                }

                                case TryLookActivity: {
                                    break;
                                }
                                case PopularLooksActivity: {
                                    break;
                                }
                                case LikedActivity: {
                                    selectedActivity = new LikedActivity();
                                    break;
                                }
                                case ChatActivity: {
                                    break;
                                }
                                case AccountActivity: {
                                    selectedActivity = new AccountActivity();
                                    break;
                                }
                                case BuyActivity: {
                                    break;
                                }
                                case Exit: {
                                    finish();
                                    return false;
                                }
                                default: {
                                    break;
                                }
                            }

                            if (selectedActivity != null &&
                                    !currentClassName.equals(selectedActivity.getClass().getSimpleName())) {
                                startActivity(new Intent(BaseNavigationDrawerActivity.this, selectedActivity.getClass())
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        getCurrentSelection();
        super.onPostResume();
    }

    public void setHamburger(ImageButton hamburger) {
        this.hamburger = hamburger;

        if (this.hamburger != null)
            this.hamburger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawer != null) {
                        drawer.openDrawer();
                    }
                }
            });
    }

    public enum DrawerItems {
        NewsActivity,
        TryLookActivity,
        PopularLooksActivity,
        LikedActivity,
        ChatActivity,
        AccountActivity,
        BuyActivity,
        Exit,
    }
}