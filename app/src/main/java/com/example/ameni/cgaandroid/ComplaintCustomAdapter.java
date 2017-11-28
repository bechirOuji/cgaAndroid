package com.example.ameni.cgaandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ComplaintCustomAdapter extends ArrayAdapter<Complaint> {

	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public ComplaintCustomAdapter(Context context, int resourceId, List<Complaint> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView IdText, ResText;
		    public ImageView image;
		  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View rowView = convertView;
	  
		  //Réutiliser les Views
		  if (rowView == null) {
			rowView = inflater.inflate(resourceId, parent, false);
		  }
		  
		  //Configuration du ViewHolder
		  ViewHolder viewHolder = new ViewHolder();
		  viewHolder.IdText = (TextView) rowView.findViewById(R.id.IDItem);
		  viewHolder.ResText = (TextView) rowView.findViewById(R.id.expirationItem);
		  rowView.setTag(viewHolder);
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  Complaint comlaint = getItem(position);
		  
		  holder.IdText.setText( String.valueOf( comlaint.getComplaintId()));
		  holder.ResText.setText( comlaint.getResponse());
		  notifyDataSetChanged();
		  return rowView;
	  }

	}



