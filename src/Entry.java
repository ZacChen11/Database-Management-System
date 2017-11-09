
import java.io.IOException;
import java.io.OutputStream;


public class Entry {

	int m_id;
	String m_fullTuple;
	
	public Entry(String tuple) {
		
		String stringId = tuple.substring(0, 7);
		m_id = Integer.parseInt(stringId);
		
		m_fullTuple = tuple;
		
	}
	
	public int getId() {
		return m_id;
	}
	
	public String getTuple() {
		return m_fullTuple;
	}
	
	public void write(OutputStream output) {
		
		String toWrite = m_fullTuple + '\n';
		
		try {
			output.write(toWrite.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String join(Entry entry) {
		
		String joined = m_fullTuple + entry.getTuple().substring(7);
		return joined;
		
	}
	
	
}
