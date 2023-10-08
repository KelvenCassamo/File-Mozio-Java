package mz.filemozio.interfaces;

public interface ObjectReadListener {
    void onProgress(String item, int progress);

    void onSuccess(Object object, String path);

    void onError(String message);
}
