import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

public class RestrictionEnzymes extends ConsoleProgram{
	
	HashMap<String, String> nucleotideEnzymeMap = new HashMap<String,String>();
	int numberOfEnzymesFound = 0;
	
	public void run(){
		
		getEnzymeList();
		
		while(true){
			
			String gene = readLine("Type nucleotide sequence in the gene: ");
			gene = gene.toUpperCase();
			
			if(isValidGene(gene)){
				searchForNucleotideSequence(gene);
				if (numberOfEnzymesFound == 0)
					println("No enzymes found.");
				
				println("\n**End of enzyme search.**\n");
				numberOfEnzymesFound = 0;
			}
			else{
				println("That is not a valid nucleotide sequence.");
			}
		}	
	}
	
	public boolean isValidGene(String gene){
		boolean valid = true;
		for (char ch ='A'; ch <= 'Z'; ch++){
			if (ch != 'A' && ch!= 'G' && ch != 'T' && ch!= 'C' && gene.indexOf(ch)!= -1){
				valid = false;
			}
		}
		
		return valid;
	}
	
	public void searchForNucleotideSequence(String gene){
		
		for (String s : nucleotideEnzymeMap.keySet()){
			int i = gene.indexOf(s);
			if (i != -1){
				numberOfEnzymesFound++;
				println(nucleotideEnzymeMap.get(s) + " to cut "  + s + " sequence which starts with " + (i+1) + "th nucleotide" );
			}
		}
		
	}
	
	public void getEnzymeList()
	{
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("enzymeList.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(true){
			String line = null;
			try {
				line = rd.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (line == null)
				break;
			
			StringTokenizer st = new StringTokenizer(line, ",");
			String nucleotides =st.nextToken();
			String enzyme = st.nextToken();
			nucleotideEnzymeMap.put(nucleotides,enzyme);
		}
		
	}

}
