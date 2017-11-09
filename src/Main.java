
import java.io.IOException;


public class Main {

	
	private static long getAvailableMemory(){
		System.gc();
		Runtime runtime = Runtime.getRuntime();
		System.out.println("memoryMax: " + runtime.maxMemory());
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		return runtime.maxMemory() - usedMemory;
	}

	
	public static void  main(String args[]) throws IOException {

		long time1 = System.currentTimeMillis();

		Phase1 phase1 = new Phase1();
		phase1.start();
		
		Phase2 phase2 = new Phase2();
		phase2.start();

		long time2 = System.currentTimeMillis();

		System.out.println("overall run time is :" + (time2 - time1)+"ms");
	}
	
}
