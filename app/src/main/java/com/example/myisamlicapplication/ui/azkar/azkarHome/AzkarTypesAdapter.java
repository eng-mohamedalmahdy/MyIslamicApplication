package com.example.myisamlicapplication.ui.azkar.azkarHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.pojo.azkar.ZekrType;

import java.util.ArrayList;
import java.util.HashSet;

public class AzkarTypesAdapter extends RecyclerView.Adapter<AzkarTypesAdapter.ViewHolder> {

   private ArrayList<ZekrType> azkarTypes;
   private Fragment fragment;

    public AzkarTypesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_zekr, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(azkarTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return azkarTypes == null ? 0 : azkarTypes.size();
    }

    public void setAzkarTypes(HashSet<ZekrType> azkarTypes) {
        this.azkarTypes = new ArrayList<>(azkarTypes);
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView zekrName;
        ImageView zekrImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrName = itemView.findViewById(R.id.zekr_name);
            zekrImage = itemView.findViewById(R.id.zeker_image);
        }

        public void bind(ZekrType zekrType) {
            zekrName.setText(zekrType.getZekrName());
            if (zekrType.getZekrImageId() != -1) {
                zekrImage.setImageResource(zekrType.getZekrImageId());
            }
            itemView.setOnClickListener(v-> NavHostFragment
                    .findNavController(fragment).
                            navigate(AzkarHomeFragmentDirections.
                                    actionAzkarHomeFragmentToAzkarListFragment(zekrType.getZekrName())));
        }
    }

}
