package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.model.CreatureOverview;

/**
 * Created by presisco on 2017/5/16.
 */

public class CreatureListAdapter extends DragAppendAdapter<CreatureOverview> implements DragAppendAdapter.ActionListener {
    private CreatureListener mCreatureListener;

    public CreatureListAdapter(Context context, FooterListener listener, CreatureListener creatureListener) {
        super(context);
        setActionListener(this);
        setFooterListener(listener);
        mCreatureListener = creatureListener;
    }

    @Override
    public ContentHolder onCreate(LayoutInflater inflater, ViewGroup parent) {
        return new CreatureHolder(
                inflater.inflate(
                        R.layout.listitem_creature_overview, parent, false)
                , mCreatureListener);
    }

    @Override
    public void onBind(ContentHolder holder, int position) {
        CreatureHolder creatureHolder = (CreatureHolder) holder;
        CreatureOverview overview = getItem(position);
        creatureHolder.nameText.setText(overview.getName());
        creatureHolder.imageView.setImageUrl(overview.getImage_url(), getImageLoader());
    }

    @Override
    public void onRecycled(ContentHolder holder) {

    }

    public interface CreatureListener {
        void onClickCreature(int position);
    }

    protected static class CreatureHolder extends ContentHolder {
        public final TextView nameText;
        public final NetworkImageView imageView;

        public CreatureHolder(View itemView, final CreatureListener listener) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.textCreatureName);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageCreature);
            imageView.setDefaultImageResId(R.drawable.ic_network_loading);
            imageView.setErrorImageResId(R.drawable.ic_warning);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickCreature(getAdapterPosition());
                }
            });
        }
    }
}
