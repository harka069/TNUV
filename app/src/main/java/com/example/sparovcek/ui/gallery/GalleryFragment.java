package com.example.sparovcek.ui.gallery;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sparovcek.R;
import com.example.sparovcek.databinding.FragmentGalleryBinding;

import java.util.Calendar;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieve the Spinner from the layout
        Spinner spinner = root.findViewById(R.id.spinnerCategory);

        // Create an ArrayAdapter and set it as the adapter for the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.array_odhodkov,
                R.layout.spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button submitButton = root.findViewById(R.id.buttonOdhodekSave);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
                Navigation.findNavController(v).navigate(R.id.nav_home);
            }
        });

        final TextView textView = root.findViewById(R.id.editTextDate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the selected date in the TextView
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textView.setText(date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showToast() {
        Toast.makeText(requireContext(), "Odhodek zabeležen!", Toast.LENGTH_SHORT).show();
    }

    private void showDatePicker() {
        datePickerDialog.show();
    }
}
