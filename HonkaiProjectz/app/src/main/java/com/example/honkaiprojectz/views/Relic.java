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

public class Relic extends AppCompatActivity {
    GridView gvRelic;
    Button btnSave, btnWeapon, btnCharacter;
    TextView txtRelicName;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relic);

        gvRelic = findViewById(R.id.gvRelic);
        btnSave = findViewById(R.id.btnSave);
        txtRelicName = findViewById(R.id.txtRelicName);
        btnWeapon = findViewById(R.id.btnWeapon);
        btnCharacter = findViewById(R.id.btnCharacter);

        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        int[] list = new int[]{
                R.drawable.reliquia_ataque, R.drawable.reliquia_cura, R.drawable.reliquia_defesa,
                R.drawable.reliquia_fisico, R.drawable.reliquia_fogo, R.drawable.reliquia_gelo,
                R.drawable.reliquia_imaginario, R.drawable.reliquia_quantum, R.drawable.reliquia_quebra,
                R.drawable.reliquia_raio, R.drawable.reliquia_vento, R.drawable.reliquia_vida
        };

        String[] description = new String[]{
                "Ataque", "Cura", "Defesa", "Dano Físico", "Dano de Fogo", "Dano de Gelo",
                "Dano Imaginário", "Dano Quantum", "Efeito de Quebra", "Dano de Raio",
                "Dano de Vento", "Vida"
        };

        String[] name = new String[]{
                "Mosqueteiro do Trigo Selvagem", "Mosqueteiro do Trigo Selvagem",
                "Cavaleira do Palácio da Pureza", "Campeão de Boxe de Rua",
                "Artesão de Fogo da Forja de Lava", "Caçador da Floresta Glacial",
                "Andarilho do Deserto da Bandidagem", "Gênio das Estrelas Brilhantes",
                "Cavalaria de Ferro contra a Praga", "Banda Trovão Crepitante",
                "Águi da Linha do Crepúsculo", "Discípula Longeva"
        };

        gvRelic.setAdapter(new GridAdapter(getApplicationContext(), list, name));

        Map<String, Object> Relic = new HashMap<>();
        Relic.put("uid", uid);

        gvRelic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Relic.put("name", name[position]);
                txtRelicName.setText("Relíquia escolhido: " + name[position]);
                Relic.put("description", description[position]);
                Relic.put("imageResource", list[position]);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("inventory")
                        .add(Relic)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Relíquia salvo com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Falha ao escolher a relíquia", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });

        btnWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Weapon.class);
                startActivity(intent);
                finish();
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}