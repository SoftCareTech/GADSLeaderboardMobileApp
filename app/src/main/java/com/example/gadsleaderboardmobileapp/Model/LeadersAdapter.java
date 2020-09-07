package com.example.gadsleaderboardmobileapp.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboardmobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeadersAdapter   extends  RecyclerView.Adapter<LeadersAdapter.LeadersViewHolder> {
  private   Context mContext;
   private boolean skillsLeaders;
  private   List<Leaders> leaders;
  private  String useSpace;
    public LeadersAdapter(Context mContext, List<Leaders> leaders, boolean skillsLeaders ) {
  this.skillsLeaders=skillsLeaders;
  this.leaders=leaders;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(skillsLeaders)
            useSpace=mContext.getString(R.string.skill_iq_scores);
        else
        useSpace=mContext.getString(R.string.learning_hours);
        view = LayoutInflater.from(mContext).inflate(R.layout.item_leaders, parent, false);
        return new LeadersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final LeadersViewHolder holder, int position) {

        if(leaders!=null){
            holder.title.setText(leaders.get(position).getName());
            holder.subTitle.setText(leaders.get(position).getScoresOrScoresAndCountry(useSpace));
            final String badgeUrl=leaders.get(position).getBadgeUrl();
           // Picasso.get().load(badgeUrl).fit().centerInside().into(holder.badgeImage)  ;
            Picasso.get().load(badgeUrl).fit().centerInside()
                    .placeholder(R.drawable.ic_twotone_cloud_download_24)
                    .error(R.drawable.ic_round_error_outline_24)
                    .into(holder.badgeImage);
        }

    }




    @Override
    public int getItemCount() {
        if(leaders!=null){
            return  leaders.size();
        }
        return 0;
    }

    static class LeadersViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subTitle;
ImageView badgeImage;
        public LeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.sub_title);
            badgeImage= itemView.findViewById(R.id.badge_image);
        }
    }

}
