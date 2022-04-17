package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class EditInfoFragment extends Fragment {

    private View editInfoView;

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

        editInfoView.findViewById(R.id.AccountInfoToEditInfoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditInfoFragment.this)
                        .navigate(R.id.action_EditInfoFragment_to_AccountInfoFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        editInfoView = null;
    }
}
