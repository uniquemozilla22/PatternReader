
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.DatatypeConverter;

public class FileScanner {
  
    /**
	 * name of the file
	 */
	private String NameofFile = "";

	/**
	 * bytes of the file will be saved as string in it
	 */
	private byte[] ReadingsInFile;

	/**
	 * calling the TreeMap and sttting the result in ResultofSearching
	 */
	public Map<Integer, byte[]> ResultofSearching = new TreeMap<Integer, byte[]>();

	/**
	 * formating the string to add the file name and print it into the area.
	 */
	public String formattedResult = "Filename: ";
    
	
	/**
	 * this constructor scans the file and shows the pattern found.
	 * 
	 * This constructor has other methods called.
	 * 
	 * @param file contains the file given by the user
	 */
    public FileScanner(File file) {
        NameofFile = file.getName();//getting the name of the file and putting it to a string 
        formattedResult += NameofFile + "\r\n"; // formating the name of the file with logic.
        
        //using the try catch method for IOException if any file not found
        try {
			fileBytes(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // calling the method to scan the file.        
        scanningFIle();
        
        //calling the method to fill the text area with result of the scanning
        fillFormattedResult();
    }
    
    /**
     * This method parses and fills the Scanned file results into the Text area in a proper format.
     */

    private void fillFormattedResult() {
    	
    	//setting a boolean to false
		boolean isPatternFound = false;


		//sorting the values by key in search result and seetting it ot set<Map.Entry>
		Set<Map.Entry<Integer, byte[]>> enteries = sortingValuePairsByKeys(ResultofSearching);

		// if the search result has the size grater than zero
		if (ResultofSearching.size() > 0) {
			
			//for each entry found the formating result is done
			for (Entry<Integer, byte[]> entry : enteries) {
				
				//using the proper way to dyplay the entries and also converting them in required way.
				formattedResult += String.format("Pattern found: %s, at offset: %d (0x%s) within the file.\r\n",
						DatatypeConverter.printHexBinary(entry.getValue()), entry.getKey(),
						Integer.toHexString(entry.getKey()));
			}
			//s the pattern is found then the vlaue of pattern found will be true
			isPatternFound = true;
		}
		
		// in case there are no patterns found
		if (!isPatternFound)
			formattedResult += "No Patterns Found.\r\n";
		formattedResult += "\r\n";
    }
    
    	/**
	 * Scans the file and calls other method for finding the index of the patterns 
	 * 
	 * @see https://stackoverflow.com/questions/7194522/how-to-putall-on-java-hashmap-contents-of-one-to-another-but-not-replace-existi
	 */
	private void scanningFIle() {
		for (byte[] pattern : pattern.bytes) {
			Map<Integer, byte[]> tmp = new HashMap<Integer, byte[]>(indexOfPattern(ReadingsInFile, pattern));
			if (!ResultofSearching.isEmpty())
				tmp.keySet().removeAll(ResultofSearching.keySet());
			ResultofSearching.putAll(tmp);
			ResultofSearching.putAll(indexOfPattern(ReadingsInFile, pattern));
		}
    }
	
	/**
	 * This method Returns the indexes of the pattern when the parameters are given in a Directory
	 * @param source   This contains the file contents
	 * @param pattern	This contains the patterns
	 * @return	Returns the answer 
	 */
    
    public Map<Integer, byte[]> indexOfPattern(byte[] source, byte[] pattern) {
		int sourceLength = source.length;
		int patternLength = pattern.length;

		Map<Integer, byte[]> answer = new TreeMap<>();

		int loopTo = sourceLength - patternLength;
		if (loopTo < 0) {
			return answer;
		}

		for (int index = 0; index < loopTo; index++) {
			if (source[index] == pattern[0]) {
				boolean exists = true;
				for (int i = 0; i < patternLength; i++) {
					if (source[index + i] != pattern[i]) {
						exists = false;
					}
				}
				if (exists) {
					answer.put(index, pattern);
				}
			}
		}
		return answer;
    }
    /**
	 * 
	 * This method reads all the bytes and stores it in string format.
	 * 
	 * @param file the file to read bytes from
	 * @throws IOException if the file could not be read
	 */
	private void fileBytes(File file) throws IOException {
		ReadingsInFile = Files.readAllBytes(file.toPath());
    }
	
	  /**
		 * 
		 * This method shows to sort the value pairs by key in a Map.
		 * 
		 * @param <K> Generic Type Key
		 * @param <V> Generic Type Valye
		 * @param map The map to sort
		 * @return sorting the map by the key
		 * 
		 * @see https://stackoverflow.com/questions/2864840/treemap-sort-by-value
		 * @see https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
		 * 
		 */
		static <K extends Comparable<? super K>, V> SortedSet<Map.Entry<K, V>> sortingValuePairsByKeys(Map<K, V> map) {
			SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
				@Override
				public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
					int res = e1.getKey().compareTo(e2.getKey());
					return res != 0 ? res : 1;
				}
			});
			sortedEntries.addAll(map.entrySet());
			return sortedEntries;
		}
  
}
