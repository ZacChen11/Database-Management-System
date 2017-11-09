
import java.io.*;

public class KWayMerger {
	
	int NumberOfSublists;

	InputStream KSublistInputStream[];

	
	public void mergeSublistsEmployees(SublistCreator sc) {
		merge(sc , "Employees2", new File("SortedEmployees.txt"));
		closeInputStreams();
		
	}
	
	public void mergeSublistProjects(SublistCreator sc) {
		merge(sc, "Projects2", new File("SortedProjects.txt"));
		closeInputStreams();
	}

	public void merge(SublistCreator sc, String filetype, File sortedfile){
		NumberOfSublists = sc.getNumberOfSublists();
		createInputStreams(NumberOfSublists, filetype);
		MinHeap myMinHeap = new MinHeap(KSublistInputStream);
		try {
			FileWriter fw = new FileWriter(sortedfile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			while(true){
				String temp = myMinHeap.poll();
				if(temp!=null){
					bw.write(temp+"\n");
					bw.flush();
				}
				else
					break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private void closeInputStreams() {
		
		int i;
		for (i = 0; i < KSublistInputStream.length; i++) {
			try {
				KSublistInputStream[i].close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

	private void createInputStreams(int numberOfSublist, String filetype) {
		this.KSublistInputStream = new InputStream[numberOfSublist];
		String filename;
		for (int i = 0; i < numberOfSublist; i++) {
			filename = filetype + "_sl_" + i;
			try {
				KSublistInputStream[i] = new FileInputStream(filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	
}
