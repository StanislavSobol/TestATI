package com.sobolgmail.i.stanislav.testati.cargos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sobolgmail.i.stanislav.testati.R;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

class CargosAdapter extends RecyclerView.Adapter<CargosAdapter.Holder> {

    private List<CargoViewModel> items = new ArrayList<>();
    @Setter
    private OnItemClickListener onItemClickListener;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cargo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<CargoViewModel> cargoViewModels) {
        items.clear();
        items.addAll(cargoViewModels);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.cargo_item_cargo_type_text_view)
        TextView cargoTypeTextView;

        @BindView(R.id.cargo_item_loading_city_text_view)
        TextView loadingCityTextView;

        @BindView(R.id.cargo_item_unloading_city_text_view)
        TextView unloadingCityTextView;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final CargoViewModel vm) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(vm.getId());
                    }
                }
            });
            cargoTypeTextView.setText(vm.getCargoType());
            loadingCityTextView.setText(vm.getLoadingCity());
            unloadingCityTextView.setText(vm.getUnloadingCity());
        }
    }

    interface OnItemClickListener {
        void OnItemClick(String id);
    }
}
