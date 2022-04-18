package ca.mcgill.ecse321.grocerystore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class CheckoutFragment extends Fragment {
    String error = "";
    private View checkoutView;
    String username;
    private TextView currentPoints;
    private TextView discountPoints;
    private String orderType;
    private TextView totalComp;
    private TextView subtotal;

    private Integer pointToCashRatio;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        checkoutView = inflater.inflate(R.layout.activity_checkout, container, false);
        return checkoutView;

    }
    public void navigate(){
        NavHostFragment.findNavController(CheckoutFragment.this)
                .navigate(R.id.action_checkoutFragment_to_CustomerProfileFragment);
    }
    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = ((MainActivity) this.getActivity()).getUsername();

        //Checking for amount of points in account
        currentPoints = checkoutView.findViewById(R.id.points);
        getPoints();

        //checking for current cart subtotal
        subtotal = checkoutView.findViewById(R.id.subtotal_comp);

        discountPoints = checkoutView.findViewById(R.id.pointsDiscount);
        totalComp = checkoutView.findViewById(R.id.total_comp);


        checkoutView.findViewById(R.id.BackToProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    NavHostFragment.findNavController(CheckoutFragment.this)
                            .navigate(R.id.action_checkoutFragment_to_CustomerProfileFragment);
            }
        });

        checkoutView.findViewById(R.id.placeOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpUtils.put("api/cart/chooseOrderType/" + username + "?orderType=" + orderType,new RequestParams(), new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    }   });
                HttpUtils.post("api/order/checkout/"+username+"?points=0",new RequestParams(), new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        navigate();
                    }
                });


            }
        });



        ((RadioGroup) checkoutView.findViewById(R.id.order_option)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(((RadioButton)checkoutView.findViewById(R.id.deliveryLabel)).isChecked()){
                    checkoutView.findViewById(R.id.delivery_window).setVisibility(View.VISIBLE);
                    orderType = "Delivery";
                } else {
                    checkoutView.findViewById(R.id.delivery_window).setVisibility(View.GONE);
                }

                if(((RadioButton)checkoutView.findViewById(R.id.pickUpLabel)).isChecked()){
                    checkoutView.findViewById(R.id.pickup_window).setVisibility(View.VISIBLE);
                    orderType= "PickUp";
                } else {
                    checkoutView.findViewById(R.id.pickup_window).setVisibility(View.GONE);
                }
            }
        });

        if(((RadioButton)checkoutView.findViewById(R.id.deliveryLabel)).isChecked()){
            ((CalendarView) checkoutView.findViewById(R.id.deliveryCalendar)).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    String  curDate = String.valueOf(dayOfMonth);
                    String  Year = String.valueOf(year);
                    String  Month = String.valueOf(month);
                }
            });
        }

    }

    private void getPoints() {
        HttpUtils.get("api/account/" + username, new RequestParams(), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    currentPoints.setText(response.getString("pointBalance"));
                } catch (Exception e) {
                    error += e.getMessage();
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
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
        checkoutView = null;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}