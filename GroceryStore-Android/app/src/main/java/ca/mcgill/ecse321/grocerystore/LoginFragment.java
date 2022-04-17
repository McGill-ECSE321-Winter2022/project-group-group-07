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

public class LoginFragment extends Fragment {

    private View loginView;
    private String error = null;
    private String userRole = "";

    private EditText username;
    private EditText password;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        return loginView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginView.findViewById(R.id.SignUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_LoginFragment_to_SignupFragment);
            }
        });

        loginView.findViewById(R.id.LogInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = (EditText) loginView.findViewById(R.id.myUsername_LogIn);
                password = (EditText) loginView.findViewById(R.id.myPassword_LogIn);

                login(username, password);

            }
        });
        refreshErrorMessage();
    }

    private void login(EditText username, EditText password) {
        error = "";
        HttpUtils.post("api/account/login/?" + "username=" + username.getText().toString() + "&password=" +
                password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            setCurrUser(response.get("username").toString());
                            setCurrRole(response.get("role").toString());
                            userRole = response.get("role").toString();
                            navigateFromLogin(userRole);
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

    private void setCurrUser(String userN){
        ((MainActivity) this.getActivity()).setUsername(userN);
    }

    private void setCurrRole(String userR){
        ((MainActivity) this.getActivity()).setRole(userR);
    }

    private void navigateFromLogin(String role){
        if (role.contains("Customer")) {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_CustomerProfileFragment);
        }
        else if (role.contains("Employment")){
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_EmployeeProfileFragment);
        } else {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_OwnerProfileFragment);
        }
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView console = (TextView) loginView.findViewById(R.id.login_error);
        console.setText(error);

        if (error == null || error.length() == 0) {
            console.setVisibility(View.GONE);
        } else {
            console.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginView = null;
    }
}
