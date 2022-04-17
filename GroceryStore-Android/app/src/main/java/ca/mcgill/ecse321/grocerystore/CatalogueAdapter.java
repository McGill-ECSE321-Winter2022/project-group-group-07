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

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.CatalogueAdapterViewHolder> {
    LayoutInflater inflater;
    List<Item> items;
    String username;

    public CatalogueAdapter(Context ctx, List<Item> items, String username) {
        this.inflater = LayoutInflater.from(ctx);
        this.items = items;
        this.username = username;
    }

    @NonNull
    @Override
    public CatalogueAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_view_item_1,parent,false);
        return new CatalogueAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueAdapterViewHolder holder, int position) {
        holder.price.setText(items.get(position).getPrice());
        holder.productName.setText(items.get(position).getProductName());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtils.put("/api/cart/addToCart/" +
                        items.get(holder.getAdapterPosition()).getItemID()+"?username=" + username, new RequestParams(), new JsonHttpResponseHandler() {
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    public class CatalogueAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView  price, productName;
        Button addToCart;
        public CatalogueAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            productName = itemView.findViewById(R.id.productName);
            addToCart= itemView.findViewById(R.id.addToCartButton);
        }
    }
}
