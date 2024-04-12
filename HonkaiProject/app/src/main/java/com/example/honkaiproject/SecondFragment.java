package com.example.honkaiproject;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.honkaiproject.databinding.FragmentSecondBinding;
import java.util.ArrayList;
import java.util.List;

import com.example.honkaiproject.AdaptadorListView;
import com.example.honkaiproject.ItemListView;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listPersonagens = binding.listPersonagens;
        ImageView imageView = binding.imageView;

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

        List<ItemListView> itens=new ArrayList<>();
        itens.add(0,new ItemListView(R.drawable.argenti_icon,"Argenti"));
        itens.add(1,new ItemListView(R.drawable.aventurine_icon,"Aventurine"));
        itens.add(2,new ItemListView(R.drawable.bailu_icon,"Bailu"));
        itens.add(3,new ItemListView(R.drawable.bronya_icon,"Bronya"));
        itens.add(4,new ItemListView(R.drawable.clara_icon,"Clara"));
        itens.add(5,new ItemListView(R.drawable.drratio_icon,"Dr. Ratio"));
        itens.add(6,new ItemListView(R.drawable.fuxuan_icon,"Fu Xuan"));
        itens.add(7,new ItemListView(R.drawable.himeko_icon,"Himeko"));
        itens.add(8,new ItemListView(R.drawable.huohuo_icon,"Huo Huo"));
        itens.add(9,new ItemListView(R.drawable.jingliu_icon,"Jingliu"));
        itens.add(10,new ItemListView(R.drawable.kafka_icon,"Kafka"));
        itens.add(11,new ItemListView(R.drawable.ruanmei_icon,"Ruan Mei"));
        itens.add(12,new ItemListView(R.drawable.silverwolf_icon,"Silverwolf"));
        itens.add(13,new ItemListView(R.drawable.topaznumby_icon,"Topaz & Numby"));

        listPersonagens.setAdapter(new AdaptadorListView(getContext(), itens));
        listPersonagens.setOnItemClickListener((parent, itemClicked, position, id) -> {
            imageView.setVisibility(View.VISIBLE);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.argenti);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.aventurine);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.bailu);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.bronya);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.clara);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.drratio);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.fuxuan);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.himeko);
                    break;
                case 8:
                    imageView.setImageResource(R.drawable.huohuo);
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.jingliu);
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.kafka);
                    break;
                case 11:
                    imageView.setImageResource(R.drawable.ruanmei);
                    break;
                case 12:
                    imageView.setImageResource(R.drawable.silverwolf);
                    break;
                case 13:
                    imageView.setImageResource(R.drawable.topaznumby);
                    break;
                default:
                    imageView.setVisibility(View.GONE);
                    break;
            }
        });
        SearchView searchView = view.findViewById(R.id.searchCharacter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ItemListView> filteredCharacter = new ArrayList<>();
                for(ItemListView itemListView: itens){
                    if(itemListView.getTexto().toLowerCase().contains(newText.toLowerCase())){
                       filteredCharacter.add(itemListView);
                    }
                }
                AdaptadorListView adapter = new AdaptadorListView(getContext(), filteredCharacter);
                listPersonagens.setAdapter(adapter);

                return false;
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}