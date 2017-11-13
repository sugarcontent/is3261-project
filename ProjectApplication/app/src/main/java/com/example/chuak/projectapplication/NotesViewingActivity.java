package com.example.chuak.projectapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;

import java.util.ArrayList;


public class NotesViewingActivity extends Activity {
    WebView webView;
    NotesDatabaseHelper notesDatabaseHelper;
    ArrayList <String> notesList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_viewing);
        String tutId = getIntent().getStringExtra("tutId");
        getAllNotes(this, tutId);

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

}
