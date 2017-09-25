import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Tokenizer {

	private static Map<String, Integer> dictionary = new HashMap<String, Integer>();

	public static void main(String[] args) {

		if(args.length>0){
			tokenizeData(args[0]);
		}

		else{
			System.out.println("Please input the folder path as argument");
		}

	}


	public static void tokenizeData(String folder) {

		try{

			File folderPath = new File(folder);			
			File[] listOfFiles = folderPath.listFiles();
			FileInputStream fileInputStream = null;
			DataInputStream dataInputStream = null;
			BufferedReader bufferedReader = null;

			//Starting to read all the files in the given folder directory

			int noOfDocuments=0;

			long startTime = System.currentTimeMillis();
			for(int i=0;i<listOfFiles.length;i++){
				File tempFile = listOfFiles[i];

				if(tempFile.isFile()){
					noOfDocuments++;
					fileInputStream = new FileInputStream(tempFile);
					dataInputStream = new DataInputStream(fileInputStream);
					bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

					String line = null;

					while((line=bufferedReader.readLine())!=null){

						StringTokenizer tokenizer = new StringTokenizer(line," ");

						while(tokenizer.hasMoreTokens()){

							String currString = tokenizer.nextToken();


							// removing xml tags and change to lower case
							currString =currString.replaceAll("<[^>]+>", "").toLowerCase();
							// removing white spaces and numeric values
							currString = currString.replaceAll("[0-9]","");
							//removing certain special characters
							currString = currString.replaceAll("[^\\w\\s-'.!:;]+", "");
							tokenizeAndAddToDictionary(currString,dictionary);								

						}
					}
				}
			}
			long endTime = System.currentTimeMillis();

			long timeDifference = endTime-startTime;
			long noOfTokens=0;
			ArrayList<String> tokensOccuringOnlyOnceList = new ArrayList<String>();
			for(Map.Entry<String, Integer> entry : dictionary.entrySet()){
				//System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
				noOfTokens+=entry.getValue();
				if(entry.getValue()==1){
					tokensOccuringOnlyOnceList.add(entry.getKey());
				}

			}

			long noOfUniqueTokens = dictionary.size();
			long noOfTokensOccuringOnlyOnce = tokensOccuringOnlyOnceList.size();

			double avgTokensPerDocument = 0;

			avgTokensPerDocument = noOfTokens/noOfDocuments;

			System.out.println("Number of tokens in Cranfield Collection: "+noOfTokens);
			System.out.println("Number of unique tokens in Cranfield Collection: "+noOfUniqueTokens);
			System.out.println("Number of tokens that occur only once: "+noOfTokensOccuringOnlyOnce);
			System.out.println("Average number of word tokens per document: "+avgTokensPerDocument);
			System.out.println("Number of documents scanned: "+noOfDocuments);
			System.out.println("Time taken: "+timeDifference/1000+" seconds");
			Map<String, Integer> sortedMapValue = new HashMap<String, Integer>();
			sortedMapValue = sortMap(dictionary);
			int counter=1;
			System.out.println("30 Most frequent words tokens with their frequency: ");
			for (Map.Entry<String, Integer> entry : sortedMapValue.entrySet()) {
				if(counter>30)
					break;
				counter++;
				System.out.println(entry.getKey() + "\t" + entry.getValue());
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public static void tokenizeAndAddToDictionary(String strToken,  Map<String, Integer> wordDictionary){


		// handle special cases
		if (strToken.endsWith("'s")) {
			strToken = strToken.replace("'s", "");
			strToken = strToken.replaceAll("['.]+", "");
			addToDictionary(strToken,wordDictionary);
		} else if (strToken.contains("-")) {			
			String[] tokensList = strToken.split("-");
			for (String token : tokensList) {
				token = token.replaceAll("['.]+", "");
				
				addToDictionary(token,wordDictionary);
			}
		} else if (strToken.contains("_")) {
			String[] tokensList = strToken.split("_");
			for (String token : tokensList) {
				token = token.replaceAll("['.]+", "");
				addToDictionary(token,wordDictionary);
			}
		} else {
			// default case
			strToken = strToken.replaceAll("['.]+", "");

			addToDictionary(strToken,wordDictionary);
		}


	}


	public static void addToDictionary(String strToken, Map<String, Integer> wordDictionary){
		strToken=strToken.trim();
		if(strToken!=null && strToken.length()>0){

			if(wordDictionary.containsKey(strToken)){
				wordDictionary.put(strToken, wordDictionary.get(strToken)+1);
			}
			else{
				wordDictionary.put(strToken, 1);
			}
		}


	}



	public static <K, V extends Comparable<? super V>> Map<K, V> sortMap(final Map<K, V> mapToSort) {
		List<Map.Entry<K, V>> entries = new ArrayList<Map.Entry<K, V>>(mapToSort.size());

		entries.addAll(mapToSort.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
			public int compare(final Map.Entry<K, V> entry1, final Map.Entry<K, V> entry2) {
				return entry2.getValue().compareTo(entry1.getValue());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}


