package com.example.revisedandroidlightsout.models;


public class AboutEntry {

    public AboutEntry(String pText)
    {
        text = pText;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
