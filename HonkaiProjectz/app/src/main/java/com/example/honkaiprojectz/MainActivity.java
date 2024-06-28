package com.example.honkaiprojectz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.honkaiprojectz.views.Inventory;
import com.example.honkaiprojectz.views.Login;
import com.example.honkaiprojectz.views.Music;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button btnLogout, btnMusic, btnInventory, btnCamera;
    TextView themeText;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchTheme;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STORAGE_PERMISSION = 2;
    private ActivityResultLauncher<Intent> requestImageCaptureLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btnLogout = findViewById(R.id.btnLogout);
        btnMusic = findViewById(R.id.btnMusic);
        btnInventory = findViewById(R.id.btnInventory);
        switchTheme = findViewById(R.id.switchTheme);
        themeText = findViewById(R.id.themeText);
        btnCamera = findViewById(R.id.btnCamera);

        switchTheme.setChecked(isNightMode);
        updateThemeText(isNightMode);

        requestImageCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Bundle extras = data.getExtras();
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            saveImageToGallery(imageBitmap);
                        }
                    }
                });

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Music.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                startActivity(intent);
                finish();
            }
        });

        btnCamera.setOnClickListener(v -> {
            // Verifica se a permissão CAMERA e WRITE_EXTERNAL_STORAGE já foram concedidas
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                // Caso as permissões não tenham sido concedidas, solicita ao usuário
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_STORAGE_PERMISSION);
            }
        });

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("NightMode", isChecked);
            editor.apply();

            updateThemeText(isChecked);

            // Define o modo noturno antes de recriar a atividade
            AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            recreate(); // Recria a atividade para aplicar a mudança de tema
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateThemeText(boolean isNightMode) {
        if (isNightMode) {
            themeText.setText("Modo noturno ativado");
        } else {
            themeText.setText("Modo claro ativado");
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            requestImageCaptureLauncher.launch(takePictureIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao abrir a câmera", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToGallery(Bitmap bitmap) {
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "Imagem_" + System.currentTimeMillis(),
                "Imagem capturada pelo aplicativo"
        );

        if (savedImageURL != null) {
            Toast.makeText(this, "Imagem salva na galeria!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Falha ao salvar imagem na galeria!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Permissão de armazenamento negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}