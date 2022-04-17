package ca.mcgill.ecse321.grocerystore;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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

//import ca.mcgill.ecse321.grocerystore.databinding.FragmentLoginBinding;
import cz.msebera.android.httpclient.Header;

public class LoginFragment extends Fragment {

    //private FragmentLoginBinding binding;
    private View loginView;
    private String error = null;
    private String currUsername = null;
    private int currUserType = 0;
    private int userType = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        /*binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();*/
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
                error = "";
                EditText username = (EditText) loginView.findViewById(R.id.myUsername_LogIn);
                EditText password = (EditText) loginView.findViewById(R.id.myPassword_LogIn);

                login(username, password);

            }
        });
    }

    private void login(EditText username, EditText password) {

        HttpUtils.post("account/" + username.getText().toString() +
                password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        refreshErrorMessage();
                        try {
                            currUsername += response.get("username").toString();
                        } catch (JSONException e) {
                            //error += e.getMessage();
                        }
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
        if (userType == 1) {
            /*NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_CustomerProfileFragment);*/
            refreshErrorMessage();
        }
        else if (userType == 2 ){
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
