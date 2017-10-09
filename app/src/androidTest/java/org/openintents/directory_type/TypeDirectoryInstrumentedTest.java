package org.openintents.directory_type;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.FileProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TypeDirectoryInstrumentedTest {
    @Test
    public void typeOfExternalFilesDir() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // create shared directory
        File sharedDirectory = new File(appContext.getExternalFilesDir(null), "external_shared");
        if (!sharedDirectory.exists() && !sharedDirectory.mkdirs()) {
            throw new RuntimeException("failed to mkdirs");
        }

        // get properties of directory
        Uri uri = FileProvider.getUriForFile(appContext, "org.openintents.directorytype", sharedDirectory);
        String typeOfDirectory = appContext.getContentResolver().getType(uri);
        boolean isDirectory = sharedDirectory.isDirectory();

        assertEquals(true, isDirectory);
        assertEquals(DocumentsContract.Document.MIME_TYPE_DIR, typeOfDirectory);
    }
}
