package io.vov.vitamio.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import io.vov.vitamio.LibsChecker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import media.explore.activities.FileExplorerActivity;

/**
 * List
 */
public class VitamioMainActivity extends ListActivity {

  public static final String LOCAL_VIDEO = "LOCAL_VIDEO";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!LibsChecker.checkVitamioLibs(this)) return;
    setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[] { "title" },
        new int[] { android.R.id.text1 }));
  }

  protected List<Map<String, Object>> getData() {
    List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
    addItem(myData, "ChooseLocalVideo", new Intent(this, FileExplorerActivity.class));
    addItem(myData, "MediaPlayer", new Intent(this, MediaPlayerDemo.class));
    addItem(myData, "VideoView", new Intent(this, VideoViewDemo.class));
    addItem(myData, "MediaMetadataRetriever", new Intent(this, MediaMetadataRetrieverDemo.class));
    addItem(myData, "VideoSubtitle", new Intent(this, VideoSubtitleList.class));
    addItem(myData, "VideoViewBuffer", new Intent(this, VideoViewBuffer.class));
    return myData;
  }

  protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
    Map<String, Object> temp = new HashMap<String, Object>();
    temp.put("title", name);
    temp.put("intent", intent);
    data.add(temp);
  }

  @SuppressWarnings("unchecked") @Override protected void onListItemClick(ListView l, View v, int position, long id) {
    Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
    Intent intent = (Intent) map.get("intent");
    startActivity(intent);
  }
}
