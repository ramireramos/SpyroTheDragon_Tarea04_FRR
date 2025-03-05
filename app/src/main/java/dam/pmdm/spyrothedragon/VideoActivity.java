package dam.pmdm.spyrothedragon;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dam.pmdm.spyrothedragon.R;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        surfaceView = findViewById(R.id.surfaceView);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer = new MediaPlayer();
        try {
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spyro_video);
            mediaPlayer.setDataSource(this, videoUri);
            mediaPlayer.setDisplay(holder);
            mediaPlayer.prepare();
            mediaPlayer.start();

            // Cuando termine el video, cerrar la actividad
            mediaPlayer.setOnCompletionListener(mp -> finish());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
