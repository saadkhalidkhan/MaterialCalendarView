package com.saad.materialcalenderview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

    }

    private void setUpView() {

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        //calendar instance
        Calendar calendar = Calendar.getInstance();

        // Add a decorator to disable prime numbered days
        calendarView.addDecorator(new PrimeDayDisableDecorator());

        calendarView.setSelectedDate(calendar);

        calendarView.newState();

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Toast.makeText(MainActivity.this, "" + (date.getMonth() + 1), Toast.LENGTH_SHORT).show();
            }
        });

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(MainActivity.this, "" + date.getDay(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class PrimeDayDisableDecorator implements DayViewDecorator {

        @Override
        public boolean shouldDecorate(final CalendarDay day) {
            if (day.getDay() == 2 || day.getDay() == 11 || day.getDay() == 16) {
                PRIME_TABLE[day.getDay()] = true;
                return true;
            }
            return PRIME_TABLE[day.getDay()];
        }

        @Override
        public void decorate(final DayViewFacade view) {
            view.setDaysDisabled(true);
        }

        private static boolean[] PRIME_TABLE = {
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
        };
    }

}
