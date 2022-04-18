package ca.mcgill.ecse321.grocerystore;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

public class CustomerProfileFragment extends Fragment {

    private View customerProfileView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        customerProfileView = inflater.inflate(R.layout.customer_profile, container, false);
        return customerProfileView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customerProfileView.findViewById(R.id.ViewAccountInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CustomerProfileFragment.this)
                        .navigate(R.id.action_CustomerProfileFragment_to_AccountInfoFragment);
            }
        });

        customerProfileView.findViewById(R.id.BrowseProducts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CustomerProfileFragment.this)
                        .navigate(R.id.action_CustomerProfileFragment_to_CatalogueFragment);
            }
        });

        customerProfileView.findViewById(R.id.ViewCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CustomerProfileFragment.this)

                        .navigate(R.id.action_CustomerProfileFragment_to_CartFragment);
            }
        });


        customerProfileView.findViewById(R.id.CustomerLogoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUser();
                NavHostFragment.findNavController(CustomerProfileFragment.this)
                        .navigate(R.id.action_CustomerProfileFragment_to_LoginFragment);
            }
        });
    }

    private void resetUser() {
        ((MainActivity) this.getActivity()).setUsername("");
        ((MainActivity) this.getActivity()).setRole("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        customerProfileView = null;
    }
}