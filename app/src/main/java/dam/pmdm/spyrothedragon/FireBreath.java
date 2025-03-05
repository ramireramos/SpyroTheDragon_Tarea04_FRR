package dam.pmdm.spyrothedragon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class FireBreath extends View {

    private Paint firePaint;
    private Random random;
    private float[][] flames;
    private int numFlames = 20; // Cantidad de partículas de fuego
    private int maxLength = 50; // Longitud del aliento de fuego

    public FireBreath(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        firePaint = new Paint();
        firePaint.setStyle(Paint.Style.FILL);
        random = new Random();
        flames = new float[numFlames][8]; // [x, y, tamaño]

        // Inicializar las partículas de fuego
        for (int i = 0; i < numFlames; i++) {
            flames[i][0] = 0;
            flames[i][1] = 0;
            flames[i][2] = random.nextInt(20) + 10; // Tamaño de la llama
        }

        postInvalidateOnAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int startX = width / 2;
        int startY = height / 2;

        for (int i = 0; i < numFlames; i++) {
            // Simular la dirección del fuego
            flames[i][0] = startX - (i * maxLength / numFlames) + random.nextFloat() * 40 - 20; // Expansión lateral
            flames[i][1] = startY - (i * maxLength / numFlames) + random.nextFloat() * 10 - 20; // Subida irregular
            float flameSize = flames[i][2];

            // Crear un degradado de color para el fuego
            firePaint.setShader(new RadialGradient(flames[i][0], flames[i][1], flameSize,
                    new int[]{Color.YELLOW, Color.RED, Color.TRANSPARENT},
                    new float[]{0.2f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));

            canvas.drawCircle(flames[i][0], flames[i][1], flameSize, firePaint);
        }

        // Actualizar la animación del fuego
        postInvalidateDelayed(50);
    }
}
