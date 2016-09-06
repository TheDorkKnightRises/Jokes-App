package thedorkknightrises.jokesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import thedorkknightrises.jokesapp.backend.jokeApi.JokeApi;
import thedorkknightrises.lib.jokehandler.DisplayActivity;

/**
 * Created by Samriddha Basu on 9/4/2016.
 */
public class GetJokeTask extends AsyncTask<Object, Void, String> {
    private static JokeApi api;
    private Context context;
    private ProgressDialog progressDialog;
    @Override
    protected String doInBackground(Object... params) {
        if (api == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokes-app-142320.appspot.com/_ah/api/");
            api = builder.build();
        }
        context = (Context) params[0];
        if (params[1] != null) progressDialog = (ProgressDialog) params[1];
        try {
            String joke = api.tellJoke().execute().getJoke();
            Log.d("Endpoint", "Returned joke");
            return joke;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (!s.equals("")) {
            Intent i = new Intent(context, DisplayActivity.class);
            i.putExtra("text", s);
            context.startActivity(i);
        } else Toast.makeText(context, "Nope, got nothing", Toast.LENGTH_SHORT).show();
        if (progressDialog != null) progressDialog.dismiss();
        super.onPostExecute(s);
    }
}