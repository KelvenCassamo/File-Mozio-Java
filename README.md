Veja a documentação em [`Português`](https://github.com/KelvenCassamo/File-Mozio-Java/tree/main/docs/pt)
Note: The official documentation was written in [`Portuguese`](https://github.com/KelvenCassamo/File-Mozio-Java/tree/main/docs/pt).

# FileMozio (Java)

**FileMozio** is a simple Java library for file manipulation, focusing on reading and writing objects. This library provides progress updates during the reading and writing of objects, which I assume will be a good experience for the user.

## Features

- **More Efficient Reading:** This library is exclusively designed for more efficient reading of objects from files.
- **Fast Writing:** It also offers faster writing of objects to files.
- **Progress Update:** This feature is useful as it allows users to track the progress of reading and writing objects.

## Installation

Currently, FileMozio is not available in the Maven Central repository. But you can manually add the library to the project by following these steps:

1. **Download the Library:**
   Download the latest JAR file from the releases section [here](https://github.com/KelvenCassamo/File-Mozio-Java-releases) on GitHub.

2. **Add the JAR to the Project:**
   Place this JAR file in the project's library (libs) folder.

## Using the FileMozio Library (in Java)
To use FileMozio in Java, you can follow the steps below:


### 1. Importing the Library

First, we need to import the FileMozio library.
````java
import mz.filemozio.*;
import mz.filemozio.FileMozio;
import mz.filemozio.interfaces.ObjectReadListener;
import mz.filemozio.interfaces.ObjectWriteListener;
`````
### 2. Create an Instance of the FileMozio Class

````java
FileMozio fileMozio = new FileMozio();
````
### 3. Set up a listener to receive the Results
We will use the `ObjectWriteListener` interface which will be practically our listener that will help us deal with the results.

````java
/// Create listener to receive the results of the saved object.
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
### Create an object to save
As an example, we will use `ArrayList` which will represent a list of names.

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



### Using the writeObject() method
Remember that we can save several types of objects in files, not just ArrayList. 
Well, the first argument of the `writeObject` method receives an object and the second receives the target, or the famous path.
The structure is basically this: `writeObject(Object, String)`.

````java

//Indicate the destination of the file you want to create.
//Example: I want to add in a directory with the name Data.
String destino = "dados/nomes.bin";

/// Write the object names in the destination file.
//Note that in this example i followed the structure i presented and placed an object as the first argument and a String as the second.
 fileMozio.writeObject(nomes, destino);

 ///After executing, the file will be generated in the path of the destination that you provided in the second argument.

````




### 3. Set up a listener to receive read results
We will use the `ObjectReadListener` interface which will be practically our listener that will help us deal with the results of the read object.

````java
 /// Create a listener to receive the results of the reading.
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

### Handle the Reading Result
Unlike the `onSuccess` method of the 'ObjectWriteListener' interface, the `onSuccess` method of the `ObjectReadListener` interface takes two parameters: `Object` and `String`.
Upon successful reading, the `onSuccess` method is called and assigned two arguments that I mentioned earlier.
Below, I'll take the snippet of the 'onSuccess' method from the interface we created in the previous section so that we can manipulate the results.

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

Note that in this snippet `(ArrayList<String>) object;` we were forced to perform **'cast'**, a conversion of the values of **'Object'** to **'ArrayList<String>'**.

**PLEASE NOTE:** This conversion was not random, before converting we must ensure that the result of the object is compatible with the type you want to convert to.





### Using the readObject() method
The `readObject` method takes only one argument, which will be the path of the file we want to grab.
This method obeys the following structure: `readObject(String)`.
````java

//Enter the path of the file you want to read.
//Example: I want to read the file that has the list of names I saved.
String arquivo = "dados/nomes.bin";

/// Read the object from the names contained in the given file.
//Note that in this example I followed the structure I presented and put as an argument a String that represents the path.
 fileMozio.readObject(arquivo);

 ///After execution, the file will be read and the onSuccess method will be called.

````



## Releases

- **v1.0.0**
  - Initial features of reading and writing objects in archives.
  - Progress updates while reading and writing.

For details on each release, see the [Release Notes](https://github.com/KelvenCassamo/File-Mozio-Java-releases).

## Manual Installation

Currently, FileMozio is not available in the Maven Central repository. But you can add the library manually to the project just follow the [installation instructions](#Instalação).

## Contribution
Contributions are welcome! You can feel free to open an [(issue)](https://github.com/KelvenCassamo/File-Mozio-Java/issues) or submit [(pull requests)](https://github.com/KelvenCassamo/File-Mozio-Java/pulls) to improve the library.

## License
This project is licensed under the MIT License. You can see the file [LICENSE.md](https://github.com/KelvenCassamo/File-Mozio-Java/blob/main/LICENSE) for details.





