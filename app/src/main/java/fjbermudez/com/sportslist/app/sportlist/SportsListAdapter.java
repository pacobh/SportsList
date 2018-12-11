package fjbermudez.com.sportslist.app.sportlist;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.data.responses.Players;
import fjbermudez.com.sportslist.data.responses.SportListResponse;

public class SportsListAdapter extends RecyclerView.Adapter<SportsListAdapter.ViewHolder> {


    private List<Players> playersList;
    private Context context;

    public SportsListAdapter(Context context,List<Players> playersList) {
        this.playersList = playersList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Uri imageUri = Uri.parse(playersList.get(i).getUrlImage());
        Picasso.get().load(imageUri).placeholder(R.drawable.ic_image).tag(context).into(viewHolder.image);
        viewHolder.name.setText(playersList.get(i).getName());
        viewHolder.surname.setText(playersList.get(i).getSurname());

    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mContainer;
        ImageView image;
        TextView name;
        TextView surname;


        ViewHolder(View  v) {
            super(v);
            mContainer = v;
            image = (ImageView) mContainer.findViewById(R.id.ivImage);
            name = (TextView) mContainer.findViewById(R.id.tvName);
            surname = (TextView) mContainer.findViewById(R.id.tvSurname);
        }
    }
}
