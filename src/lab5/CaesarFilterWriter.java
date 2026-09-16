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

// Потік фільтрації для дешифрування під час читання
class CaesarFilterReader extends FilterReader {
    private final char keyChar;

    public CaesarFilterReader(Reader in, char keyChar) {
        super(in);
        this.keyChar = keyChar;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? -1 : (c - keyChar);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int charsRead = super.read(cbuf, off, len);
        if (charsRead == -1) return -1;
        for (int i = off; i < off + charsRead; i++) {
            cbuf[i] = (char) (cbuf[i] - keyChar);
        }
        return charsRead;
    }
}
