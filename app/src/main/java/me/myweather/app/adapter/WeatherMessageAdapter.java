package me.myweather.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.myweather.app.R;
import me.myweather.app.been.WeatherMessage;
import me.myweather.app.factory.WeatherIconFactory;
import me.myweather.app.tool.NumberWeekTool;

/**
 * Created by admin on 2017/8/11.
 */

public class WeatherMessageAdapter extends BaseAdapter {

    WeatherMessage weatherMessage;
    List<WeatherMessage.ForecastsBean.CastsBean> weathers;
    private LayoutInflater layoutInflater;

    public WeatherMessageAdapter(Context context, WeatherMessage weatherMessage) {
        this.weatherMessage = weatherMessage;
        this.weathers = weatherMessage.getForecasts().get(0).getCasts();
        this.weathers.remove(0);
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        WeatherMessage.ForecastsBean.CastsBean weather = weathers.get(position);
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_day_info, null);
            viewHolder.tvWeek = (TextView) convertView.findViewById(R.id.week);
            viewHolder.tvDayTemperature = (TextView) convertView.findViewById(R.id.day_temperature);
            viewHolder.tvNightTemperature = (TextView) convertView.findViewById(R.id.night_temperature);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvWeek.setText(NumberWeekTool.getWeekByNumber(weather.getWeek()));
        viewHolder.tvDayTemperature.setText(weather.getDaytemp());
        viewHolder.tvNightTemperature.setText(weather.getNighttemp());
        viewHolder.ivIcon.setBackgroundResource(WeatherIconFactory.getDayResource(weather.getDayweather()));
        return convertView;
    }

    final class ViewHolder {
        public TextView tvWeek;
        public ImageView ivIcon;
        public TextView tvDayTemperature;
        public TextView tvNightTemperature;
    }
}
