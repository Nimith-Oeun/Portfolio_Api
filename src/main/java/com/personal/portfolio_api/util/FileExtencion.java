package com.personal.portfolio_api.util;

public class FileExtencion {

    public static String getExtension(String file) {
        int index = file.lastIndexOf('.');
        if (index < 0 ){
            return null;
        }
        return file.substring(index + 1);
    }
}