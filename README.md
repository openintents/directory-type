# directory-type
Android experiment to show the mime-type of file directories

The app creates a directory in the `getExternalFilesDir()` and a corresponding uri for that directory. 
Then the MIME type of the uri is checked, querying the underlying `FileProvider`.

## Usage
Launch the app and see the result on the screen ... 

... or run
```
./gradlew cAT
```

## Background
Some Apps would like to direct a user to a specific file directory. These apps should launch a `ACTION_VIEW` intent with 
a data of a certain mime-type. However, there is no mime-type defined for file directories, see for example discussion at 
[https://stackoverflow.com/questions/18869772/mime-type-for-a-directory](https://stackoverflow.com/questions/18869772/mime-type-for-a-directory) or
[https://github.com/openintents/filemanager/issues/87](https://github.com/openintents/filemanager/issues/87).

The suggested mime-type here is the one used by `DocumentProviders`. 
It is defined at [`DocumentContracts.Document.MIME_TYPE_DIR`](https://developer.android.com/reference/android/provider/DocumentsContract.Document.html#MIME_TYPE_DIR) 
as 
```
vnd.android.document/directory
```
