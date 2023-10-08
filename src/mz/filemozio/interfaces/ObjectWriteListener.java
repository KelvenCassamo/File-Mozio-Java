package mz.filemozio.interfaces;

public interface ObjectWriteListener {
    void onProgress(String item, int progress);

    void onSuccess(String object);

    void onError(String message);
}
