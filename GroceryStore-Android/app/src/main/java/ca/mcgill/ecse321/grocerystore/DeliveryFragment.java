package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class DeliveryFragment extends Fragment {
    private View DeliveryView;
    private String error;
    private String currUser;
    private EditText username;
    RecyclerView recyclerView;
    List<Order> orders;
    DeliveryAdapter adapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        DeliveryView = inflater.inflate(R.layout.fragment_delivery_orders, container, false);
        recyclerView = DeliveryView.findViewById(R.id.deliveryOrdersList);
        orders = new ArrayList<>();

        updateOrders();

        return DeliveryView;
    }

    private void updateOrders() {
        HttpUtils.get("api/order/pendingDeliveryOrders"
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

                        adapter = new DeliveryAdapter(DeliveryView.getContext(), orders);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DeliveryView.getContext()));


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
        DeliveryView.findViewById(R.id.updateButtonInDeliveryOrderPage).setOnClickListener(new View.OnClickListener() {
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
        DeliveryView = null;
    }
}
