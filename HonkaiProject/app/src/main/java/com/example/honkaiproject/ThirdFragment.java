package com.example.honkaiproject;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.honkaiproject.databinding.FragmentSecondBinding;
import com.example.honkaiproject.databinding.FragmentThirdBinding;

import java.lang.reflect.Array;


public class ThirdFragment extends Fragment {
    private FragmentThirdBinding binding;
    private MediaPlayer mediaPlayer;
    private ImageView imageView;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnThirdBackFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_FirstFragment)
        );
        imageView = view.findViewById(R.id.ivMusic);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.theme, android.R.layout.simple_spinner_item);
        binding.spinnerTheme.setAdapter(adapter);
        binding.spinnerTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mediaPlayer!=null){
                    mediaPlayer.release();
                }
                switch (position){
                    case 0:
                        imageView.setImageResource(R.drawable.musicnote);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.jarilo);
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.embers);
                        mediaPlayer.start();
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.xianzhou);
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.blade);
                        mediaPlayer.start();
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.penacony);
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.goldenhour);
                        mediaPlayer.start();
                        break;
                }
                if(mediaPlayer!=null){
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.release();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}