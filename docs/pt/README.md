# FileMozio (Java) 
O **FileMozio** é uma biblioteca Java simples para manipulação de arquivos, focado em leitura e escrita de objectos. Esta biblioteca fornece atualização de progresso durante a leitura e escrita dos objectos, o que presumo que será uma boa experiência para o usuário.

## Funcionalidades

- **Leitura mais eficiente:** Esta biblioteca foi projectada exlusivamente para uma leitura mais eficiente de objectos a partir de arquivos.
- **Escrita Rápida:** Inclusive oferece uma escrita maisrápida de objectos em arquivos.
- **Atualização de Progresso:** Este recurso é útil, pois permite que os usuários acompanhem o progresso da leitura e escrita dos objectos.


## Instalação

Atualmente, o FileMozio não está disponível no repositório Maven Central. Mas pode adicionar a biblioteca manualmente no projecto basta sehuir estas etapas:

1. **Baixar a Biblioteca:**
   Faça o download do arquivo JAR mais recente da secção de lançamentos [releases](https://github.com/KelvenCassamo/File-Mozio-Java-releases) no GitHub.

2. **Adicionar o JAR ao Projecto:**
   Coloque esse arquivo JAR na pasta de bibliotecas (libs) do projecto.

## Uso da biblioteca FileMozio (em Java)
Para utilizar o FileMozio em java, pode seguir as etapas abaixo:


### 1. Importar a Biblioteca

Primeiro devemos incluir a biblioteca FileMozio.
````java
import mz.filemozio.*;
import mz.filemozio.FileMozio;
import mz.filemozio.interfaces.ObjectReadListener;
import mz.filemozio.interfaces.ObjectWriteListener;
`````
### 2. Criar uma Instância da Classe FileMozio

````java
FileMozio fileMozio = new FileMozio();
````
### 3. Configurar um listener para receber os Resultados
Utilizaremos a interface `ObjectWriteListener` que será praticamene o nosso ouvinte que nos ajudará a lidar com os resultados.

````java
/// Criar listener para receber os resulados do objecto salvo.
        fileMozio.createObjectWriteListener(new ObjectWriteListener() {

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
````
### Criar um objecto para salvar
Como exemplo, utilizaremos `ArrayList` que irá representar uma lista de nomes.

````java

ArrayList<String> nomes = new ArrayList<>();
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


````



### Utilização do método writeObject()
Lembre-se que podemos salvar varios tipos de objectos em arquivos, não apenas ArrayList. 
Bem, o primeiro argumento do método `writeObject` recebe um objecto e o segundo recebe o destino, ou o famoso path.
A estrutura é basicamente esta: `writeObject(Object, String)`.

````java

//Indicar o destino do arquivo que pretende criar.
//exemplo: eu pretendo adiconar em um directório com o nome dados.
String destino = "dados/nomes.bin";

/// Escrever o objecto dos nomes no arquivo do destino.
//Veja que neste exemplo segui a estrutura que apresentei e coloquei como o primeiro argumanto um objecto e como segundo uma String.
 fileMozio.writeObject(nomes, destino);

 ///Após executar, o arquivo será gerado no caminho do destino que forneu no segundo argumento.

````




### 3. Configurar um listener para receber os eesultados da leitura
Utilizaremos a interface `ObjectReadListener` que será praticamene o nosso ouvinte que nos ajudará a lidar com os resultados do objecto lido.

````java
 /// Criar listener para receber os resulados da leitura.
 fileMozio.createObjectReadListener(new ObjectReadListener() {

            @Override
            public void onProgress(String item, int progress) {
                System.out.println("Progresso: " + progress + "%");
            }

            @Override
            public void onSuccess(Object object, String path) {
           
            }

            @Override
            public void onError(String message) {
                System.out.println(message);
            }
        });
````

### Manipular o resuldado da leitura
Diferentemente do método `onSuccess` da interface `ObjectWriteListener`, o método `onSuccess` da interface `ObjectReadListener` recebe dois parâmetros o `Object` e o `String`.
Após a leitura bem sucedida, o método `onSuccess` é chamado e lhe é atribuído dois argumentos que mencionei anteriormente.
Abaixo, pegarei o trecho do método `onSuccess` da interface que criamos na secção anterior para que possamos manipular os resultados.

````java

 @Override
            public void onSuccess(Object object, String path) {
                nomes = new ArrayList<>();
                //atribuir o objecto convertido.
                nomes = (ArrayList<String>) object;
                
                //Listar os nomes
                for (var item : a) {
                    System.out.println("NOME: " + item);

                }

            }

````

Veja que neste trecho  `(ArrayList<String>) object;` fomos obrigados a realizar o **`cast`**, uma conversão dos valores do **`Object`** para **`ArrayList<String>`**.

**ATENÇÃO:** Esta conversao não foi aleatória, antes de converter devemos garantir que o resultado que o resultado do objecto seja compatível com o tipo para qual pretende converter.





### Utilização do método readObject()
O método `readObject` recebe apenas um argumento, que será o caminho do arquivo que desejamos pegar.
Este método obedece a seguinte estrutura: `readObject(String)`.
````java

//Indicar o caminho do arquivo que pretende ler.
//exemplo: eu pretendo ler o arquivo que possui a lista de nomes que salvei.
String arquivo = "dados/nomes.bin";

/// Ler o objecto dos nomes contidos no arquivo indicado.
//Veja que neste exemplo segui a estrutura que apresentei e coloquei como argumanto uma String que representa o caminho.
 fileMozio.readObject(arquivo);

 ///Após executar, o arquivo será lido e o método onSuccess será chamado.

````



## Releases

- **v1.0.0**
  - Funcionalidades iniciais de leitura e escrita de objectos em arquivos.
  - Atualizações de progresso durante a leitura e escrita.

Para obter detalhes sobre cada versão, veja as [Notas de Versão](https://github.com/KelvenCassamo/File-Mozio-Java-releases).

## Instalação manual

Atualmente, o FileMozio não está disponível no repositório Maven Central. Mas pode adicionar a biblioteca manualmente projecto basta seguir as [instruções de instalação](#Instalação).

## Contribuição
As contribuições são bem-vindas! Pode sentir-se à vontade para abrir uma [(issue)](https://github.com/KelvenCassamo/File-Mozio-Java/issues) ou enviar [(pull requests)](https://github.com/KelvenCassamo/File-Mozio-Java/pulls) para melhorar a biblioteca.

## Licença
Este projecto está licenciado sob a Licença MIT. Pode ver no arquivo [LICENSE.md](https://github.com/KelvenCassamo/File-Mozio-Java/blob/main/LICENSE) para detalhes.





