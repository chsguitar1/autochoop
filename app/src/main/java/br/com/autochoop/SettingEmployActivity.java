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
import java.util.Date;
import java.util.List;

import br.com.autochoop.model.Machine;
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
    @BindView(R.id.rv_machines)
    RecyclerView rvMachines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_employ);
        ButterKnife.bind(this);
        rvProducts.setHasFixedSize(true);
        rvMachines.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerP = new LinearLayoutManager(getApplicationContext());
        rvProducts.setLayoutManager(mLayoutManagerP);
        rvProducts.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvProducts.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManagerM = new LinearLayoutManager(getApplicationContext());
        rvMachines.setLayoutManager(mLayoutManagerM);
        rvMachines.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvMachines.setItemAnimator(new DefaultItemAnimator());

    }

    @OnClick(R.id.bt_search)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                setupRecyclerViewMachine();
                break;

        }
    }

    void setupRecyclerViewProducts() {

        if (!teIdemploy.getText().toString().equals("")) {
            final List<Products> listBeer = new ArrayList<>();
            myRef = database.getReference("autobeer");
            Query query = myRef.child("products").orderByChild("idEmploy").equalTo(teIdemploy.getText().toString());
            if (query != null) {
                A.IDEMPLOY = teIdemploy.getText().toString();
            }
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    rvProducts.setAdapter(null);
                    listBeer.clear();
                    System.out.println("veio " + dataSnapshot);
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Products product = data.getValue(Products.class);
                        listBeer.add(product);
                    }
                    System.out.println("array " + listBeer.toString());
                    adapter = new RecyclerViewProducts(SettingEmployActivity.this, listBeer);
                    rvProducts.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    A.showMsg(databaseError.getMessage() + "\n" + databaseError.getDetails(), SettingEmployActivity.this);
                }
            });
        }

    }
    void setupRecyclerViewMachine() {

        if (!teIdemploy.getText().toString().equals("")) {
            final List<Machine> listMachine = new ArrayList<>();
            myRef = database.getReference("autobeer");
            Query query = myRef.child("machines").orderByChild("idEmploy").equalTo(teIdemploy.getText().toString());
            if (query != null) {
                A.IDEMPLOY = teIdemploy.getText().toString();
            }
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    rvMachines.setAdapter(null);
                    listMachine.clear();
                    System.out.println("veio " + dataSnapshot);
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Machine machine = data.getValue(Machine.class);
                        listMachine.add(machine);
                    }
                    System.out.println("array " + listMachine.toString());


                    adapter = new RecyclerViewMachine(SettingEmployActivity.this, listMachine);
                    rvMachines.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    A.showMsg(databaseError.getMessage() + "\n" + databaseError.getDetails(), SettingEmployActivity.this);
                }
            });
        }

    }

    public class RecyclerViewProducts extends RecyclerView.Adapter<RecyclerViewProducts.ViewHolder> {

        Context context;
        List<Products> productsList;


        public RecyclerViewProducts(Context context, List<Products> TempList) {

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
                    Products.deleteAll(Products.class);
                    Telemetria.deleteAll(Telemetria.class);
                    product.save();
                    Telemetria t = new Telemetria(new Date(), Integer.parseInt(product.getIdproduct()));
                    t.save();
                    if(A.IDMACHINE  != null){
                        myRef.child("machines").child(A.IDMACHINE.getIdmachine()).child("product").setValue(product);
                        Intent it = new Intent(SettingEmployActivity.this, MainActivity.class);
                        startActivity(it);
                        finish();
                    }
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
    public class RecyclerViewMachine extends RecyclerView.Adapter<RecyclerViewMachine.ViewHolder> {

        Context context;
        List<Machine> machineList;


        public RecyclerViewMachine(Context context, List<Machine> TempList) {

            this.machineList = TempList;

            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_machine, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final Machine machine = machineList.get(position);
            holder.name.setText(machine.getIdmachine());

            holder.btSel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    A.IDMACHINE = machine;
                    setupRecyclerViewProducts();
                }
            });

        }

        @Override
        public int getItemCount() {

            return machineList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            Button btSel;


            public ViewHolder(View itemView) {

                super(itemView);
                ButterKnife.bind(this, itemView);
                name = (TextView) itemView.findViewById(R.id.tv_name_machine);
                btSel = (Button) itemView.findViewById(R.id.bt_sel_machine);
            }

        }
    }

}
