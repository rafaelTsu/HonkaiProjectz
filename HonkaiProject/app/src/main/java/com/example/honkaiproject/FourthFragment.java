package com.example.honkaiproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.honkaiproject.databinding.FragmentFourthBinding;
import com.example.honkaiproject.databinding.FragmentSecondBinding;

public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnFourthBackFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_fourthFragment_to_FirstFragment)
        );
        int[] list = new int[]{
                R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
                R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_8
        };
        binding.gridRelic.setAdapter(new Adaptador(getContext(), list));
        binding.gridRelic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = "";
                String twoPeace = "";
                String fourPeace = "";
                switch (position){
                    case 0:
                        nome = "Banda Trovão Crepitante";
                        twoPeace = "Aumenta o Dano de Raio em 10%.";
                        fourPeace = "Quando o usuário usa a Perícia, aumenta o ATQ em 20% por 1 rodada(s).";
                        break;
                    case 1:
                        nome = "Campeã de Boxe de Rua";
                        twoPeace = "Aumenta o Dano Físico em 10%.";
                        fourPeace = "Depois que o usuário ataca ou é atingido, aumenta em 5% seu ATQ durante todo o resto da batalha. Este efeito pode acumular até 5 vez(es).";
                        break;
                    case 2:
                        nome = "Águia da Linha do Crepúsculo";
                        twoPeace = "Aumenta o Dano de Vento em 10%.";
                        fourPeace = "Após o usuário usar a Perícia Suprema, sua ação é Avançada Adiante em 25%.";
                        break;
                    case 3:
                        nome = "Artesão de Fogo da Forja de Lava";
                        twoPeace = "Aumenta o Dano de Fogo em 10%.";
                        fourPeace = "Aumenta o Dano da Perícia do usuário em 12%. Após usar a Perícia Suprema, aumenta o Dano de Fogo do próximo ataque em 12%.";
                        break;
                    case 4:
                        nome = "Gênio das Estrelas Brilhantes";
                        twoPeace = "Aumenta o Dano Quântico em 10%.";
                        fourPeace = "Quando o usuário causa Dano ao inimigo-alvo, ignora 10% de DEF. Se o inimigo-alvo tiver Fraqueza contra Quântico, o usuário ignora adicionalmente 10% de DEF.";
                        break;
                    case 5:
                        nome = "Guarda da Neve Uivante";
                        twoPeace = "Reduz o Dano recebido em 8%.";
                        fourPeace = "No início da rodada, se os PV do usuário forem iguais ou inferiores a 50%, restaura PV igual a 8% de seus PV máximos e regenera 5 de Energia.";
                        break;
                    case 6:
                        nome = "Caçador da Floresta Glacial";
                        twoPeace = "Aumenta o Dano de Gelo em 10%.";
                        fourPeace = "Após o usuário utilizar sua Perícia Suprema, aumenta em 25% o Dano CRIT por 2 rodada(s).";
                        break;
                    case 7:
                        nome = "Cavaleira do Palácio da Pureza";
                        twoPeace = "Aumenta a DEF em 15%.";
                        fourPeace = "Aumenta em 20% o Dano máximo que pode ser absorvido pelo Escudo criado pelo usuário.";
                        break;
                    case 8:
                        nome = "Discípula Longeva";
                        twoPeace = "Aumenta os PV máximos em 12%.";
                        fourPeace = "Quando o usuário recebe um golpe ou tem seus PV consumidos por um aliado ou por si próprio, a Chance de CRIT dele aumenta em 8% por 2 rodada(s) e até 2 acúmulos.";
                        break;
                }
                binding.txtNameResult.setText(nome);
                binding.txtTwoPeaceResult.setText(twoPeace);
                binding.txtFourPeaceResult.setText(fourPeace);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}