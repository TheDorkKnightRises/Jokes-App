package thedorkknightrises.jokesapp;

import android.app.ProgressDialog;
import android.content.Context;

import junit.framework.TestCase;

/**
 * Created by Samriddha Basu on 9/7/2016.
 */
public class GetJokeTaskTest extends TestCase{
    private Context context;
    private ProgressDialog progressDialog;

    public void testDoInBackground() throws Exception {
        String joke = new GetJokeTask().doInBackground(context, progressDialog);
        assertNotNull("Null string returned", joke);
        assertNotSame("Error fetching joke", "", joke);
    }
}