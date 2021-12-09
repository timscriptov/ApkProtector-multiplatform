package brut.image.io;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BufferedImage {

    public static final Bitmap.Config TYPE_INT_RGB = Bitmap.Config.RGB_565;
    public static final Bitmap.Config TYPE_INT_ARGB = Bitmap.Config.ARGB_8888;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mDynamicPaint = new Paint();

    public BufferedImage(Bitmap bitmap) {
        this.mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        this.mCanvas = new Canvas(mBitmap);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public BufferedImage(int width, int height, Bitmap.Config config) {
        mBitmap = Bitmap.createBitmap(width, height, config);
        this.mCanvas = new Canvas(mBitmap);
    }

    public int getWidth() {
        return mBitmap.getWidth();
    }

    public int getHeight() {
        return mBitmap.getHeight();
    }

    public Graphics2D createGraphics() {
        return new Graphics2D(this);
    }

    public void release() {
        if (!mBitmap.isRecycled()) {
            mBitmap.recycle();
        }
    }

    public void setRGB(int x, int y, int color) {
        mDynamicPaint.setColor(color);
        mCanvas.drawPoint(x, y, mDynamicPaint);
    }
}
