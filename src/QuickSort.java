
import java.util.ArrayList;
import java.util.Collections;

public class QuickSort {

	private int getInteger(ArrayList<String> values, int index) {
		return Integer.parseInt(((String) values.get(index).subSequence(0, 7)).trim());
	}
	
	public void sort(ArrayList<String> value, int start, int end){
		if(start < end){
			int p = partition(value, start, end);
			sort(value, p+1, end);
			sort(value, start, p-1);
		}
	}

	private int partition(ArrayList<String> value, int start, int end) {
		
		int pivot = getInteger(value, end);
		int i = start;
		for (int j = start; j < end; j++) {
			if(getInteger(value, j) < pivot){
				Collections.swap(value, i, j);
				i++;
			}
		}
		Collections.swap(value, i, end);
		return i;
	}
}