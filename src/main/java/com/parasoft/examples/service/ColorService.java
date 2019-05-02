
package com.parasoft.examples.service;

import java.awt.Color;

import org.springframework.stereotype.Component;

@Component
public class ColorService
{
    public Color adapter(String color)
    {
        Color result = null;
        switch (color) {
            case "red":
                result = Color.red;
                break;
            case "yellow":
                result = Color.yellow;
                break;
            case "green":
                result = Color.green;
                break;
            default:
                result = Color.gray;
                break;
        }
        return result;
    }
}
