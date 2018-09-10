package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by 17259224 on 13/08/2018.
 */



public class InicioActivity extends Activity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // iniciar a musica de tempo
        mediaPlayer = MediaPlayer.create(this, R.raw.abertura);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }

    public void iniciarJogo(View v){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        mediaPlayer.stop();

        finish();
    }
}
