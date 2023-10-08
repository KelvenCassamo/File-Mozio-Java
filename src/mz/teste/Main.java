package mz.teste;

import java.util.ArrayList;

import mz.filemozio.FileMozio;
import mz.filemozio.interfaces.ObjectReadListener;
import mz.filemozio.interfaces.ObjectWriteListener;

public class Main {
    private static ArrayList<String> a;

    public static void main(String[] args) {

        FileMozio files = new FileMozio();

        a = new ArrayList<>();
        /*
         * for (int i = 0; i < 100; i++) {
         * a.add("Cassamo" + 100 * i);
         * 
         * }
         */

        /// Criar listener para receber os resulados do objecto salvo.
        files.createObjectWriteListener(new ObjectWriteListener() {

            @Override
            public void onProgress(String item, int progress) {
                System.out.println("progresso: " + progress + "%");
            }

            @Override
            public void onSuccess(String path) {
                System.out.println("Salvo com sucesso!");
            }

            @Override
            public void onError(String message) {
                System.out.println(message);
            }
        });
        files.writeObject(a, "cassamo.bin");

        /// Criar listener para receber os resulados da leitura.
        files.createObjectReadListener(new ObjectReadListener() {

            @Override
            public void onProgress(String item, int progress) {
                System.out.println("Lido: " + progress + "%");
            }

            @Override
            public void onSuccess(Object object, String path) {
                a = (ArrayList<String>) object;

                for (var item : a) {
                    System.out.println("ITEM: " + item);

                }

            }

            @Override
            public void onError(String message) {
                System.out.println(message);
            }
        });

        // files.readObject("cassamo.bin");

    }

}
