package com.example.ms_pro.base.datacache;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyFileMangerHelper {

    public String readFile(Context context, String url) {
        String fileName = getFileName(url);

        if (fileName == null || context == null)
            return null;

        String line = null;
        StringBuffer res = null;
        InputStream in = null;
        try {
            in = context.openFileInput(fileName);
            if (in != null) {
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffreader = new BufferedReader(input);
                res = new StringBuffer();
                while ((line = buffreader.readLine()) != null) {
                    res.append(line);
                }
                in.close();
                return res.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean writeFile(Context context, String url, String data) {
        String fileName = getFileName(url);

        if (fileName == null || context == null)
            return false;

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            if (fos != null) {
                fos.write(data.getBytes());
                fos.close();
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private String getFileName(String url) {
        if (url != null)
            return String.valueOf(url.hashCode());
        return null;
    }

    public boolean deleteFile(Context context, String url) {
        String fileName = getFileName(url);
        if (fileName == null || context == null)
            return false;

        File file = context.getFileStreamPath(fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    public boolean checkFileExists(Context context, String url) {
        String fileName = getFileName(url);

        if (fileName == null || context == null)
            return false;

        File file = context.getFileStreamPath(fileName);

        return file.exists();
    }

    public boolean deleteAllFile(Context context) {
        File file = context.getFilesDir();
        File[] files = file.listFiles();
        if (files == null)
            return false;
        for (File f : files)
            f.delete();
        return true;
    }

}
