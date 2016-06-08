package com.example.moran_lap.projbitmapv11;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gili on 25/04/2016.
 */
public class SurfaceComponentAdapter extends RecyclerView.Adapter<SurfaceComponentAdapter.SurfaceComponentViewHolder> implements ItemTouchHelperAdapter {

    protected List<SurfaceComponent> surfaceComponents;
    private MainActivity mainActivity;
    private Handler handler;
    private static final int NOTIFY_DATA_SET_CHANGED = 2;

    public SurfaceComponentAdapter(List<SurfaceComponent> surfaceComponents, MainActivity mainActivity, Handler handler){
        this.surfaceComponents = surfaceComponents;
        this.mainActivity = mainActivity;
        this.handler = handler;
    }

    @Override
    public SurfaceComponentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.single_listview_item, parent, false);

        return new SurfaceComponentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SurfaceComponentViewHolder holder, final int position)
    {
        SurfaceComponent sc = surfaceComponents.get(position);
        holder.SourceName.setText(sc.getSurfaceComponentName());
        holder.checkbox.setTag(surfaceComponents.get(position));
        holder.checkbox.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox)v;
                SurfaceComponent sp = (SurfaceComponent)checkbox.getTag();
                sp.setIsEnabled(checkbox.isChecked());
                Toast.makeText(ApplicationContext.getActivity(), "Clicked on Source " + sp.getImageSource().getSourceName() + " State is: " + sp.isEnabled(), Toast.LENGTH_SHORT).show();
                mainActivity.refreshSurfaceComponentsOnBitmap();
            }
        });
        holder.optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(mainActivity, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.list_item_popup_view, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch(item.getItemId())
                        {
                            case (R.id.set_position) :
                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplicationContext.getActivity());
                                builder.setTitle("Insert position here");

                                // Get the layout inflater
                                LayoutInflater inflater = ApplicationContext.getActivity().getLayoutInflater();

                                final View setPositionView = inflater.inflate(R.layout.edit_position_layout, null);

                                // Inflate and set the layout for the dialog
                                // Pass null as the parent view because its going in the dialog layout
                                builder.setView(setPositionView)
                                // Set up the buttons
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        TextView xStartView = (TextView)setPositionView.findViewById(R.id.x_start);
                                        TextView xEndView = (TextView)setPositionView.findViewById(R.id.x_end);
                                        TextView yStartView = (TextView)setPositionView.findViewById(R.id.y_start);
                                        TextView yEndView = (TextView)setPositionView.findViewById(R.id.y_end);

                                        int xStart = Integer.parseInt(xStartView.getText().toString());
                                        int xEnd = Integer.parseInt(xEndView.getText().toString());
                                        int yStart = Integer.parseInt(yStartView.getText().toString());
                                        int yEnd = Integer.parseInt(yEndView.getText().toString());

                                        SurfaceComponent sComponent = surfaceComponents.get(position);
                                        Position newPosition = new Position(xStart,xEnd,yStart,yEnd);
                                        sComponent.setImagePositionOnSurface(newPosition);
                                        ((MainActivity)ApplicationContext.getActivity()).refreshSurfaceComponentsOnBitmap();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.show();
                                break;
                            case (R.id.configure_surface_component) :
                                SurfaceComponent sComponent = surfaceComponents.get(position);
                                sComponent.getImageSource().EditSource();
                                break;
                            case (R.id.delete_surface_component) :
                                surfaceComponents.remove(surfaceComponents.get(position));
                                swap((ArrayList<SurfaceComponent>) surfaceComponents);
                                break;
                        }
                        ((MainActivity)ApplicationContext.getActivity()).refreshSurfaceComponentsOnBitmap();
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });
    }

    @Override
    public int getItemCount() {
        return surfaceComponents.size();
    }

    public void swap(ArrayList<SurfaceComponent> datas){
        surfaceComponents.clear();
        surfaceComponents.addAll(datas);
        //notifyDataSetChanged();
        Message msg = handler.obtainMessage();
        msg.what = NOTIFY_DATA_SET_CHANGED;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemDismiss(int position) {
        synchronized (mainActivity.locker) {
            surfaceComponents.remove(position);
            notifyItemRemoved(position);
            swap((ArrayList<SurfaceComponent>) surfaceComponents);
            mainActivity.refreshSurfaceComponentsOnBitmap();
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        synchronized (mainActivity.locker) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(surfaceComponents, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(surfaceComponents, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            mainActivity.refreshSurfaceComponentsOnBitmap();
        }
        return true;
    }

    public static class SurfaceComponentViewHolder extends RecyclerView.ViewHolder{
        protected TextView SourceName;
        protected ImageButton optionsButton;
        protected CheckBox checkbox;

        public SurfaceComponentViewHolder(View itemView) {
            super(itemView);
            SourceName = (TextView) itemView.findViewById(R.id.sourcename);
            optionsButton = (ImageButton) itemView.findViewById(R.id.options_button);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}


