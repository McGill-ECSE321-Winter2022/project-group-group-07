package ca.mcgill.ecse321.grocerystore;


import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

public class EmployeeProfileFragment extends Fragment {

    private View employeeProfileView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        employeeProfileView = inflater.inflate(R.layout.employee_profile, container, false);
        return employeeProfileView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeProfileView.findViewById(R.id.EmployeeViewAccountInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EmployeeProfileFragment.this)
                        .navigate(R.id.action_EmployeeProfileFragment_to_NonCustomerInfoFragment);
            }
        });

        employeeProfileView.findViewById(R.id.EmployeeLogoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUser();
                NavHostFragment.findNavController(EmployeeProfileFragment.this)
                        .navigate(R.id.action_EmployeeProfileFragment_to_LoginFragment);
            }
        });

        employeeProfileView.findViewById(R.id.EmployeeViewCustomerOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDelivery()) {
                    NavHostFragment.findNavController(EmployeeProfileFragment.this)
                            .navigate(R.id.action_EmployeeProfileFragment_to_DeliveryFragment);
                } else {
                    NavHostFragment.findNavController(EmployeeProfileFragment.this)
                            .navigate(R.id.action_EmployeeProfileFragment_to_PickUpFragment);
                }
            }
        });
    }

    private boolean isDelivery() {
        String role= ((MainActivity) this.getActivity()).getRole();
        if (role.contains("Delivery")){
            return true;
        }else {
            return false;
        }
    }

    private void resetUser() {
        ((MainActivity) this.getActivity()).setUsername("");
        ((MainActivity) this.getActivity()).setRole("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        employeeProfileView = null;
    }
}

