
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class SublistCreator {

	private long						m_availableMemory;
	private long						m_maxMemory;
	private	ArrayList<String>			m_bufferEntries;
	private QuickSort 					m_quicksort;
	private int							m_nSublists;
	private	long						m_memoryOffset;
	private String						m_fileName;
	
	public SublistCreator(int memorySize) {
		
		m_quicksort = new QuickSort();
		m_bufferEntries = new ArrayList<String>();
		m_maxMemory = getAvailableMemory();
		m_availableMemory = m_maxMemory;
		m_nSublists = 0;
		m_memoryOffset = (long) (m_maxMemory/2.0);

	}
	
	
	private long getAvailableMemory(){
		System.gc();
		Runtime runtime = Runtime.getRuntime();
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		return runtime.maxMemory() - usedMemory;
	}
	
	
	private String readEntry(BufferedReader targetFile, int entrySize) {
				
		String buffer = "";
		
		try {
			buffer = targetFile.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		return buffer;
	}
	
	
	private void writeSublist(BufferedWriter output) {
		
		int i;
		for (i = 0; i < m_bufferEntries.size(); i++) {
			
			try {
				output.write(m_bufferEntries.get(i) + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
	
	public void createSublistsEmployees() {
		createSublist(100, "Employees2");
		
	}
	
	public void createSublistProjects() {
		createSublist(60, "Projects2");
		
	}
	
	
	BufferedReader open(String filePath) {
		
		//int bufferSize = (int) Math.floor(m_memory / 2.0);
		
		FileReader input = null;
		try {
			input = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BufferedReader bufferedReader = new BufferedReader(input);
		return bufferedReader;
		
	}	

	private void createSublist(int entrySize, String fileName) {
				
		BufferedReader input = open(fileName + ".txt");
		
		m_fileName = fileName;
		
		while(true) {
		
			if ((m_availableMemory >= m_memoryOffset)) {
				
				//System.out.println("memory left: " + m_availableMemory);
				String entry = readEntry(input, entrySize);
				
				
				
				//System.out.println("entry:" + entry);
				
				if (entry == null) {
					produce(fileName);
					m_bufferEntries = null;
					return;
				}
				
				entrySize = entry.length();
				
				m_bufferEntries.add(entry);
				m_availableMemory -= entrySize * 2 + 48;
			}
			
			else {
				produce(fileName);
				m_availableMemory = m_maxMemory;
				m_bufferEntries = new ArrayList<String>();
			}
			
		}	
			
		
	}
	
	private long qsBenchmark;
	
	private void produce(String fileName) {
		
		if (m_bufferEntries.isEmpty()) {
			return;
		}
		
		// Sort
		m_quicksort.sort(m_bufferEntries, 0, m_bufferEntries.size()-1);
		

		
		// Create sublist.
		
		FileWriter sublistFileWriter = null;
		
		try {
			sublistFileWriter = new FileWriter(fileName + "_sl_" + m_nSublists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		BufferedWriter output = new BufferedWriter(sublistFileWriter);
		
		writeSublist(output);
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_nSublists++;
		
	}
	
	
	public int getNumberOfSublists() {
		return m_nSublists;
	}
	
	public void deleteSublists() {
		int i;
		for (i = 0; i < m_nSublists; i++) {
						
			File fileToDelete = new File("./" + m_fileName + "_sl_" + i);
			fileToDelete.delete();
			
		}
	}
	
	
}
