
public class Phase1 {

	int m_memory = 220;
	
	void start() {
		
		long time1 = System.currentTimeMillis();
		
		SublistCreator scEmployees = new SublistCreator(m_memory);
		SublistCreator scProjects = new SublistCreator(m_memory);

		scEmployees.createSublistsEmployees();
		scProjects.createSublistProjects();

		KWayMerger kmEmployees = new KWayMerger();

		kmEmployees.mergeSublistsEmployees(scEmployees);

		kmEmployees = new KWayMerger();

		kmEmployees.mergeSublistProjects(scProjects);
		
		scEmployees.deleteSublists();
		scProjects.deleteSublists();
		
		long time2 = System.currentTimeMillis();

		System.out.println("run time for phase1 is :" + (time2 - time1)+"ms");
		
	}
	
}
