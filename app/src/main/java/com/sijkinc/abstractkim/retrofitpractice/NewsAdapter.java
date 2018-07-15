package com.sijkinc.abstractkim.retrofitpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

         private Context mContext;
     private List<News> newsList;
 
             public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title, source;
         public ImageView thumbnail, overflow;
 
                 public MyViewHolder(View view) {
                         super(view);
                         title = (TextView) view.findViewById(R.id.title);
                         source = (TextView) view.findViewById(R.id.source);
                         thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                         //overflow = (ImageView) view.findViewById(R.id.overflow);
                     }
     }
 
         
             public NewsAdapter(Context mContext, List<News> albumList) {
                 this.mContext = mContext;
                 this.newsList = albumList;
             }
 
             @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 View itemView = LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.news_card, parent, false);
         
                         return new MyViewHolder(itemView);
             }
 
             @Override
     public void onBindViewHolder(final MyViewHolder holder, int position) {
                 News album = newsList.get(position);
                 holder.title.setText(album.getName());
                 holder.source.setText(album.getSource());
         
                         // loading album cover using Glide library
                 Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
         
//                         holder.overflow.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                                         //showPopupMenu(holder.overflow);
//                                         //Toast.makeText(this, "Thank you for trying this app, Find out more...",Toast.LENGTH_SHORT).show();
//                                             }
//         });
             }
 
             /**
       * Showing popup menu when tapping on 3 dots
       */
//             private void showPopupMenu(View view) {
//                 // inflate menu
//                         PopupMenu popup = new PopupMenu(mContext, view);
//                 MenuInflater inflater = popup.getMenuInflater();
//                 inflater.inflate(R.menu.menu_news, popup.getMenu());
//                 popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//                 popup.show();
//                 //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
//                     }
 
             /**
       * Click listener for popup menu items
       */
//             class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//                 public MyMenuItemClickListener() {
//                     }
//
//                 @Override
//         public boolean onMenuItemClick(MenuItem menuItem) {
//                         switch (menuItem.getItemId()) {
//                                 case R.id.action_add_favourite:
//                                         Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                                         return true;
//
//                                         default:
//                                 }
//                         return false;
//                     }
//     }
 
             @Override
     public int getItemCount() {
                 return newsList.size();
             }
        
}
