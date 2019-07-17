package purplestudio.BGL.Injection;

import android.os.Looper;

public abstract class Sender
{
    protected static Injector injector;

    public Sender(){
        if(injector == null)
            injector = new Injector(Looper.getMainLooper());
    }

}
