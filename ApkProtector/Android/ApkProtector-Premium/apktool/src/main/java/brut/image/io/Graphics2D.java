package brut.image.io;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Graphics2D {

    private BufferedImage mImage;
    private Paint mPaint = new Paint();

    public Graphics2D(BufferedImage bufferedImage) {
        this.mImage = bufferedImage;
    }

    public void drawImage(BufferedImage img, int x, int y,
                          int width, int height) {
        Canvas canvas = mImage.getCanvas();
        Bitmap targetBitmap = img.getBitmap();
        Rect location = new Rect(x, y, width, height);
        canvas.drawBitmap(targetBitmap, null, location, mPaint);
    }
}
