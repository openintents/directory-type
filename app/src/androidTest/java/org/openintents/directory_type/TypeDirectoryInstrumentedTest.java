package org.openintents.directory_type;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;

import androidx.core.content.FileProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TypeDirectoryInstrumentedTest {

    @Test
    public void typeOfExternalFilesDir() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // create shared directory
        File sharedDirectory = new File(appContext.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "external_shared");
        if (!sharedDirectory.exists() && !sharedDirectory.mkdirs()) {
            throw new RuntimeException("failed to mkdirs");
        }

        // get properties of directory
        Uri uri = FileProvider.getUriForFile(appContext, "org.openintents.directorytype", sharedDirectory);
        String typeOfDirectory = appContext.getContentResolver().getType(uri);
        boolean isDirectory = sharedDirectory.isDirectory();

        assertTrue(isDirectory);
        assertEquals(DocumentsContract.Document.MIME_TYPE_DIR, typeOfDirectory);
    }
}
