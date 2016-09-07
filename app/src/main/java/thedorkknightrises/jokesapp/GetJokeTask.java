package thedorkknightrises.jokesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import thedorkknightrises.jokesapp.backend.myApi.MyApi;
import thedorkknightrises.lib.jokehandler.DisplayActivity;

public class GetJokeTask extends AsyncTask<Object, Void, String> {
    private static MyApi api;
    private Context context;
    private ProgressDialog progressDialog;
    @Override
    protected String doInBackground(Object... params) {
        context = (Context) params[0];
        if (params[1] != null) progressDialog = (ProgressDialog) params[1];
        if (api == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokes-app-142320.appspot.com/_ah/api/");

            api = builder.build();
        }
        try {
            String joke = api.sayHi().execute().getData();
            Log.d("Endpoint", "Returned joke: " + joke);
            return joke;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null && !s.equals("")) {
            Intent i = new Intent(context, DisplayActivity.class);
            i.putExtra("text", s);
            context.startActivity(i);
        } else Toast.makeText(context, "Nope, got nothing", Toast.LENGTH_SHORT).show();
        if (progressDialog != null) progressDialog.dismiss();
        super.onPostExecute(s);
    }
}