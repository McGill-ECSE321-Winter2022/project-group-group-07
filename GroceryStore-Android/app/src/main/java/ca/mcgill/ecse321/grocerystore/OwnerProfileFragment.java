package ca.mcgill.ecse321.grocerystore;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

public class OwnerProfileFragment extends Fragment {

    private View ownerProfileView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ownerProfileView = inflater.inflate(R.layout.owner_profile, container, false);
        return ownerProfileView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ownerProfileView.findViewById(R.id.OwnerViewAccountInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(OwnerProfileFragment.this)
                        .navigate(R.id.action_OwnerProfileFragment_to_NonCustomerInfoFragment);
            }
        });

        ownerProfileView.findViewById(R.id.OwnerLogoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetUser();
                NavHostFragment.findNavController(OwnerProfileFragment.this)
                        .navigate(R.id.action_OwnerProfileFragment_to_LoginFragment);
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
        ownerProfileView = null;
    }
}