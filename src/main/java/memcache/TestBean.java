package memcache;

import java.io.Serializable;
import java.util.Date;

public class TestBean implements Serializable{

    private int nativeInt;
    private String string;
    private Date date;

    public int getNativeInt() {
        return nativeInt;
    }

    public void setNativeInt(int nativeInt) {
        this.nativeInt = nativeInt;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
