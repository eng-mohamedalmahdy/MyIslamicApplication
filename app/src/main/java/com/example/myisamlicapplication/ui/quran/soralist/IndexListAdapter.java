package com.example.myisamlicapplication.ui.quran.soralist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.pojo.Jozz;
import com.example.myisamlicapplication.data.pojo.Sora;
import com.example.myisamlicapplication.ui.quran.quranindex.QuranIndexFragmentDirections;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class IndexListAdapter extends RecyclerView.Adapter<IndexListAdapter.ViewHolder> {

    List<?> index;
    Fragment fragment;


    public IndexListAdapter(List<?> index, Fragment fragment) {
        this.index = index;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sora, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (index.get(position) instanceof Sora) {
            holder.bind((Sora) index.get(position));
        } else if (index.get(position) instanceof Jozz) {
            holder.bind((Jozz) index.get(position));
        } else if (index.get(position) instanceof Integer) {
            holder.bind((Integer) index.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return index.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView soraNumber, soraName, from, to, wordTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soraNumber = itemView.findViewById(R.id.sora_number);
            soraName = itemView.findViewById(R.id.sora_name);
            from = itemView.findViewById(R.id.sora_start);
            to = itemView.findViewById(R.id.sora_end);
            wordTo = itemView.findViewById(R.id.word_to);
        }

        public void bind(Sora sora) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            wordTo.setVisibility(View.VISIBLE);

            soraNumber.setText(nf.format(sora.getSoraNumber()));
            soraName.setText(sora.getArabicName());
            from.setText(nf.format(sora.getStartPage()));
            to.setText(nf.format(sora.getEndPage()));
            itemView.setOnClickListener(v -> NavHostFragment
                    .findNavController(fragment)
                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(sora.getStartPage())));
        }

        public void bind(Jozz jozz) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraName.setVisibility(View.GONE);
            wordTo.setVisibility(View.VISIBLE);

            soraNumber.setText(nf.format(jozz.getJozzNumber()));
            from.setText(nf.format(jozz.getStartPage()));
            to.setText(nf.format(jozz.getEndPage()));

            itemView.setOnClickListener(v -> NavHostFragment
                    .findNavController(fragment)
                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(jozz.getStartPage())));
        }

        public void bind(Integer page) {
            NumberFormat nf= NumberFormat.getInstance(new Locale("ar","EG"));

            soraName.setVisibility(View.GONE);
            from.setVisibility(View.GONE);
            to.setVisibility(View.GONE);
            wordTo.setVisibility(View.GONE);

            soraNumber.setText(nf.format(page));

            itemView.setOnClickListener(v -> NavHostFragment
                    .findNavController(fragment)
                    .navigate(QuranIndexFragmentDirections.actionQuranIndexToQuranFragment(page)));
        }

    }
}
