
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Xingjian on 2017-02-22.
 */
public class MinHeap {

    private BufferedReader[] readers;
    private int size;
    private String[] heap;
//    public static long totalTime = 0;

    public MinHeap(InputStream[] inputs) {
        readers = new BufferedReader[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            this.readers[i] = new BufferedReader(new InputStreamReader(inputs[i]));
        }
        this.size = inputs.length;
        heap = new String[this.size];
        init();
        buildHeap();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            heap[i] = load(i);
        }
    }

    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            keepMin(i);
        }
    }

    private String load(int idx) {
        String str = readEntry(idx);
        if (null == str) return null;
        return idx + "$" + str;
    }

    public String poll() {
    	if (size == 0){
    		return null;
    	}
        String res = heap[0];
        int dollarIdx = res.indexOf("$");
        int idx = Integer.parseInt(res.substring(0, dollarIdx));
        String in = load(idx);
        if (null == in) {
            swap(size - 1, 0);
            size--;
            keepMin(0);
        } else {
            heap[0] = in;
            keepMin(0);
        }
        return res.substring(dollarIdx + 1);
    }

    private void keepMin(int k) {
        int min = k;
        int left = 2 * k + 1;
        int right = left + 1;
        if (left < size && getEmpId(heap[left]) < getEmpId(heap[k])) {
            min = left;
        }
        if (right < size && getEmpId(heap[right]) < getEmpId(heap[min])) {
            min = right;
        }
        if (min != k) {
            swap(min, k);
            keepMin(min);
        }
    }

    private void swap(int x, int y) {
        String tmp = heap[x];
        heap[x] = heap[y];
        heap[y] = tmp;
    }

    private int getEmpId(String s) {
        int idx = s.indexOf("$");
        return Integer.parseInt(s.substring(idx + 1, idx + 8));
    }


    private String readEntry(int idx) {
//        long time1 = System.currentTimeMillis();

        BufferedReader br = readers[idx];
        String buffer = null;
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        long time2 = System.currentTimeMillis();
//        totalTime += time2 - time1;
        return buffer;
    }
}
