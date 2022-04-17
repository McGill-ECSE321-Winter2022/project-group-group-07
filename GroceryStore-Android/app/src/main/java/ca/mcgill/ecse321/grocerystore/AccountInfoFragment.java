package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AccountInfoFragment extends Fragment {

    private View accountInfoView;
    private String error = null;

    /**currently still need a way to get username**/
    private String username = "Testing";

    //fragment_account_info variables
    private TextView myUsername;
    private TextView myName;
    private TextView myAddress;
    private TextView myCurrentPoints;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        accountInfoView = inflater.inflate(R.layout.fragment_account_info, container, false);
        return accountInfoView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fragment_account_info variables
        myUsername= accountInfoView.findViewById(R.id.myUsername_AccountInfo);
        myName= accountInfoView.findViewById(R.id.myName_AccountInfo);
        myAddress = accountInfoView.findViewById(R.id.myAddress_AccountInfo);
        myCurrentPoints = accountInfoView.findViewById(R.id.myPoints_AccountInfo);

        getAccountInfo();

        accountInfoView.findViewById(R.id.EditInfoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AccountInfoFragment.this)
                        .navigate(R.id.action_AccountInfoFragment_to_EditInfoFragment);
            }
        });


    }

    private void getAccountInfo() {
        error = "";
        HttpUtils.get("api/account/"+username, new RequestParams(), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    myUsername.setText(response.getString("username"));
                    myName.setText(response.getString("name"));
                    myCurrentPoints.setText(response.getString("pointBalance"));
                } catch (Exception e) {
                    error += e.getMessage();
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
        error = "";
        HttpUtils.get("api/address/address/"+username, new RequestParams(), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String tempString = "";
                    tempString += response.getString("buildingNo");
                    tempString += " ";
                    tempString += response.getString("street");
                    tempString += " ";
                    tempString += response.getString("town");
                    myAddress.setText(tempString);
                } catch (Exception e) {
                    error += e.getMessage();
                }
            }
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
    public void onDestroyView() {
        super.onDestroyView();
        accountInfoView = null;
    }

}