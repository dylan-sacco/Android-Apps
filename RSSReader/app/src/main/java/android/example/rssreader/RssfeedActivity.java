package android.example.rssreader;
import android.app.Activity;
import android.os.Bundle;
public class RssfeedActivity extends Activity implements MyListFragment.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_rssfeed);
    }
    @Override
    public void onRssItemSelected(String text) {
        DetailFragment fragment = (DetailFragment) getFragmentManager() .findFragmentById(R.id.detailFragment);
        fragment.setText(text);
    }
}