package ca.mcgill.ecse321.grocerystore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    LayoutInflater inflater;
    List<Order> orders;


    public Adapter(Context ctx, List<Order> orders){
        this.inflater=LayoutInflater.from(ctx);
        this.orders=orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.orderID.setText(orders.get(position).getOrderID());
        holder.status.setText(orders.get(position).getStatus());
        holder.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpUtils.put("/api/order/PickUpUpdate/"+orders.get(holder.getAdapterPosition()).getOrderID()+"?status=Ready",new RequestParams(), new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {//0408
                        orders.remove(holder.getAdapterPosition());
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderID,status;
        Button complete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.OrderID);
            status = itemView.findViewById(R.id.status);
             complete= itemView.findViewById(R.id.markAsComplete);
        }
    }
}
