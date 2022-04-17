package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditInfoFragment extends Fragment {

    private int userType =1;
    private View editInfoView;
    private EditText username;
    private EditText name;
    private String error;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        editInfoView = inflater.inflate(R.layout.fragment_edit_info, container, false);
        return editInfoView;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editInfoView.findViewById(R.id.EditInfoButtonToAccountInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType == 1 ) {
                    NavHostFragment.findNavController(EditInfoFragment.this)
                            .navigate(R.id.action_EditInfoFragment_to_AccountInfoFragment);
                } else {
                    NavHostFragment.findNavController(EditInfoFragment.this)
                            .navigate(R.id.action_EditInfoFragment_to_NonCustomerInfoFragment);
                }
            }
        });

        editInfoView.findViewById(R.id.UpdateNameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) editInfoView.findViewById(R.id.myUsername_nameEditInfo);
                name = (EditText) editInfoView.findViewById(R.id.myNewName_nameEditInfo);
                updateName();
            }
        });
    }

    private void updateName() {
        HttpUtils.put("api/account/updateName/" + username.getText().toString() +
                "?newName=" + name.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String success = response.get("username").toString();
                    username.setText("");
                    name.setText("");
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
        editInfoView = null;
    }
}
