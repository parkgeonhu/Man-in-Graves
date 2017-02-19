package kr.sunrin.maningraves;
import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

import kr.sunrin.framework.Game;

public class FileOutput {
    String filename;
    FileOutputStream fos = null;
    Context ctx;

    public FileOutput(Game game, String filename) {
        ctx = (Context) game;
        this.filename = filename;
        try {
            fos = ctx.openFileOutput(this.filename, Context.MODE_PRIVATE);
        } catch (IOException e) {
        }
    }

    public void changeFile(String filename) {
        this.filename = filename;
        try {
            close();
            fos = ctx.openFileOutput(this.filename, Context.MODE_PRIVATE);
        } catch (IOException e) {
        }
    }

    public void write(String data) {
        try {
            fos.write(data.getBytes());
        } catch (IOException e) {
        }
    }

    public void close() {
        try {
            fos.close();
        } catch (IOException e) {
        }
    }
}
