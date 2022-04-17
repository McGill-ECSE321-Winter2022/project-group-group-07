package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import ca.mcgill.ecse321.grocerystore.databinding.FragmentEditInfoBinding;

public class EditInfoFragment extends Fragment {

    private FragmentEditInfoBinding binding;
    private int userType = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEditInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AccountInfoToEditInfoButton.setOnClickListener(new View.OnClickListener() {
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
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
