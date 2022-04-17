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

import ca.mcgill.ecse321.grocerystore.databinding.FragmentNonCustomerAccInfoBinding;
import cz.msebera.android.httpclient.entity.mime.Header;

public class NonCustomerAccInfoFragment extends Fragment {

    private View nonCustomerAccInfoView;
    private String error = null;
    private String role;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        nonCustomerAccInfoView = inflater.inflate(R.layout.fragment_account_info, container, false);
        return  nonCustomerAccInfoView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        role = ((MainActivity) this.getActivity()).getRole();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        nonCustomerAccInfoView = null;
    }

}