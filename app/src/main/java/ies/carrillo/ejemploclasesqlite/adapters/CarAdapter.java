package ies.carrillo.ejemploclasesqlite.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.R;
import ies.carrillo.ejemploclasesqlite.models.Car;
import ies.carrillo.ejemploclasesqlite.models.Person;

public class CarAdapter extends ArrayAdapter<Car> {

    private List<Car> cars;

    public CarAdapter(@NonNull Context context, int resource, @NonNull List<Car> cars) {
        super(context, resource, cars);
        this.cars = cars;
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Car p = cars.get(position);
        TextView tvCar;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item, parent, false);
        }

        return convertView;
    }
}
