package br.com.autochoop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.Query;

import net.qiujuer.genius.ui.widget.Button;

import java.util.ArrayList;

import br.com.autochoop.model.Products;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingEmployActivity extends AppCompatActivity {

    @BindView(R.id.te_idemploy)
    TextInputEditText teIdemploy;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_employ);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_search)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                break;

        }
    }
    public class MyAdapter extends FirebaseRecyclerAdapter<MyAdapter.ViewHolder, Products> {



        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.tv_name_product)
            TextView tvNameProduct;
            @BindView(R.id.tv_price)
            TextView tvPrice;


            public ViewHolder(View view) {
                super(view);

            }

            @Override
            public void onClick(View v) {
                Products.deleteAll(Products.class);
                Products p = getItems().get(getAdapterPosition());
                p.save();

            }
        }

        public MyAdapter(Query query, @Nullable ArrayList<Products> items,
                         @Nullable ArrayList<String> keys) {
            super(query, items, keys);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);

            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Products item = getItem(position);
            holder.tvNameProduct.setText(item.getNameProduct());
            holder.tvPrice.setText(A.formatarValor(item.getValueProduct()));

        }

        @Override
        protected void itemAdded(Products item, String key, int position) {
            Log.d("MyAdapter", "Added a new item to the adapter.");
        }

        @Override
        protected void itemChanged(Products oldItem, Products newItem, String key, int position) {
            Log.d("MyAdapter", "Changed an item.");
        }

        @Override
        protected void itemRemoved(Products item, String key, int position) {
            Log.d("MyAdapter", "Removed an item from the adapter.");
        }

        @Override
        protected void itemMoved(Products item, String key, int oldPosition, int newPosition) {
            Log.d("MyAdapter", "Moved an item.");
        }
    }
}
