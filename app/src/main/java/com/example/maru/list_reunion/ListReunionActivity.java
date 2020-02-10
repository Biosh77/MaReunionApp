package com.example.maru.list_reunion;


import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.maru.R;
import com.example.maru.di.Injection;
import com.example.maru.event.DeleteReunionEvent;
import com.example.maru.model.Reunion;
import com.example.maru.repository.ReunionRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.maru.list_reunion.FormActivity.KEY_REUNION;


public class ListReunionActivity extends AppCompatActivity {


    protected ImageButton add_reunion;
    private ReunionAdapter adapter;
    private final static int REQUEST_DETAIL = 1;
    private RecyclerView recyclerView;
    private List<Reunion> mReunions;
    private ReunionRepository mRepo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_reunion);
        this.add_reunion = findViewById(R.id.add_reunion);

        mRepo = Injection.createReunionRepository();


        add_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListReunionActivity.this, FormActivity.class), REQUEST_DETAIL);
            }
        });


        configureRecyclerView();

    }

    @VisibleForTesting
    public void addReunion ( Reunion fakeReunion ){
        mRepo.addReunion(fakeReunion);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog, null);
            builder.setView(dialogView)

                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText Date = (EditText) dialogView.findViewById(R.id.date_filter);
                            Spinner spinner = (Spinner) dialogView.findViewById(R.id.place_filter);
                            mReunions = mRepo.filterReunion(spinner.getSelectedItem().toString(),Date.getText().toString());
                            recyclerView.setAdapter(new ReunionAdapter(ListReunionActivity.this,mReunions));
                        }
                    })
                    .setNegativeButton("Annuler", null);

            setSpinner(dialogView);
            setCalendar(dialogView);

            builder.create()
                    .show();
        }
        return true;
    }

    public void setCalendar(View dialogView) {
        final EditText Date = (EditText) dialogView.findViewById(R.id.date_filter);

        final Calendar myCalendar = Calendar.getInstance();

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
                new DatePickerDialog(ListReunionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    public void setSpinner(View dialogView) {

        Spinner spinner = (Spinner) dialogView.findViewById(R.id.place_filter);

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

    public void configureRecyclerView() {
        final ArrayList<Reunion> reunions = new ArrayList<>();

        recyclerView = findViewById(R.id.activity_list_reunion_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReunionAdapter(this, reunions);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    public void initList() {
        mReunions = mRepo.getReunions();
        recyclerView.setAdapter(new ReunionAdapter(this, mReunions));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mRepo.deleteReunion(event.reunion);
        initList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DETAIL && resultCode == RESULT_OK) {
            Reunion reunion = (Reunion) data.getSerializableExtra(KEY_REUNION);
            mRepo.addReunion(reunion);
            initList();
        }
    }
}

