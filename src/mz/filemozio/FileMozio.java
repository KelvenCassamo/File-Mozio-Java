package mz.filemozio;

import mz.filemozio.interfaces.ObjectReadListener;
import mz.filemozio.interfaces.ObjectWriteListener;

import java.io.*;

public class FileMozio {

    private ObjectReadListener orl;
    private ObjectWriteListener owl;

    public FileMozio() {
    }

    // aqui ira criar o listener para a interface ObjectReadListener
    public void createObjectReadListener(ObjectReadListener orl) {
        this.orl = orl;
    }

    // este método será utilizado no momento em que o progresso for atualizado.
    private void setObjectReadProgress(String item, int progress) {
        if (orl != null) {
            orl.onProgress(item, progress);
        }
    }

    private void setObjectWriteProgress(String item, int progress) {
        if (owl != null) {
            owl.onProgress(item, progress);
        }

    }

    // Este metodo ira informar o sucesso após concluir a leitura.
    private void setReadSuccess(Object o, String path) {
        if (orl != null) {
            orl.onSuccess(o, path);
        }
    }

    // Este metodo ira informar os errors caso ocorra uma excepção durante a
    // leitura.
    private void setReadError(String message) {
        if (orl != null) {
            orl.onError(message);
        }
    }

    // Este metodo ira informar o sucesso após salvar o arquivo.
    private void setWriteSuccess(String path) {
        if (owl != null) {
            owl.onSuccess(path);
        }
    }

    // Este metodo ira informar os errors caso ocorra uma excepção quando estiver a
    // salvar.
    private void setWriteError(String message) {
        if (owl != null) {
            owl.onError(message);
        }
    }

    // aqui ira criar o listener para a interface ObjectWriteListener
    public void createObjectWriteListener(ObjectWriteListener owl) {
        this.owl = owl;
    }

    public void writeObject(Object objeto, String path) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            // aqui fiz uma pequena manipulação para obter o tamanho aproximado do arquivo
            // em bytes.
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream tempStream = new ObjectOutputStream(byteArrayOutputStream);
            tempStream.writeObject(objeto);
            tempStream.close();
            int tamanhoDoObjeto = byteArrayOutputStream.size();

            // escrever o objecto
            outputStream.writeObject(objeto);

            // criei esta estrutura para representar o progresso em percentagem
            for (int progress = 0; progress <= 100; progress += 10) {
                if (progress >= tamanhoDoObjeto) {
                    break;
                }
                this.setObjectWriteProgress(path, progress);
            }

            this.setObjectWriteProgress(path, 100); // aqui chamei o metodo setObjectWriteProgress que ira actualizar o
                                                    // progresso
            this.setWriteSuccess(path); // aqui chamei o metodo setWriteSuccess e adicionei um argumento, o path.
        } catch (IOException e) {
            this.setWriteError(e.getMessage());
        }
    }

    public void readObject(String path) {
        File file = new File(path);
        Object object = null;
        boolean error = false;
        if (file.exists()) {

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
                // aqui ira pegar i tamanho do arquivo.
                File arquivo = new File(path);
                long tamanhoDoArquivo = arquivo.length();
                long bytesLidos = 0;

                // aqui ira ler o objecto
                object = inputStream.readObject();

                int progress;
                while ((progress = (int) ((bytesLidos * 100) / tamanhoDoArquivo)) < 100) {
                    // aqui chamei o metodo setObjectReadProgress e adicionei dois argumentos, path
                    // e progress
                    this.setObjectReadProgress(path, progress);
                    bytesLidos += 1024; // aqui ira actualizar de 1 em 1kb
                }

                this.setObjectReadProgress(path, 100);
            } catch (IOException | ClassNotFoundException e) {
                error = true;
                this.setReadError(e.getMessage());

            }
        } else {
            error = true;
            this.setReadError("FileNotFound.");
        }
        if (!error) {
            // aqui chamei o metodo setReadSuccess e adicionei dois argumentos, object e
            // path
            this.setReadSuccess(object, path);
        }
    }
}
