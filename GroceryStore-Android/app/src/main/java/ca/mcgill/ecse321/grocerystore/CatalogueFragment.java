package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CatalogueFragment extends Fragment {

    private View CatalogueView;
    RecyclerView recyclerView;
    List<Item> items;
    String error;
    CatalogueAdapter adapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        CatalogueView = inflater.inflate(R.layout.fragment_catalogue, container, false);
        recyclerView = CatalogueView.findViewById(R.id.catalogueList);
        items = new ArrayList<>();

        updateItems();

        return CatalogueView;
    }

    private void updateItems() {
        HttpUtils.get("api/item/items"
                , new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {//0408

                        for (int i=0; i< response.length();i++) {
                            try {
                                JSONObject itemObject = response.getJSONObject(i);
                                Item item = new Item();
                                item.setItemID(itemObject.getString("itemID"));
                                item.setProductName(itemObject.getString("productName"));
                                item.setPrice(itemObject.getString("price"));
                                items.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter = new CatalogueAdapter(CatalogueView.getContext(), items, getUsername());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CatalogueView.getContext()));


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


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CatalogueView.findViewById(R.id.ViewCartFromCatalogue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CatalogueFragment.this)
                        .navigate(R.id.action_CatalogueFragment_to_CartFragment);
            }
        });
    }

    public String getUsername(){
        return ((MainActivity) this.getActivity()).getUsername();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CatalogueView = null;
    }
}

