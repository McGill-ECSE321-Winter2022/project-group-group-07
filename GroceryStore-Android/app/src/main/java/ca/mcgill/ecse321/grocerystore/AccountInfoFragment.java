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

import ca.mcgill.ecse321.grocerystore.databinding.FragmentAccountInfoBinding;
import cz.msebera.android.httpclient.entity.mime.Header;

public class AccountInfoFragment extends Fragment {

    private FragmentAccountInfoBinding binding;
    private String error = null;
    private String username = "Test";
    private View accountInfoView;

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
        binding = FragmentAccountInfoBinding.inflate(inflater, container, false);
        /*accountInfoView = inflater.inflate(R.layout.fragment_account_info, container, false);

        //fragment_account_info variables
        myUsername= accountInfoView.findViewById(R.id.myUsername_AccountInfo);
        myName= accountInfoView.findViewById(R.id.myName_AccountInfo);
        myAddress = accountInfoView.findViewById(R.id.myAddress_AccountInfo);
        myCurrentPoints = accountInfoView.findViewById(R.id.myPoints_AccountInfo);

        //function needed on page creation/redirection for AccountInfo
        error = "";
        HttpUtils.get("/api/account/".concat(username), new RequestParams(), new JsonHttpResponseHandler() {
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
        HttpUtils.get("/api/address/address/".concat(username), new RequestParams(), new JsonHttpResponseHandler() {
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
        });*/
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.EditInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AccountInfoFragment.this)
                        .navigate(R.id.action_AccountInfoFragment_to_EditInfoFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}