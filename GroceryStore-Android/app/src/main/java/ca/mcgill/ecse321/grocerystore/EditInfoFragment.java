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

    /**currently still need a way to get userType**/

    private int userType =1;

    private View editInfoView;
    private String error;

    //EditText for update Name
    private EditText usernameForUpdateName;
    private EditText name;

    //EditText for update Password
    private EditText usernameForUpdatePassword;
    private EditText currPassword;
    private EditText newPassword;

    //EditText for update Address
    private EditText usernameForUpdateAddress;
    private EditText buildingNo;
    private EditText street;
    private EditText town;

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
                usernameForUpdateName = (EditText) editInfoView.findViewById(R.id.myUsername_nameEditInfo);
                name = (EditText) editInfoView.findViewById(R.id.myNewName_nameEditInfo);
                updateName();
            }
        });

        editInfoView.findViewById(R.id.UpdatePasswordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameForUpdatePassword = (EditText) editInfoView.findViewById(R.id.myUsername_passwordEditInfo);
                currPassword = (EditText) editInfoView.findViewById(R.id.myCurrentPassword_passwordEditInfo );
                newPassword = (EditText) editInfoView.findViewById(R.id.myNewPassword_passwordEditInfo );
                updatePassword();
            }
        });

        editInfoView.findViewById(R.id.UpdateAddressButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameForUpdateAddress = (EditText) editInfoView.findViewById(R.id.myUsername_addressEditInfo);
                buildingNo = (EditText) editInfoView.findViewById(R.id.myBuildingNo_addressEditInfo);
                street = (EditText) editInfoView.findViewById(R.id.myStreet_addressEditInfo);
                town = (EditText) editInfoView.findViewById(R.id.myTown_addressEditInfo);
                updateAddress();
            }
        });
    }

    private void updateName() {
        HttpUtils.put("api/account/updateName/" + usernameForUpdateName.getText().toString() +
                "?newName=" + name.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                usernameForUpdateName.setText("");
                name.setText("");
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

    private void updatePassword() {
        HttpUtils.put("api/account/updatePassword/" + usernameForUpdatePassword.getText().toString() +
                "?oldPassword=" + currPassword.getText().toString() + "&newPassword=" + newPassword.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                usernameForUpdatePassword.setText("");
                currPassword.setText("");
                newPassword.setText("");
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

    private void updateAddress() {
        HttpUtils.put("api/address/updateAddress/" + usernameForUpdateAddress.getText().toString() +
                "?buildingNo=" + buildingNo.getText().toString() +
                "&street=" + street.getText().toString() +
                "&town=" + town.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                usernameForUpdateAddress.setText("");
                buildingNo.setText("");
                street.setText("");
                town.setText("");
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
