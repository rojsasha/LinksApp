package alex.example.com.linksapp.ui.news_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import alex.example.com.linksapp.R;
import alex.example.com.linksapp.data.entity.Event;

public class RecyclerViewAdapterNews extends RecyclerView.Adapter<RecyclerViewAdapterNews.ViewHolder> {
    private List<Event> mEventList;
    private AdapterInterfaceOnClick mCallback;

    public RecyclerViewAdapterNews(List<Event> eventList, AdapterInterfaceOnClick Callback) {
        mEventList = eventList;
        mCallback = Callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = layoutInflater.inflate(R.layout.item_list, null, false);
        view.setLayoutParams(lp);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(mEventList.get(i).getTitle());
        viewHolder.preview.setText(mEventList.get(i).getPreview());
        viewHolder.place.setText(mEventList.get(i).getPlace());
        viewHolder.time.setText(mEventList.get(i).getTime());
        viewHolder.coefficient.setText(mEventList.get(i).getCoefficient());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onCLickItem(mEventList.get(i).getArticle(), mEventList.get(i).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView preview;
        TextView place;
        TextView time;
        TextView coefficient;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            preview = itemView.findViewById(R.id.preview);
            place = itemView.findViewById(R.id.place);
            time = itemView.findViewById(R.id.time);
            coefficient = itemView.findViewById(R.id.coefficient);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
    public interface AdapterInterfaceOnClick {
        void onCLickItem(String article, String title);
    }
}
