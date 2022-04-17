package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CatalogueFragment extends Fragment {

    private View catologueView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        catologueView = inflater.inflate(R.layout.fragment_catalogue,container,false);
        return catologueView;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);
        catologueView.findViewById(R.id.addToCartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
    }


}
