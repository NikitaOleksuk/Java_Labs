package lab5;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

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
