package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


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

public class PickUpFragment extends Fragment {
    private View PickUpView;
    private String error;
    private String currUser;
    private EditText username;
    RecyclerView recyclerView;
    List<Order> orders;
    Adapter adapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        PickUpView = inflater.inflate(R.layout.fragment_pickup_orders, container, false);
        recyclerView = PickUpView.findViewById(R.id.pickUpOrdersList);
        orders = new ArrayList<>();

        updateOrders();

        return PickUpView;
    }

    private void updateOrders() {
        HttpUtils.get("api/order/pendingPickUpOrders"
                , new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {//0408

                            for (int i=0; i< response.length();i++) {
                                try {
                                    JSONObject orderObject = response.getJSONObject(i);
                                    Order order = new Order();
                                    order.setOrderID(orderObject.getString("orderID"));
                                    order.setStatus(orderObject.getString("status"));
                                    orders.add(order);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        adapter = new Adapter(PickUpView.getContext(), orders);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PickUpView.getContext()));


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
    private void refreshErrorMessage() {
        // set the error message
        TextView console = (TextView) PickUpView.findViewById(R.id.pickup_error);
        console.setText(error);

        if (error == null || error.length() == 0) {
            console.setVisibility(View.GONE);
        } else {
            console.setVisibility(View.VISIBLE);
        }
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PickUpView.findViewById(R.id.updateButtonInPickUpPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orders = new ArrayList<>();

                updateOrders();
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PickUpView = null;
    }
}
