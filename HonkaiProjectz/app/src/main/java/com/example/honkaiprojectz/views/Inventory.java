package com.example.honkaiprojectz.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.honkaiprojectz.MainActivity;
import com.example.honkaiprojectz.R;
import com.example.honkaiprojectz.adapters.GridAdapter;
import com.example.honkaiprojectz.adapters.ListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Inventory extends AppCompatActivity {
    ImageView ivBack;
    Button btnAdd, btnScreenshot;
    ListView lstInventory;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);

        ivBack = findViewById(R.id.ivBack);
        btnAdd = findViewById(R.id.btnAdd);
        lstInventory = findViewById(R.id.lstInventory);
        btnScreenshot = findViewById(R.id.button_screenshot);

        String userId = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnScreenshot.setOnClickListener(v -> {
            // Captura a tela atual do inventário
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.ARGB_8888);
            rootView.draw(new Canvas(bitmap));

            // Salva a imagem na galeria
            String savedImageURL = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    bitmap,
                    "Inventário_" + System.currentTimeMillis(),
                    "Captura de tela do inventário"
            );

            if (savedImageURL != null) {
                Toast.makeText(this, "Captura de tela salva!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao salvar captura de tela!", Toast.LENGTH_SHORT).show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Character.class);
                startActivity(intent);
            }
        });

        int[] imageResource = new int[50];
        String[] name = new String[50];
        String[] description = new String[50];

        db.collection("inventory")
                .whereEqualTo("uid", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                int count = 0;
                                // Document found
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    name[count] = document.getString("name");
                                    description[count] = document.getString("description");
                                    imageResource[count++] = document.getLong("imageResource").intValue();
                                }
                            } else {

                            }
                        } else {

                        }
                    }
                });

        lstInventory.setAdapter(new ListAdapter(getApplicationContext(), imageResource, name, description));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}