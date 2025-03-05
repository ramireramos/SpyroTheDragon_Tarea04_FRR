package dam.pmdm.spyrothedragon.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import dam.pmdm.spyrothedragon.R;

public class GuideDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_guide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el NavController del NavHostFragment dentro del DialogFragment
        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_fragment_guide);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            // La navegación entre fragmentos de la guía ocurre dentro del DialogFragment
        }
    }

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog; // Aplicar estilo de pantalla completa con fondo transparente
    }

    public void closeGuide() {
        // Guardar que la guía ya fue vista
        Log.d("GUIDE_DEBUG", "Método closeGuide() ejecutado. Guardando guide_shown = true");

        SharedPreferences prefs = requireActivity().getSharedPreferences("SpyroPrefs", requireActivity().MODE_PRIVATE);
        prefs.edit().putBoolean("guide_shown", true).commit();

        dismiss(); // Cierra el diálogo y vuelve a MainActivity
    }
}
