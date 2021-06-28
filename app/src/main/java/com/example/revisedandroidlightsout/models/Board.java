package com.example.revisedandroidlightsout.models;

import java.util.ArrayList;

public class Board {

    private ArrayList<Light> lights;
    private int width;
    private int height;

    public Board()
    {
        lights = new ArrayList<Light>();
        width = height = 5;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void switchLights(int position)
    {
        lights.get(position).Switch(); // clicked on light

        if(position >= width) // Up light, if exists
            lights.get(position - width).Switch();

        if(position < lights.size() - width) // Down light, if exists
            lights.get(position + height).Switch();

        if(position % width != 0) //Left light, if exists
            lights.get(position - 1).Switch();

        if((position+1) % width != 0)
            lights.get(position+1).Switch();
    }
    public void initBoard()
    {
        lights.clear();
        for(int i = 0; i < width*height; i++)
        {
            Light l = new Light(false);
            lights.add(l);
        }

        int randomNumberOfTimes = (int) (Math.random() *26 + 5); // random number between 5 and 25
        int contor = 0;
        int randomPosition;

        while(contor < randomNumberOfTimes)
        {
            randomPosition = (int)(Math.random()* 25);
            switchLights(randomPosition);
            ++contor;
        }

    }

    public boolean isGameDone()
    {
        for (Light light : lights)
        {
            if(light.IsItOn())
                return false;
        }
        return true;
    }

}
