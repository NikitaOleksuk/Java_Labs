package lab10;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nManager {
    private ResourceBundle bundle;
    private Locale currentLocale;

    public I18nManager(Locale initialLocale) {
        setLocale(initialLocale);
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;

        this.bundle = ResourceBundle.getBundle("lab10.location.messages", currentLocale);
    }

    public String get(String key) {
        return bundle.getString(key);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
