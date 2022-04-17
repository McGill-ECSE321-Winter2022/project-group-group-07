package ca.mcgill.ecse321.grocerystore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import ca.mcgill.ecse321.grocerystore.databinding.ActivityCheckoutBinding;

public class CheckoutFragment extends Fragment {

    private ActivityCheckoutBinding binding;
    String currentPoints = "100";
    String total = "10000";
    String subtotal = "10000";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = ActivityCheckoutBinding.inflate(inflater, container, false);
//        binding.

        return binding.getRoot();

    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: query for current amount of points
        binding.points.setText(currentPoints);

        //TODO: query for current subtotal of points
        binding.totalComp.setText(total);

        //TODO: query for current total of points
        binding.subtotalComp.setText(subtotal);

        binding.applyPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(binding.pointsDiscount)) {
                    Integer valueToApply = Integer.parseInt(binding.pointsDiscount.getText().toString());
                    Integer currentPointInt = Integer.parseInt(binding.points.getText().toString());
                    Integer currentTotal = Integer.parseInt(total);
                    if (valueToApply <= currentPointInt && valueToApply <= currentTotal) {
                        binding.errorMSG.setVisibility(View.GONE);
                        currentTotal -= valueToApply;
                        currentPointInt -= valueToApply;
                        total = String.valueOf(currentTotal);
                        currentPoints = String.valueOf(currentPointInt);

                        binding.pointsDiscount.getText().clear();
                        binding.totalComp.setText(total);
                        binding.points.setText(currentPoints);
                    } else {
                        binding.errorMSG.setVisibility(View.VISIBLE);
                        binding.pointsDiscount.getText().clear();
                    }
                }
            }
        });

        binding.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: submit data from the fields
                NavHostFragment.findNavController(CheckoutFragment.this)
                        .navigate(R.id.action_third_to_SecondFragment);
            }
        });

        binding.orderOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(binding.deliveryLabel.isChecked()){
                    binding.deliveryWindow.setVisibility(View.VISIBLE);
                } else {
                    binding.deliveryWindow.setVisibility(View.GONE);
                }

                if(binding.pickUpLabel.isChecked()){
                    binding.pickupWindow.setVisibility(View.VISIBLE);
                } else {
                    binding.pickupWindow.setVisibility(View.GONE);
                }
            }
        });

        if(binding.deliveryLabel.isChecked()){
            binding.deliveryCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    String  curDate = String.valueOf(dayOfMonth);
                    String  Year = String.valueOf(year);
                    String  Month = String.valueOf(month);
                }
            });
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}