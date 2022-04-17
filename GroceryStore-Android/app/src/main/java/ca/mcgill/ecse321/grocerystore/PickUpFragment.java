package ca.mcgill.ecse321.grocerystore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


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
        recyclerView.setLayoutManager(new LinearLayoutManager(PickUpView.getContext()));
         adapter = new Adapter(PickUpView.getContext(), orders);
        recyclerView.setAdapter(adapter);

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
                                    order.setOrderID(orderObject.getString("orderID").toString());
                                    order.setStatus(orderObject.getString("status").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        recyclerView.setLayoutManager(new LinearLayoutManager(PickUpView.getContext().getApplicationContext()));
                        adapter = new Adapter(PickUpView.getContext().getApplicationContext(), orders);
                        recyclerView.setAdapter(adapter);
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
        PickUpView.findViewById(R.id.updateButtonInPickUpPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) PickUpView.findViewById(R.id.myUsername_SignUpPage); //0408

                signup();
                NavHostFragment.findNavController(PickUpFragment.this)
                        .navigate(R.id.action_SignupFragment_to_LoginFragment);//0408
            }
        });
    }

    private void signup(){
        HttpUtils.get("api/order/pendingPickUpOrders"
                , new RequestParams(), new JsonHttpResponseHandler() {

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {//0408
                        try {
                            currUser += response.get("username").toString();
                            error += "oh hey it worked?";
                        } catch (JSONException e) {
                            error += e.getMessage();
                        }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PickUpView = null;
    }
}
