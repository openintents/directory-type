package org.openintents.directory_type;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);

        // create shared directory
        File sharedDirectory = new File(getExternalFilesDir(null), "external_shared");
        if (!sharedDirectory.exists() && !sharedDirectory.mkdirs()) {
            tv.setText(R.string.mkdirs_failed);
            return;
        }

        // get properties of directory
        Uri uri = FileProvider.getUriForFile(this, "org.openintents.directorytype", sharedDirectory);
        String typeOfDirectory = getContentResolver().getType(uri);
        boolean isDirectory = sharedDirectory.isDirectory();

        // show properties
        tv.setText(Html.fromHtml(getString(R.string.type_of, uri.toString(), typeOfDirectory, isDirectory),
                Html.FROM_HTML_MODE_COMPACT));
    }
}
