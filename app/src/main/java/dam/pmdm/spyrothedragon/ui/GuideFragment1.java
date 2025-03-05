package dam.pmdm.spyrothedragon.ui;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dam.pmdm.spyrothedragon.MainActivity;
import dam.pmdm.spyrothedragon.R;

public class GuideFragment1 extends Fragment {

    private SoundPool soundPool;
    private int soundId; // ID del sonido cargado

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // referencia los elementos de la vista
        Button btnSkip = view.findViewById(R.id.btn_skip);
        TextView guideText = view.findViewById(R.id.textView);
        Button btnNext = view.findViewById(R.id.btn_next);
        View diamond = view.findViewById(R.id.diamond);


        // Configurar SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1) // Número máximo de sonidos simultáneos
                .setAudioAttributes(audioAttributes)
                .build();

        // Cargar el sonido en memoria
        soundId = soundPool.load(getContext(), R.raw.intro, 1);

        // Cargar la animación de translate y fade
        ObjectAnimator fade = ObjectAnimator.ofFloat(btnNext, "alpha", 0.5f, 1f);
        ObjectAnimator fade2 = ObjectAnimator.ofFloat(diamond, "alpha", 0.5f, 1f);
        Animation translate = AnimationUtils.loadAnimation(getContext(), R.anim.translationy);

        fade.setDuration(500); // Duración de la animación en milisegundos
        fade.setRepeatCount(ObjectAnimator.INFINITE); // Repetir indefinidamente
        fade.start();
        fade2.setDuration(500); // Duración de la animación en milisegundos
        fade2.setRepeatCount(ObjectAnimator.INFINITE); // Repetir indefinidamente
        fade2.start();
        diamond.startAnimation(translate);

        btnSkip.setOnClickListener(v -> {
            // Cerrar la guía al pulsar botón "saltar"
            GuideDialogFragment guideDialog = (GuideDialogFragment) requireActivity().getSupportFragmentManager().findFragmentByTag("GuideDialog");
            if (guideDialog != null) {
                guideDialog.closeGuide(); // Llama a closeGuide() para guardar la preferencia y cerrar el diálogo
            }
        });


        btnNext.setOnClickListener(v -> {
            // Reproducir sonido al hacer clic
            soundPool.play(soundId, 1, 1, 0, 0, 1);

            // Navegar al siguiente fragmento
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_guide1_to_guide2);
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
