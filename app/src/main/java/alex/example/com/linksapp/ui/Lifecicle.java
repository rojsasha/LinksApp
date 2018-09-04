package alex.example.com.linksapp.ui;

public interface Lifecicle<V> {
    void bind(V view);
    void unbind();
}
