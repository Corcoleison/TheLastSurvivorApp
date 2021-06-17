package com.example.pablo.thelastsurvivor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.text.DateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DayNight extends AppCompatActivity {
    private TextView day_night_text;
    private TextView timeZone_text;
    private TextView sunrise_text;
    private TextView sunset_text;
    private EditText timezone_EditText;
    private Button validate;
    private TextView validate_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_night);
        Initialize();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
        day_night_text.setText(currentDateTimeString);

        TimeZone tz = TimeZone.getDefault();
        timeZone_text.setText(tz.getDisplayName());

        Location location = new Location("38.988266", "-1.861976");
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, "Europe/Madrid");
        String officialSunrise = calculator.getOfficialSunriseForDate(Calendar.getInstance());
        String officialSunset = calculator.getOfficialSunsetForDate(Calendar.getInstance());
        //Calendar officialSunset = calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());
        sunrise_text.setText(officialSunrise);
        sunset_text.setText(officialSunset);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timezone_EditText.getText().toString().matches("")){
                    Toast.makeText(DayNight.this, "Enter something", Toast.LENGTH_LONG).show();
                }else{
                    try{
                        ZoneId denverTimeZone = ZoneId.of( timezone_EditText.getText().toString() );
                        ZonedDateTime zdt = ZonedDateTime.now( denverTimeZone );
                        //Time time = new Time();
                        //time.switchTimezone(timezone_EditText.getText().toString());
                        String output2 = zdt.toLocalTime().toString();
                        validate_textView.setText( "Current time in " + denverTimeZone + ": " + output2);
                    }catch (ZoneRulesException e){
                        Toast.makeText(DayNight.this, "Wrong Time Zone (Try something like America/Denver)", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }

    public void Initialize(){
        day_night_text = (TextView)findViewById(R.id.set_time_date_TextView);
        timeZone_text = (TextView)findViewById(R.id.timeZone_TextView);
        sunrise_text = (TextView)findViewById(R.id.sunrise_TextView);
        sunset_text = (TextView)findViewById(R.id.sunset_TextView);
        validate_textView = (TextView)findViewById(R.id.validate_textView);
        timezone_EditText = (EditText)findViewById(R.id.timezone_EditText);
        validate = (Button)findViewById(R.id.button_timezone);
    }

    public void timeZoneChange(){
        ZoneId denverTimeZone = ZoneId.of( "America/Denver" );
        ZonedDateTime zdt = ZonedDateTime.now( denverTimeZone );
        String output2 = zdt.toLocalTime().toString();
        System.out.println( "Current time in " + denverTimeZone + ": " + output2 );
    }
}
