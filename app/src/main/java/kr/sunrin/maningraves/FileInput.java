package kr.sunrin.maningraves;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.sunrin.framework.Game;

public class FileInput{
    FileInputStream fis;
    BufferedReader reader;
    Context ctx;
    String filename;

    public FileInput(Game game, String filename) {
        this.ctx = (Context) game;
        this.filename = filename;

        try {
            fis = ctx.openFileInput(this.filename);
            reader = new BufferedReader(new InputStreamReader(fis));
        } catch (IOException e) {
        }
    }

    public String getData() throws IOException {
        return reader.readLine();
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void changeFile(String filename) {
        this.filename = filename;
        try {
            close();
            fis = ctx.openFileInput(this.filename);
            reader = new BufferedReader(new InputStreamReader(fis));
        } catch (IOException e) {
        }
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
        }
    }

    public void nullify() {
        reader = null;
        fis = null;
    }
}
