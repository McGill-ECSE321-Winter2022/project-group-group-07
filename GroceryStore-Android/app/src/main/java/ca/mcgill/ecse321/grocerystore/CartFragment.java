package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class CartFragment extends Fragment {

    private View CartView;
    RecyclerView recyclerView;
    List<Item> items;
    String error;
    CartAdapter adapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        CartView = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = CartView.findViewById(R.id.cartList);
        items = new ArrayList<>();

        updateItems();

        return CartView;
    }

    private void updateItems() {
        HttpUtils.get("api/cart/cart/" + getUsername()
                , new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject resp) {//0408

                        JSONArray response= null;
                        try {
                            response = resp.getJSONArray("items");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

                        adapter = new CartAdapter(CartView.getContext(), items, getUsername());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CartView.getContext()));


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

    }

    public String getUsername(){
        return ((MainActivity) this.getActivity()).getUsername();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CartView = null;
    }
}

