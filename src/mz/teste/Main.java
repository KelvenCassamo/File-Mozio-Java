package mz.teste;

import java.util.ArrayList;

import mz.filemozio.FileMozio;
import mz.filemozio.interfaces.ObjectReadListener;
import mz.filemozio.interfaces.ObjectWriteListener;

public class Main {
    private static ArrayList<String> nomes;

    public static void main(String[] args) {

        FileMozio files = new FileMozio();

        nomes = new ArrayList<>();
        nomes.add("Allen");
        nomes.add("Kelven");
        nomes.add("Emiliana");
        nomes.add("Enoque");
        nomes.add("Kespar");
        nomes.add("Faimo");
        nomes.add("António");
        nomes.add("Khelon");
        nomes.add("Ian");
        nomes.add("Edilson");

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
        files.writeObject(nomes, "cassamo.bin");

        /// Criar listener para receber os resulados da leitura.
        files.createObjectReadListener(new ObjectReadListener() {

            @Override
            public void onProgress(String item, int progress) {
                System.out.println("Lido: " + progress + "%");
            }

            @Override
            public void onSuccess(Object object, String path) {
                nomes = (ArrayList<String>) object;

                for (var item : nomes) {
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
