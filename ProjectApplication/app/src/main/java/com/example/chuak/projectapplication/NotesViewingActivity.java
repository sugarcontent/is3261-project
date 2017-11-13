package com.example.chuak.projectapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class NotesViewingActivity extends Activity {
    WebView webView;
    NotesDatabaseHelper notesDatabaseHelper;
    ArrayList <String> notesList = new ArrayList<String>();
    String filename = "notes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_viewing);
        String tutId = getIntent().getStringExtra("tutId");
        System.out.println("tut id is" + tutId);
        getAllNotes(this, tutId);
        System.out.println(notesList);




        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        // beware of XSS vulnerabilities

        webView.addJavascriptInterface(this, "webConnector");
        webView.setWebChromeClient(new WebChromeClient());


        webView.loadUrl("file:///android_asset/www/note.html");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Gson gson = new Gson();
        final String json = gson.toJson(notesList);
        System.out.println("3030");
        System.out.println(json);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:test("+json+")");
            }
        });

    }

    @JavascriptInterface
    public void exitActivity() {
        finish();
    }

    public void getAllNotes(Context context, String tutId) {

        notesDatabaseHelper = new NotesDatabaseHelper(context);
        notesList = notesDatabaseHelper.getAllTutNotes(tutId);

    }

    @JavascriptInterface
    public void saveNoteToFile() {
        ArrayList<String> notes = this.notesList;
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i=0; i < notes.size(); i++) {
                String line = notes.get(i) + "\n\n";
                fos.write(line.getBytes());
            }
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, this.getFilesDir().getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
