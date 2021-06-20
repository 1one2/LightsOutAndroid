package com.example.lightsoutandroid.models;

import android.widget.ImageView;

import com.example.lightsoutandroid.R;
import com.example.lightsoutandroid.enums.LightState;
import com.example.lightsoutandroid.interfaces.OnItemClickListener;

public class Light {

    private LightState lightState;
    private int whichLight;

    public Light(boolean isItOn)
    {
        if(isItOn)
        {
            lightState = LightState.On;
            whichLight = (R.raw.lighton);
        }
        else
        {
            lightState = LightState.Off;
            whichLight = (R.raw.lightoff);
        }

    }

    public int getImageSource()
    {
        return whichLight;
    }

    public boolean isItOn()
    {
        if(lightState == LightState.On)
            return true;
        return false;
    }

    public void Switch()
    {
        if(lightState == LightState.On)
        {
            lightState = LightState.Off;
            whichLight = (R.raw.lightoff);
        }
        else
        {
            lightState = LightState.On;
            whichLight = (R.raw.lighton);
        }
    }

}
