package com.wildlifeoftianjin.ui.framework.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by presisco on 2017/5/18.
 */

public class SelectPictureAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_PICTURE = 10;
    private static final int VIEW_TYPE_ADD_BUTTON = 20;

    private List<Uri> mPictures = new ArrayList<>();
    private List<Bitmap> mThumbnails = new ArrayList<>();
    private LayoutInflater mInflater = null;
    private Context mContext;
    private AddListener mAddListener;
    private DeleteListener mDeleteListener = new DeleteListener() {
        @Override
        public void onDeletePicture(int position) {
            mPictures.remove(position);
            mThumbnails.remove(position);
            notifyDataSetChanged();
        }
    };

    public SelectPictureAdapter(Context context, AddListener listener) {
        mAddListener = listener;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PictureHolder) {
            PictureHolder pictureHolder = (PictureHolder) holder;
            pictureHolder.imageView.setImageBitmap(mThumbnails.get(position));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_TYPE_ADD_BUTTON)
            viewHolder = new AddButtonHolder(
                    mInflater.inflate(R.layout.listitem_selected_picture, parent, false)
                    , mAddListener);
        else
            viewHolder = new PictureHolder(
                    mInflater.inflate(R.layout.listitem_selected_picture, parent, false)
                    , mDeleteListener);
        return viewHolder;
    }

    public void addPicture(Uri uri) {
        Bitmap thumbnail = ImageUtils.getThumbnail(mContext, uri, 512, 512);
        if (thumbnail != null) {
            mPictures.add(uri);
            mThumbnails.add(thumbnail);
            notifyItemInserted(mPictures.size() - 1);
        }
    }

    public List<Uri> getPictures() {
        return mPictures;
    }

    @Override
    public int getItemCount() {
        return mPictures.size() + 1;
    }

    @Override
    public int getItemViewType(final int position) {
        int content_count = mPictures.size();
        if (position == content_count) {
            return VIEW_TYPE_ADD_BUTTON;
        } else {
            return VIEW_TYPE_PICTURE;
        }
    }

    protected interface DeleteListener {
        void onDeletePicture(int position);
    }

    public interface AddListener {
        void onAddPicture();
    }

    protected static class PictureHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;

        public PictureHolder(View itemView, final DeleteListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageMain);
            itemView.findViewById(R.id.imageDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeletePicture(getAdapterPosition());
                }
            });
        }
    }

    protected static class AddButtonHolder extends RecyclerView.ViewHolder {
        public AddButtonHolder(View itemView, final AddListener listener) {
            super(itemView);
            itemView.findViewById(R.id.imageDelete).setVisibility(GONE);
            ImageView buttonImage = (ImageView) itemView.findViewById(R.id.imageMain);
            buttonImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddPicture();
                }
            });
            buttonImage.setImageResource(R.drawable.ic_add_picture);
        }
    }
}
