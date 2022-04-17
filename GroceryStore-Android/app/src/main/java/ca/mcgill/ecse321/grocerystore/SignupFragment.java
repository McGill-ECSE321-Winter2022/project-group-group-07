package ca.mcgill.ecse321.grocerystore;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    private EditText username;
    private EditText name;
    private EditText password;

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
                signup();
                NavHostFragment.findNavController(SignupFragment.this)
                        .navigate(R.id.action_SignupFragment_to_LoginFragment);
            }
        });
    }

    private void signup(){
        HttpUtils.post("api/account/customerAccount/" +
                username.getText().toString() +
                "?name=" +
                name.getText().toString() +
                "&password=" +
                password.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    currUser += response.get("username").toString();
                    error += "oh hey it worked?";
                } catch (JSONException e) {
                    error += e.getMessage();
                }

            }

            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONObject errorResponse) {
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
        signupView = null;
    }
}
