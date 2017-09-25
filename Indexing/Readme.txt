commands to run:

javac PostingFileEntry.java
javac DocumentFrequency.java
javac DictionaryEntryTerm.java
javac Stemmer.java
javac TrieDictionary.java
javac Compresser.java
javac -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar Lemmatizer.java
javac IndexBuilding.java

IndexBuilding.java is the main file to run.
Use following command to run it:
java -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar IndexBuilding <Cranfield dataset-path> <stopwords file-path> <output-directory path>

Example:
java -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar IndexBuilding Cranfield stopwords.txt hw2_output


If there is any version error in running the above commands, the please follow the steps mentioned below:
Since the csgrads1 machine has Java 1.7 installed, and my local machine has Java 1.8 installed; there is a need to mention -source 1.7 -target 1.7 in order to properly compile the Java files
The following library was used for Lemmatizer: stanfordcorenlp version 3.4.1
In order to compile the source files on csgrads1 machine, use these commands:

javac -source 1.7 -target 1.7 PostingFileEntry.java
javac -source 1.7 -target 1.7 DocumentFrequency.java
javac -source 1.7 -target 1.7 DictionaryEntryTerm.java
javac -source 1.7 -target 1.7 Stemmer.java
javac -source 1.7 -target 1.7 TrieDictionary.java
javac -source 1.7 -target 1.7 Compresser.java
javac -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar -source 1.7 -target 1.7  Lemmatizer.java
javac -source 1.7 -target 1.7 IndexBuilding.java

IndexBuilding.java is the main file to run.
Use following command to run it:
java -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar IndexBuilding <Cranfield dataset-path> <stopwords file-path> <output-directory path>

Example:
java -classpath .:/usr/local/corenlp341/stanford-corenlp-3.4.1.jar:/usr/local/corenlp341/stanford-corenlp-3.4.1-models.jar IndexBuilding Cranfield stopwords.txt hw2_output

Following are commands that I used to compile and run on my local machine:

javac PostingFileEntry.java
javac DocumentFrequency.java
javac DictionaryEntryTerm.java
javac Stemmer.java
javac TrieDictionary.java
javac Compresser.java
javac -classpath "stanford-corenlp-3.4.1.jar;stanford-corenlp-3.4.1-models.jar" Lemmatizer.java
javac IndexBuilding.java

java -classpath "stanford-corenlp-3.4.1.jar;stanford-corenlp-3.4.1-models.jar" IndexBuilding Cranfield stopwords.txt hw2_output