package purplestudio.BGL.Graphic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import purplestudio.BGL.Models.DrawableObject;
import purplestudio.BGL.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Graphic
{
    private int tmpId;
    private int layerCursor;
    private int layersAmount;
    private Object acquirer;
    private HashMap<String, Bitmap> bitmaps;
    private List<DrawableObject>[] objects;
    private List<DrawableObject> tmpLayer;
    private Context context;
    private Bitmap tmpBitmap;
    private DrawableObject tmpObject;
    private Semaphore mutex;

    public Graphic(Context context, int layersAmount)
    {
        mutex = new Semaphore(1);

        acquire();
        this.context = context;
        this.layersAmount = layersAmount;
        bitmaps = new HashMap<>();
        objects = new ArrayList[layersAmount];

        for (int i = 0; i < layersAmount; i++)
        {
            objects[i] = new ArrayList<>();
        }
        mutex.release();
    }

    private void loadBitmap(DrawableObject object)
    {
        if (!bitmaps.containsKey(object.getDrawable()))
        {

            tmpId = context.getResources().getIdentifier(
                    object.getDrawable(),
                    Paths.DRAWABLE.toString(),
                    context.getPackageName()

            );

            tmpBitmap = BitmapFactory.decodeResource(context.getResources(), tmpId);

            bitmaps.put(object.getDrawable(), tmpBitmap);
        }

        object.setBitmap(bitmaps.get(object.getDrawable()));

    }

    private void acquire()
    {
        try
        {
            mutex.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean pushBack(DrawableObject object)
    {
        acquire();
        layerCursor = 0;
        loadBitmap(object);
        objects[object.layerId].add(object);
        mutex.release();
        return true;
    }

    public Bitmap getBitmap(String drawable){
        acquire();
        if (!bitmaps.containsKey(drawable))
        {

            tmpId = context.getResources().getIdentifier(
                    drawable,
                    Paths.DRAWABLE.toString(),
                    context.getPackageName()

            );

            tmpBitmap = BitmapFactory.decodeResource(context.getResources(), tmpId);

            bitmaps.put(drawable, tmpBitmap);
        }

        mutex.release();
        return bitmaps.get(drawable);
    }

    public void setObjects(List<DrawableObject> objects, int layerId)
    {
        acquire();
        this.objects[layerId] = new ArrayList<>(objects);
        mutex.release();
    }

    public List<DrawableObject> getLayer(int layerId)
    {
        acquire();
        tmpLayer = new ArrayList<>(objects[layerId]);
        mutex.release();
        return tmpLayer;
    }

    public void clearLayer(int layerId){
        acquire();
        objects[layerId] = new ArrayList<>();
        mutex.release();
    }

    public int getLayersAmount()
    {
        return layersAmount;
    }
}
