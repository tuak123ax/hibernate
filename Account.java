import java.io.Serializable;

public class Account<K,V> implements Serializable {
    private K key;
    private V value;
    private String type;
    Account(K key,V value)
    {
        this.key=key;
        this.value=value;
    }
    Account(K key,V value,String type)
    {
        this.key=key;
        this.value=value;
        this.type=type;
    }
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

