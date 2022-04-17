package ca.mcgill.ecse321.grocerystore;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignupFragment extends Fragment {

    private View signupView;
    private String error;
    private String currUser;
    private String currAddress;
    private EditText username;
    private EditText name;
    private EditText password;
    private EditText buildingNo;
    private EditText street;
    private EditText town;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        signupView = inflater.inflate(R.layout.fragment_signup, container, false);
        return signupView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupView.findViewById(R.id.SignUpButtonInSignUpPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) signupView.findViewById(R.id.myUsername_SignUpPage);
                name = (EditText) signupView.findViewById(R.id.myName_SignUpPage);
                password = (EditText) signupView.findViewById(R.id.myPassword_SignUpPage);
                buildingNo = (EditText) signupView.findViewById(R.id.myBuildingNo_SignUpPage);
                street = (EditText) signupView.findViewById(R.id.myStreet_SignUpPage);
                town = (EditText) signupView.findViewById(R.id.myTown_SignUpPage);
                signup();
            }
        });
        refreshErrorMessage();
    }

    private void signup(){
        error = "";
        HttpUtils.post("api/account/customerAccount/" +
                username.getText().toString() +
                "?name=" +
                name.getText().toString() +
                "&password=" +
                password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    setAddress(response.get("username").toString());
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    private void setAddress(String userN){
        HttpUtils.post(
                "api/address/address/" +
                        username.getText().toString() +
                        "?buildingNo=" +
                        buildingNo.getText().toString() +
                        "&street=" +
                        street.getText().toString() +
                        "&town=" +
                        town.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            response.get("street");
                            navigateFromSignup();
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }
                        refreshErrorMessage();
                    }

                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        try {
                            error += errorResponse.get("message").toString();
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }
                        refreshErrorMessage();
                    }
                });
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView console = (TextView) signupView.findViewById(R.id.signup_error);
        console.setText(error);

        if (error == null || error.length() == 0) {
            console.setVisibility(View.GONE);
        } else {
            console.setVisibility(View.VISIBLE);
        }
    }

    private void navigateFromSignup(){
        NavHostFragment.findNavController(SignupFragment.this)
                .navigate(R.id.action_SignupFragment_to_LoginFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        signupView = null;
    }
}
