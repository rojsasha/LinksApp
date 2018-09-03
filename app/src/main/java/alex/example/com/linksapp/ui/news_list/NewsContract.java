package alex.example.com.linksapp.ui.news_list;

import java.util.List;

import alex.example.com.linksapp.data.entity.Event;
import alex.example.com.linksapp.ui.Lifecicle;

public interface NewsContract {
    interface View {
        void onSuccess(List<Event> list);
        void onError(String msg);

    }

    interface Presenter extends Lifecicle<View> {
        void getCurrentNews(String category);
    }
}
