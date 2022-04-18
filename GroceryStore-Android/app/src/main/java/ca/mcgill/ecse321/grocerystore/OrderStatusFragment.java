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

public class OrderStatusFragment extends Fragment {
    private View OrderStatusView;
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
        OrderStatusView = inflater.inflate(R.layout.fragment_orderstatus, container, false);
        recyclerView = OrderStatusView.findViewById(R.id.customerOrderList);
        orders = new ArrayList<>();

        updateOrders();

        return OrderStatusView;
    }

    private void updateOrders() {
        HttpUtils.get("api/order/order/" + getUsername()
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

                        adapter = new DeliveryAdapter(OrderStatusView.getContext(), orders);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(OrderStatusView.getContext()));


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
        orders = new ArrayList<>();
        updateOrders();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OrderStatusView = null;
    }

    public String getUsername(){
      return  ((MainActivity) this.getActivity()).getUsername();
    }
}
