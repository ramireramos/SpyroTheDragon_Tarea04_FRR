package dam.pmdm.spyrothedragon.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.pmdm.spyrothedragon.FireBreath;
import dam.pmdm.spyrothedragon.R;
import dam.pmdm.spyrothedragon.models.Collectible;
import dam.pmdm.spyrothedragon.VideoActivity;

public class CollectiblesAdapter extends RecyclerView.Adapter<CollectiblesAdapter.CollectiblesViewHolder> {

    private List<Collectible> list;
    private Handler handler = new Handler();
    private int tapCount = 0; // Contador de toques rápidos

    public CollectiblesAdapter(List<Collectible> collectibleList) {
        this.list = collectibleList;
    }

    @Override
    public CollectiblesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CollectiblesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectiblesViewHolder holder, int position) {
        Collectible collectible = list.get(position);
        holder.nameTextView.setText(collectible.getName());

        // Cargar la imagen (simulado con un recurso drawable)
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(collectible.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imageImageView.setImageResource(imageResId);
        holder.fireView.setVisibility(View.INVISIBLE);

        // Easter Egg: Detectar 4 toques rápidos en la imagen de las gemas
        holder.imageImageView.setOnClickListener(v -> {
            if (collectible.getName().equalsIgnoreCase("Gemas")) { // Solo contar si es "Gemas"
                tapCount++;
                if (tapCount == 4) {
                    tapCount = 0; // Reiniciar contador
                    launchEasterEgg(holder.itemView.getContext());
                }

                // Reiniciar el contador después de 1.5 segundos si no se sigue tocando
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(() -> tapCount = 0, 1500);
            }
        });
    }

    private void launchEasterEgg(Context context) {
        Intent intent = new Intent(context, dam.pmdm.spyrothedragon.VideoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CollectiblesViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageImageView;
        FireBreath fireView;

        public CollectiblesViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            imageImageView = itemView.findViewById(R.id.image);
            fireView = itemView.findViewById(R.id.fire_container);
        }
    }
}
