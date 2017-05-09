package assolid.com.myrestclient;

import android.app.Activity;

import com.octo.android.robospice.SpiceManager;


public abstract class BaseActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(RestService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}