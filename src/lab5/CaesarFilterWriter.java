package lab5;

import java.io.*;

class CaesarFilterWriter extends FilterWriter {
    private final char keyChar;

    public CaesarFilterWriter(Writer out, char keyChar) {
        super(out);
        this.keyChar = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + keyChar);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(str.charAt(i));
        }
    }
}

