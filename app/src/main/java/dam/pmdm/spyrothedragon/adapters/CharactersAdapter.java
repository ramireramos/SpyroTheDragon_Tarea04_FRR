package dam.pmdm.spyrothedragon.adapters;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dam.pmdm.spyrothedragon.FireBreath;
import dam.pmdm.spyrothedragon.R;
import dam.pmdm.spyrothedragon.models.Character;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> {

    private List<Character> list;
    private static final long LONG_PRESS_DURATION = 5000; // 5 seconds

    public CharactersAdapter(List<Character> charactersList) {
        this.list = charactersList;
    }

    @Override
    public CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharactersViewHolder holder, int position) {
        Character character = list.get(position);
        holder.nameTextView.setText(character.getName());

        // Cargar la imagen (simulado con un recurso drawable)
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(character.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imageImageView.setImageResource(imageResId);

        // Verificar si el personaje es Spyro
        if (character.getName().equals("Spyro")) {
            holder.fireView.setVisibility(View.INVISIBLE);

            Handler handler = new Handler(Looper.getMainLooper());
            Runnable showFireRunnable = () -> {
                holder.fireView.setVisibility(View.VISIBLE);
            };

            holder.imageImageView.setOnLongClickListener(v -> {
                handler.postDelayed(showFireRunnable, LONG_PRESS_DURATION);
                return true;
            });

            holder.imageImageView.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.removeCallbacks(showFireRunnable);
                    holder.fireView.setVisibility(View.INVISIBLE);
                }
                return false;
            });
        } else {
            holder.imageImageView.setOnLongClickListener(null);
            holder.imageImageView.setOnTouchListener(null);
            holder.fireView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CharactersViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageImageView;
        FireBreath fireView;

        public CharactersViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            imageImageView = itemView.findViewById(R.id.image);
            fireView = itemView.findViewById(R.id.fire_container);
        }
    }
}