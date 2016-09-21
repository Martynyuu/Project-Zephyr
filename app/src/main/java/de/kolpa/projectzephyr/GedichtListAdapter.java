package de.kolpa.projectzephyr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 21.09.2016.
 */

class GedichtListAdapter extends BaseAdapter {
    private Context _context;
    private ArrayList<String> _zeilen;
    private ArrayList<String> _displayZeilen;

    private static class GedichtViewHolder {
        TextView textView;
    }

    GedichtListAdapter(Context context) {
        _context = context;
        _zeilen = new ArrayList<>();
        _displayZeilen = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return _zeilen.size();
    }

    @Override
    public Object getItem(int position) {
        return _zeilen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mainView = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            mainView = inflater.inflate(R.layout.gedicht_line_layout, parent, false);

            GedichtViewHolder holder = new GedichtViewHolder();
            holder.textView = (TextView) mainView.findViewById(R.id.gedicht_line_text);

            mainView.setTag(holder);
        }

        GedichtViewHolder holder = (GedichtViewHolder) mainView.getTag();
        holder.textView.setText(_displayZeilen.get(position));

        return mainView;
    }

    private void makeHiddenLine(String line) {
        _zeilen.add(line);
        _displayZeilen.add(line);

        for (int i = 0; i < _displayZeilen.size() - 1; i++) {
            String hidden = "";

            for (char ch : _displayZeilen.get(i).toCharArray()) {
                hidden += "*";
            }

            _displayZeilen.set(i, hidden);
        }
    }

    void addZeile(String zeile) {
        makeHiddenLine(zeile);
        notifyDataSetChanged();
    }

    void revealText() {
        _displayZeilen.clear();
        _displayZeilen.addAll(_zeilen);
        notifyDataSetChanged();
    }

    void reset() {
        _displayZeilen.clear();
        _zeilen.clear();
        notifyDataSetChanged();
    }
}
