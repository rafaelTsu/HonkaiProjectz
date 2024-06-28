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
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Character extends AppCompatActivity {
    GridView gvCharacter;
    Button btnSave, btnRelic, btnWeapon;
    TextView txtCharacterName;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character);

        gvCharacter = findViewById(R.id.gvCharacter);
        btnSave = findViewById(R.id.btnSave);
        txtCharacterName = findViewById(R.id.txtCharacterName);
        btnRelic = findViewById(R.id.btnRelic);
        btnWeapon = findViewById(R.id.btnWeapon);

        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        int[] list = new int[]{
                R.drawable.argenti, R.drawable.aventurine, R.drawable.bailu,
                R.drawable.bronya, R.drawable.clara, R.drawable.drratio,
                R.drawable.fuxuan, R.drawable.himeko, R.drawable.huohuo,
                R.drawable.jingliu, R.drawable.kafka, R.drawable.ruanmei,
                R.drawable.silverwolf, R.drawable.topaznumby
        };

        String[] description = new String[]{
                "A Erudição", "A Preservação", "A Abundância", "A Harmonia", "A Destruição", "A Caça", "A Preservaçâo",
                "A Erudição", "A Abundância", "A Destruição", "A Inexistência", "A Harmonia",
                "A Inexistência", "A Caça"
        };

        String[] name = new String[]{
                "Argenti", "Aventurine", "Bailu", "Bronya", "Clara", "Dr Ratio",
                "Fuxuan", "Himeko", "Huohuo", "Jingliu", "Kafka", "Ruan Mei", "Silver wolf",
                "Topaz & Numby"
        };

        gvCharacter.setAdapter(new GridAdapter(getApplicationContext(), list, name));

        Map<String, Object> character = new HashMap<>();
        character.put("uid", uid);

        gvCharacter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                character.put("name", name[position]);
                txtCharacterName.setText("Personagem escolhido: " + name[position]);
                character.put("description", description[position]);
                character.put("imageResource", list[position]);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("inventory")
                        .add(character)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Personagem salvo com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Falha ao escolher o personagem", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
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

        btnWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Weapon.class);
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