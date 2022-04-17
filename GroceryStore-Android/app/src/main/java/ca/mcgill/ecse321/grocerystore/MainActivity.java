package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import cz.msebera.android.httpclient.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String error = null;
    private String username = "testing1";

    //fragment_account_info variables
    private TextView myUsername;
    private TextView myName;
    private TextView myAddress;
    private TextView myCurrentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I wanna go back to my profile using this button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //fragment_account_info variables
        myUsername= (TextView)findViewById(R.id.myUsername_AccountInfo);
        myName= (TextView)findViewById(R.id.myName_AccountInfo);
        myAddress = (TextView)findViewById(R.id.myAddress_AccountInfo);
        myCurrentPoints = (TextView)findViewById(R.id.myPoints_AccountInfo);

        //function needed on page creation for AccountInfo
        error = "";
        HttpUtils.get("api/account/"+ username, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    myUsername.setText(response.getString("username"));
                    myName.setText(response.getString("name"));
                    myCurrentPoints.setText(response.getString("pointBalance"));
                } catch (Exception e) {
                    error += e.getMessage();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });

        error = "";
        HttpUtils.get("api/address/address/".concat(username), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String tempString = "";
                    tempString += response.getString("buildingNo");
                    tempString += response.getString("street");
                    tempString += response.getString("town");
                    myAddress.setText(tempString);
                } catch (Exception e) {
                    error += e.getMessage();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateName(View view) {
        //yet to be written
    }

    public void updatePassword(View view) {
        //yet to be written
    }
}