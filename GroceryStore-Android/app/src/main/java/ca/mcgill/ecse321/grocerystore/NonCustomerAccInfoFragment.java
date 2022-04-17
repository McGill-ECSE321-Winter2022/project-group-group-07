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

public class NonCustomerAccInfoFragment extends Fragment {

    private View nonCustomerAccInfoView;
    private String error = null;
    private String role;

    private String username;

    //fragment_account_info variables
    private TextView myUsername;
    private TextView myName;
    private TextView myAddress;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        nonCustomerAccInfoView = inflater.inflate(R.layout.fragment_non_customer_acc_info, container, false);
        return  nonCustomerAccInfoView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        role = ((MainActivity) this.getActivity()).getRole();
        username = ((MainActivity) this.getActivity()).getUsername();

        //fragment_account_info variables
        myUsername= nonCustomerAccInfoView.findViewById(R.id.nonCust_myUsername_AccountInfo);
        myName= nonCustomerAccInfoView.findViewById(R.id.nonCust_myName_AccountInfo);
        myAddress = nonCustomerAccInfoView.findViewById(R.id.nonCust_myAddress_AccountInfo);

        getAccountInfo();

        nonCustomerAccInfoView.findViewById(R.id.NonCustomerAccountInfoToProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role.contains("Owner")) {
                    NavHostFragment.findNavController(NonCustomerAccInfoFragment.this)
                            .navigate(R.id.action_NonCustomerInfoFragment_to_OwnerProfileFragment);
                } else {
                    NavHostFragment.findNavController(NonCustomerAccInfoFragment.this)
                            .navigate(R.id.action_NonCustomerInfoFragment_to_EmployeeProfileFragment);
                }
            }
        });

        nonCustomerAccInfoView.findViewById(R.id.EditInfoButtonForNonCustomers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NonCustomerAccInfoFragment.this)
                        .navigate(R.id.action_NonCustomerInfoFragment_to_EditInfoFragment);
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
        nonCustomerAccInfoView = null;
    }

}