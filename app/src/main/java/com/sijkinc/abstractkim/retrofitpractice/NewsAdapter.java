package com.sijkinc.abstractkim.retrofitpractice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 
         
             public NewsAdapter(Context mContext, List<News> newsList) {
                 this.mContext = mContext;
                 this.newsList = newsList;
             }
 
             @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 View itemView = LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.news_card, parent, false);
         
                         return new MyViewHolder(itemView);
             }
 
             @Override
     public void onBindViewHolder(final MyViewHolder holder, int position) {
                 News n = newsList.get(position);
                 holder.title.setText(n.getName());
                 holder.source.setText(n.getSource());
         
                         // loading album cover using Glide library
                 Glide.with(mContext).load(n.getThumbnail()).into(holder.thumbnail);

                 holder.thumbnail.setOnTouchListener(new View.OnTouchListener(){
                     @Override
                     public boolean onTouch(View view, MotionEvent event) {
                         if(event.getAction() == MotionEvent.ACTION_DOWN) {
                             Toast.makeText(view.getContext(), n.getBody(), Toast.LENGTH_SHORT).show();
                             return true;
                         }
                         return false;
                     }
                 });

                 holder.title.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(n.getUrl()));
                         view.getContext().startActivity(browserIntent);
                     }
                });
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
