package ca.mcgill.ecse321.grocerystore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.OrderStatusAdapterViewHolder> {
        LayoutInflater inflater;
        List<Order> orders;

        public OrderStatusAdapter(Context ctx, List<Order> orders) {
            this.inflater = LayoutInflater.from(ctx);
            this.orders = orders;
        }

        @NonNull
        @Override
        public OrderStatusAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=inflater.inflate(R.layout.recycler_view_item_3,parent,false);
            return new OrderStatusAdapterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderStatusAdapterViewHolder holder, int position) {
            holder.orderID.setText(orders.get(position).getOrderID());
            holder.status.setText(orders.get(position).getStatus());

        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
        public class OrderStatusAdapterViewHolder extends RecyclerView.ViewHolder {
            TextView orderID, status;

            public OrderStatusAdapterViewHolder(@NonNull View itemView) {
                super(itemView);
                orderID = itemView.findViewById(R.id.customerOrderID);
                status = itemView.findViewById(R.id.customerOrderStatus);

            }
        }
    }

