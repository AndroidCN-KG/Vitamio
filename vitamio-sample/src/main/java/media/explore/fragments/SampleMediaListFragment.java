package media.explore.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import io.vov.vitamio.demo.MediaPlayerDemo_Video;
import io.vov.vitamio.demo.R;

public class SampleMediaListFragment extends Fragment {
  private ListView mFileListView;
  private SampleMediaAdapter mAdapter;

  public static SampleMediaListFragment newInstance() {
    SampleMediaListFragment f = new SampleMediaListFragment();
    return f;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_file_list, container, false);
    mFileListView = (ListView) viewGroup.findViewById(R.id.file_list_view);
    return viewGroup;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    final Activity activity = getActivity();

    mAdapter = new SampleMediaAdapter(activity);
    mFileListView.setAdapter(mAdapter);
    mFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
        SampleMediaItem item = mAdapter.getItem(position);
        String name = item.mName;
        String url = item.mUrl;
        MediaPlayerDemo_Video.intentTo(activity, url, name, MediaPlayerDemo_Video.STREAM_VIDEO);
      }
    });
    mAdapter.addItem("http://video19.ifeng.com/video06/2012/04/11/629da9ec-60d4-4814-a940-997e6487804a.mp4", "陈思成");
    mAdapter.addItem("rtmp://live.hkstv.hk.lxdns.com/live/hks", "RTMP香港电视台");
  }

  final class SampleMediaItem {
    String mUrl;
    String mName;

    public SampleMediaItem(String url, String name) {
      mUrl = url;
      mName = name;
    }
  }

  final class SampleMediaAdapter extends ArrayAdapter<SampleMediaItem> {
    public SampleMediaAdapter(Context context) {
      super(context, android.R.layout.simple_list_item_2);
    }

    public void addItem(String url, String name) {
      add(new SampleMediaItem(url, name));
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;
      if (view == null) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
      }

      ViewHolder viewHolder = (ViewHolder) view.getTag();
      if (viewHolder == null) {
        viewHolder = new ViewHolder();
        viewHolder.mNameTextView = (TextView) view.findViewById(android.R.id.text1);
        viewHolder.mUrlTextView = (TextView) view.findViewById(android.R.id.text2);
      }

      SampleMediaItem item = getItem(position);
      viewHolder.mNameTextView.setText(item.mName);
      viewHolder.mUrlTextView.setText(item.mUrl);

      return view;
    }

    final class ViewHolder {
      public TextView mNameTextView;
      public TextView mUrlTextView;
    }
  }
}
