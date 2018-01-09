package com.oxilo.ipif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oxilo.ipif.R;
import com.oxilo.ipif.modal.Cart;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.mirrajabi.rxcontacts.Contact;

/**
 * Created by Harshita on 11/19/2017.
 */

public class ContactsListAdapter<T>  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private Context mContext;
    public List<T> dataSet;
    private static CartListAdapter.MyClickListener myClickListener;
    private int inflated_row;

    public ContactsListAdapter(int inflated_row, List<T> productLists, Context mContext) {
        this.mContext = mContext;
        this.dataSet = productLists;
        this.inflated_row = inflated_row;
    }
    public void setOnItemClickListener(CartListAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    private void applyAndAnimateAdditions(List<T> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final T model = newModels.get(i);
            if (!dataSet.contains(model)) {
                addItem(i, model);
            }
        }
    }

    public void addItem(T item) {
        if (!dataSet.contains(item)) {
            dataSet.add(item);
            notifyItemInserted(dataSet.size() - 1);
        }
    }

    public void addItem(int position, T model) {
        dataSet.add(position, model);
        notifyItemInserted(position);
    }

    public void removeItem(T item) {
        int indexOfItem = dataSet.indexOf(item);
        if (indexOfItem != -1) {
            this.dataSet.remove(indexOfItem);
            notifyItemRemoved(indexOfItem);
        }
    }

    public T removeItem(int position) {
        final T model = dataSet.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void clearItem(){
        if (dataSet != null)
            dataSet.clear();
    }

    public void moveItem(int fromPosition, int toPosition) {
        final T model = dataSet.remove(fromPosition);
        dataSet.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public T getItem(int index) {
        if (dataSet != null && dataSet.get(index) != null) {
            return dataSet.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, dataSet is " + dataSet);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position) == null ? VIEW_PROG : VIEW_ITEM ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_ITEM){
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(inflated_row, parent, false);
            vh = new ContactsListAdapter.EventViewHolder(itemView);
        }
        else if(viewType == VIEW_PROG){
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);
            vh = new CartListAdapter.ProgressViewHolder(v);
        }else {
            throw new IllegalStateException("Invalid type, this type ot items " + viewType + " can't be handled");
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ContactsListAdapter.EventViewHolder){
            final T dataItem = dataSet.get(position);

            ((ContactsListAdapter.EventViewHolder) holder).username.setText(((Contact)dataItem).getDisplayName());
            if (!((Contact)dataItem).getPhoneNumbers().toString().equals("")){
                String string = ((Contact)dataItem).getPhoneNumbers().toString().replace("[","");
                string = string.replace("]","");
                ((ContactsListAdapter.EventViewHolder) holder).phone.setText(string);
            }

            else
                ((ContactsListAdapter.EventViewHolder) holder).phone.setText("");
            if (((Contact)dataItem).getThumbnail()!=null)
            ((ContactsListAdapter.EventViewHolder) holder).profile_img.setImageURI(((Contact)dataItem).getThumbnail());
            else
                ((ContactsListAdapter.EventViewHolder) holder).profile_img.setImageResource(R.drawable.app_icon);

        }else{
            ((CartListAdapter.ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        if (dataSet!=null)
            return dataSet.size();
        else
            return 0;
    }

    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView username,phone;
        public ImageView profile_img;

        public EventViewHolder(View v) {
            super(v);
            username = (TextView)v.findViewById(R.id.username);
            phone = (TextView)v.findViewById(R.id.phone);
            profile_img = (ImageView) v.findViewById(R.id.profile_img);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try{
                if(null != myClickListener){
                    myClickListener.onItemClick(getLayoutPosition(), view);
                }else{
//                    Toast.makeText(view.getContext(),"Click Event Null hai", Toast.LENGTH_SHORT).show();
                }
            }catch(NullPointerException e){
                e.printStackTrace();
//                Toast.makeText(view.getContext(),"Click Event Null Ex", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar)v.findViewById(R.id.progress_bar);
        }
    }


    /**
     * y Custom Item Listener
     */

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
