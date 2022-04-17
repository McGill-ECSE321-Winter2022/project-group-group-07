package ca.mcgill.ecse321.grocerystore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderID,status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.OrderID);
            status = itemView.findViewById(R.id.status);

        }
    }
}
