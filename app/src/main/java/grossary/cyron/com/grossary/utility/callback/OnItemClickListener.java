package grossary.cyron.com.grossary.utility.callback;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(T t, View view, int position);

}
