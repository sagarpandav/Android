package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar.pandav on 23/01/18.
 */

public class AllDataAdapter extends RecyclerView.Adapter <AllDataAdapter.MyViewHolder> implements Filterable {

    List<Data> data = new ArrayList<>();
    List<Data> filterData = new ArrayList<>();

    ItemFilter itemFilter = new ItemFilter();
    Context context;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private final LayoutInflater inflater;

    public AllDataAdapter(Context context, List<Data> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.filterData = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data current = filterData.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return filterData.size();
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView icon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.listText);
            icon = itemView.findViewById(R.id.listIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), getAdapterPosition()+"Position", Toast.LENGTH_SHORT).show();
                    fragmentManager = ((MainActivity)context).getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = new FragmentRouteDetails();
                    Bundle args = new Bundle();
                    Data current = filterData.get(getAdapterPosition());
                    args.putString("title", String.valueOf(current.title));
                    fragment.setArguments(args);
                    fragmentTransaction.replace(R.id.fragment_container, fragment, "routedetails").addToBackStack(null).commit();
                }
            });
        }
    }

    class ItemFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String query = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();
            List<Data> dataItems = data;
            List<Data> resultItems = new ArrayList<>();

            for (int i=0; i<dataItems.size(); i++){
                String str_title = dataItems.get(i).title.toLowerCase();

                if (str_title.contains(query)){
                    resultItems.add(data.get(i));
                }
            }
            filterResults.values = resultItems;
            filterResults.count = resultItems.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filterData = (List<Data>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
