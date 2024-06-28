package com.example.honkaiprojectz.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.honkaiprojectz.R;
import com.example.honkaiprojectz.adapters.GridAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Weapon extends AppCompatActivity {
    GridView gvWeapon;
    Button btnSave, btnRelic, btnCharacter;
    TextView txtWeaponName;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weapon);

        gvWeapon = findViewById(R.id.gvWeapon);
        btnSave = findViewById(R.id.btnSave);
        txtWeaponName = findViewById(R.id.txtWeaponName);
        btnRelic = findViewById(R.id.btnRelic);
        btnCharacter = findViewById(R.id.btnCharacter);

        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        int[] list = new int[]{
                R.drawable.argenti_lc, R.drawable.aventurine_lc, R.drawable.bailu_lc,
                R.drawable.bronya_lc, R.drawable.clara_lc, R.drawable.drratio_lc,
                R.drawable.fuxuan_lc, R.drawable.himeko_lc, R.drawable.huohuo_lc,
                R.drawable.jingliu_lc, R.drawable.kafka_lc, R.drawable.ruanmei_lc
        };

        String[] description = new String[]{
                "A Erudição", "A Preservação", "A Abundância", "A Harmonia", "A Destruição", "A Caça",
                "A Preservação", "A Erudição", "A Abundância", "A Destruição",
                "A Inexistência", "A Harmonia"
        };

        String[] name = new String[]{
                "Um Instante Eternamente Adorado", "Destino Inerentemente Injusto",
                "O Tempo Não Espera por Ninguém", "Mas a Batalha Não Acabou",
                "Algo Insubstituível", "O Batismo de Pensamento Puro",
                "Ela Já Fechou Seus Olhos", "Noite na Via Láctea",
                "Noite Aterrorizante", "Eu Serei Minha Própria Espada",
                "Você Só Precisa de Paciência", "Meu Passado no Espelho"
        };

        gvWeapon.setAdapter(new GridAdapter(getApplicationContext(), list, name));

        Map<String, Object> Weapon = new HashMap<>();
        Weapon.put("uid", uid);

        gvWeapon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weapon.put("name", name[position]);
                txtWeaponName.setText("Relíquia escolhido: " + name[position]);
                Weapon.put("description", description[position]);
                Weapon.put("imageResource", list[position]);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("inventory")
                        .add(Weapon)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Arma salvo com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Falha ao escolher a arma", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });

        btnCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Character.class);
                startActivity(intent);
                finish();
            }
        });

        btnRelic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Relic.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}