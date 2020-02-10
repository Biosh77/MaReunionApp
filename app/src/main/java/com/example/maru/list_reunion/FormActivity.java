package com.example.maru.list_reunion;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.R;
import com.example.maru.model.Reunion;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class FormActivity extends AppCompatActivity {


    private EditText Hour;
    private EditText Subject;
    private ChipGroup chipgroup;
    private Button validate;
    private EditText Date;
    private Spinner spinner;
    public static final String KEY_REUNION = "1";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);
        this.Subject = findViewById(R.id.subject);
        this.chipgroup = findViewById(R.id.chipgroup);
        this.validate = findViewById(R.id.Validate);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        setHour();
        setSpinner();
        setCalendar();
        setEmail();
        addReunion();

    }

    public void setHour() {

        this.Hour = findViewById(R.id.hour);

        Hour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Hour.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }


    public void setSpinner() {

        this.spinner = (Spinner) findViewById(R.id.place);

        String[] places = new String[]{
                "",
                "Salle A",
                "Salle B",
                "Salle C",
                "Salle D",
                "Salle E",
                "Salle G",
                "Salle H",
                "Salle I",
                "Salle J",
                "Salle K",
        };


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, places
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }


    public void setCalendar() {


        final Calendar myCalendar = Calendar.getInstance();
        this.Date = (EditText) findViewById(R.id.Date_Reunion);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            private void updateLabel() {
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

                Date.setText(sdf.format(myCalendar.getTime()));
            }

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        Date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void setEmail() {
        final EditText mailET = findViewById(R.id.mail);

        mailET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    if (isValidEmail(mailET.getText().toString())) {
                        addChip(mailET.getText().toString());
                        mailET.getText().clear();
                    } else
                        Toast.makeText(FormActivity.this, "L'email n'est pas valide", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void addChip(String email) {
        Chip chip = new Chip(this);
        chip.setText(email);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipgroup.removeView(v);
            }
        });
        chip.setCloseIconVisible(true);
        chipgroup.addView(chip);
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    public void addReunion() {
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationSuccess()) {
                    Intent intent = new Intent(FormActivity.this, ListReunionActivity.class);
                    intent.putExtra(KEY_REUNION, new Reunion(Hour.getText().toString(), spinner.getSelectedItem().toString(), Subject.getText().toString(), getListFromChipGroup(chipgroup), Date.getText().toString()));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Boolean validationSuccess() {
        if (Subject.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(FormActivity.this, "Veuillez rentrez le sujet", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Hour.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(FormActivity.this, "Veuillez rentrer l'heure ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Date.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(FormActivity.this, "Veuillez rentrer la date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Veuillez rentrer la salle ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (getListFromChipGroup(chipgroup).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Veuillez rentrer le mail ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private List<String> getListFromChipGroup(ChipGroup cg) {
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < cg.getChildCount(); i++) {
            Chip chip = (Chip) (cg.getChildAt(i));
            emails.add(chip.getText().toString());
        }
        return emails;
    }
}
