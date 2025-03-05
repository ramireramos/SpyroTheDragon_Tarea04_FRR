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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dam.pmdm.spyrothedragon.MainActivity;
import dam.pmdm.spyrothedragon.R;

public class GuideFragment2 extends Fragment {

    private SoundPool soundPool;
    private int soundId; // ID del sonido cargado

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // referencias a los elementos de la vista
        ImageView speechBalloon = view.findViewById(R.id.speech_balloon);
        TextView BotonPersonajes = view.findViewById(R.id.guide_title);
        TextView descripcion = view.findViewById(R.id.guide_description);
        ImageView fire = view.findViewById(R.id.fire);
        Button btnNext = view.findViewById(R.id.btn_next);
        Button btnSkip = view.findViewById(R.id.btn_skip);

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
        soundId = soundPool.load(getContext(), R.raw.fwosh, 1);

        // Cargar la animación de escala y fade
        Animation scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        ObjectAnimator fade = ObjectAnimator.ofFloat(fire, "alpha", 0.5f, 1f);

        fade.setDuration(500); // Duración de la animación en milisegundos
        fade.setRepeatCount(ObjectAnimator.INFINITE); // Repetir indefinidamente

        // Iniciar animaciones
        speechBalloon.startAnimation(scaleUp);
        BotonPersonajes.startAnimation(scaleUp);
        descripcion.startAnimation(scaleUp);
        fade.start();

        // Cambiar la sección en la actividad principal
        ((MainActivity) requireActivity()).changeMainSection(R.id.navigation_characters);

        btnSkip.setOnClickListener(v -> {
            // Cerrar la guía al pulsar botón "saltar"
            GuideDialogFragment guideDialog = (GuideDialogFragment) requireActivity().getSupportFragmentManager().findFragmentByTag("GuideDialog");
            if (guideDialog != null) {
                guideDialog.closeGuide(); // Llama a closeGuide() para guardar la preferencia y cerrar el diálogo
            }
        });

        btnNext.setOnClickListener(v -> {
            // Reproducir sonido "fwosh.wav"
            soundPool.play(soundId, 1, 1, 0, 0, 1);

            // Navegar al siguiente fragmento en la guia
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_guide2_to_guide3);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (soundPool != null) {
            soundPool.release(); // Liberar memoria en soundpool
            soundPool = null;
        }
    }
}
