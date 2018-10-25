import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];
		//right now this goes through each element in codons and checks if it is in strand by 
		//iterating through	it until there are no more and appending it to a map
		//because it iterates for every element in codon it is O(c*n)
		//for(int k=0; k < codons.length; k++) {
			//String strand1 = codons[k];
			Iterator<Character> iter = strand.iterator();
			//if (!map.containsKey(strand1)){
			//	map.put(strand1, 0);
			//}
			while (iter.hasNext()) {
				char a = iter.next();
				char b = 'z';           // not part of any real codon
				char c = 'z';
				if (iter.hasNext()) {
					b = iter.next();
				}
				if (iter.hasNext()) {
					c = iter.next();
				}
				String cod = ""+a+b+c;
				//if (cod.equals(strand1)) {
				if (Arrays.asList(codons).contains(cod)) {
					int index = Arrays.asList(codons).indexOf(cod);
					if (!map.containsKey(cod)){
						map.put(cod, 0);
					}
					int x = map.get(cod); 
					x += 1;
					map.put(cod, x);
					ret[index] = map.get(cod);
				}
			//}
			//ret[index] = map.get(cod);
		}
		return ret;
	}
}
