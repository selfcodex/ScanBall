package com.example.scanball.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.scanball.R;
import java.util.List;
//Создание таблицы
public class Custom_ArrayAdapter extends ArrayAdapter  {

    private LayoutInflater inflater;  //рисует layout на экране
    private List<ListItemClass> listItem;
    private Context context;
   // private int layout;


    public Custom_ArrayAdapter(@NonNull Context context, int resource, List<ListItemClass> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;
        this.context = context;
    }
/*при прокрутке в ListView, если в списке очень много объектов, то для каждого элемента, когда он попадет в зону видимости,
 будет повторно вызываться метод getView, в котором будет заново создаваться новый объект View.
 Соответственно будет увеличиваться потреление памяти и снижаться производительность. Поэтому оптимизируем код метода getView:
 Параметр convertView указывает на элемент View, который используется для объекта в списке по позиции position.
 Если ранее уже создавался View для этого объекта, то параметр convertView уже содержит некоторое значение,
 которое мы можем использовать.
 */
    @SuppressLint({"ViewHolder", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemClass listItemMain = listItem.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item, null, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.place = convertView.findViewById(R.id.textView_place);
            viewHolder.club = convertView.findViewById(R.id.textView_club);
            viewHolder.games = convertView.findViewById(R.id.textView_games);
            viewHolder.scores = convertView.findViewById(R.id.textView_scores);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.place.setText(listItemMain.getNumber_place());
        viewHolder.club.setText(listItemMain.getName_club());
        viewHolder.games.setText(listItemMain.getKolvo_games());
        viewHolder.scores.setText(listItemMain.getKolvo_scores());
        return convertView;
    }
    //ViewHolder сохраняет ссылки на необходимые в элементе списка шаблоны.
    private class ViewHolder{
        TextView place;
        TextView club;
        TextView games;
        TextView scores;
    }
}
