package br.com.autochoop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import net.qiujuer.genius.ui.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.autochoop.model.Products;
import br.com.autochoop.model.Telemetria;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_employ);
        ButterKnife.bind(this);
        rvProducts.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducts.setLayoutManager(mLayoutManager);
        rvProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvProducts.setItemAnimator(new DefaultItemAnimator());

    }

    @OnClick(R.id.bt_search)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
setupRecyclerView();
                break;

        }
    }

    void setupRecyclerView() {

        if (!teIdemploy.getText().toString().equals("")) {
            final List<Products> listBeer = new ArrayList<>();
          myRef =   database.getReference("autobeer");
            Query query = myRef.child("products").orderByChild("idEmploy").equalTo(teIdemploy.getText().toString());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("veio "+dataSnapshot);
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Products product = data.getValue(Products.class);
                        listBeer.add(product);
                    }
                    System.out.println("array "+listBeer.toString());
                    adapter = new RecyclerViewAdapter(SettingEmployActivity.this, listBeer);
                    rvProducts.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    A.showMsg(databaseError.getMessage() + "\n" + databaseError.getDetails(), SettingEmployActivity.this);
                }
            });
        }

    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        Context context;
        List<Products> productsList;


        public RecyclerViewAdapter(Context context, List<Products> TempList) {

            this.productsList = TempList;

            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final Products product = productsList.get(position);
            holder.name.setText(product.getNameproduct());
            holder.price.setText(product.getValueproduct());
            holder.btSel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.save();
                    Telemetria t = Telemetria.listAll(Telemetria.class).get(0);
                    System.out.println("teste : "+Telemetria.listAll(Telemetria.class));
                    t.setIdproduct(Integer.parseInt(product.getIdproduct()));
                    t.save();
                    Intent it = new Intent(SettingEmployActivity.this,MainActivity.class);
                    startActivity(it);
                    finish();
                }
            });

        }

        @Override
        public int getItemCount() {

            return productsList.size();
        }



        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView price;
            Button btSel;



            public ViewHolder(View itemView) {

                super(itemView);
                ButterKnife.bind(this, itemView);
                name = (TextView) itemView.findViewById(R.id.tv_name_product);

                price = (TextView) itemView.findViewById(R.id.tv_price);
                btSel = (Button) itemView.findViewById(R.id.bt_sel);
            }

        }
    }



}
