package dam.pmdm.spyrothedragon.ui;

import android.animation.ObjectAnimator;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dam.pmdm.spyrothedragon.MainActivity;
import dam.pmdm.spyrothedragon.R;

public class GuideFragment6 extends Fragment {

    private SoundPool soundPool;
    private int soundId; // ID del sonido cargado

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide6, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btnFinish = view.findViewById(R.id.btn_finish);
        View speechBalloon = view.findViewById(R.id.speech_balloon_blank);

        // Cargar la animación de escala
        Animation scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);

        // Iniciar animaciones
        speechBalloon.startAnimation(scaleUp);
        ObjectAnimator fade = ObjectAnimator.ofFloat(btnFinish, "alpha", 0.2f, 1f);
        fade.setDuration(500); // Duración de la animación en milisegundos
        fade.setRepeatCount(ObjectAnimator.INFINITE); // Repetir indefinidamente
        fade.start();

        // Configurar SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1) // Número máximo de sonidos simultáneos
                .setAudioAttributes(audioAttributes)
                .build();

        // Cargar el sonido "fwosh.wav"
        soundId = soundPool.load(getContext(), R.raw.superflame, 1);
        btnFinish.setOnClickListener(v -> {
            // Reproducir sonido "fwosh.wav"
            soundPool.play(soundId, 1, 1, 0, 0, 1);
            // Cerrar la guía al pulsar botón "finalizar"
            GuideDialogFragment guideDialog = (GuideDialogFragment) requireActivity().getSupportFragmentManager().findFragmentByTag("GuideDialog");
            if (guideDialog != null) {
                guideDialog.closeGuide(); // Llama a closeGuide() para guardar la preferencia y cerrar el diálogo
            }
        });
    }

}
