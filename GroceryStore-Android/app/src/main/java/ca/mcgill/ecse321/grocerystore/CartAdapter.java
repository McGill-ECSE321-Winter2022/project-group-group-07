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

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {
    LayoutInflater inflater;
    List<Item> items;
    String username;

    public CartAdapter(Context ctx, List<Item> items, String username) {
        this.inflater = LayoutInflater.from(ctx);
        this.items = items;
        this.username = username;
    }

    @NonNull
    @Override
    public CartAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_view_item_2,parent,false);
        return new CartAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterViewHolder holder, int position) {
        holder.price.setText(items.get(position).getPrice());
        holder.productName.setText(items.get(position).getProductName());
        holder.remButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtils.put("/api/cart/removeFromCart/" +
                        items.get(holder.getAdapterPosition()).getItemID()+"?username=" + username, new RequestParams(), new JsonHttpResponseHandler() {
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    public class CartAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView  price, productName;
        Button remButton;
        public CartAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            productName = itemView.findViewById(R.id.productName);
            remButton= itemView.findViewById(R.id.remButton);
        }
    }
}