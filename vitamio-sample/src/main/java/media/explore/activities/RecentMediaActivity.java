package media.explore.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import io.vov.vitamio.demo.R;
import media.explore.application.AppActivity;
import media.explore.fragments.RecentMediaListFragment;

public class RecentMediaActivity extends AppActivity {
  public static Intent newIntent(Context context) {
    Intent intent = new Intent(context, RecentMediaActivity.class);
    return intent;
  }

  public static void intentTo(Context context) {
    context.startActivity(newIntent(context));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Fragment newFragment = RecentMediaListFragment.newInstance();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    transaction.replace(R.id.body, newFragment);
    transaction.commit();
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    boolean show = super.onPrepareOptionsMenu(menu);
    if (!show) return show;

    MenuItem item = menu.findItem(R.id.action_recent);
    if (item != null) item.setVisible(false);

    return true;
  }
}
