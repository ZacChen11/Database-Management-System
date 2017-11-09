
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Phase2 {
	
	private String sortedEmployeesFileName = "sortedEmployees.txt";
	private String sortedProjectsFileName = "sortedProjects.txt";
	
	private BufferedWriter		m_output;
	
	public Phase2() {
				
		FileWriter fileWriter = null;
				
		String filePath = "./joinedEmployeesProjects.txt";
		
		try {
			fileWriter = new FileWriter(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		m_output = new BufferedWriter(fileWriter);
		
				
	}
	
	
	BufferedReader open(String filePath) {
				
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
	
	void output(String string) {
		
		try {
			
			m_output.write(string + '\n');
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	void start() {
		
		BufferedReader sortedEmployeesBR = open(sortedEmployeesFileName);
		BufferedReader sortedProjectsBR =  open(sortedProjectsFileName);
				
		String projectEntryString = null;
		String employeeEntryString = null;
		
		try {
			projectEntryString = sortedEmployeesBR.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while ((employeeEntryString = sortedEmployeesBR.readLine()) != null) {
				
				while (true) {
								
					if (projectEntryString == null) {
						// EOF reached in project file; we are done.
						m_output.close();
						sortedEmployeesBR.close();
						sortedProjectsBR.close();
						
						File sortedEmployeeFileToDelete = new File("./" + sortedEmployeesFileName);
						File sortedProjectsFileToDelete = new File("./" + sortedProjectsFileName);
						
						sortedEmployeeFileToDelete.delete();
						sortedProjectsFileToDelete.delete();
						
						return;
					}
					
					Entry employeeEntry = new Entry(employeeEntryString);
					Entry projectEntry = new Entry(projectEntryString);
					
					int empId = employeeEntry.getId();
					int prjId = projectEntry.getId();
					
					if (empId > prjId) {
						
						// See if the next entry of project will give us a match with current empoyee entry.
						projectEntryString = sortedProjectsBR.readLine();
						continue;
						
					}
					
					if (empId == prjId) {
						
						// Match found, output joined entries.
						String joined = employeeEntry.join(projectEntry);
						
						// Output entry.
						output(joined);
						
						// Buffer up next line in project file.
						projectEntryString = sortedProjectsBR.readLine();
						continue;
						
					}
					
					if (empId < prjId) {
						// We need to read the next employee entry as we won't be able to have a match with this current employee entry.
						break;
						
					}
										
				}
			}
						
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
