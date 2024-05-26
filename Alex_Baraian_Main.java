import java.io.*;

public class Alex_Baraian_Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		File inFile = new File(args[0]);	//Step 0
		File SSSfile = new File(args[1]);
		File deBugFile = new File(args[2]);
		Alex_Baraian_DijktraSSS thing = new Alex_Baraian_DijktraSSS();
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(SSSfile));
		
		thing.numNodes= Integer.parseInt(reader.readLine());
		thing.best= new int[thing.numNodes+1];
		thing.father = new int[thing.numNodes + 1];
		thing.costMatrix = new int[thing.numNodes+1][thing.numNodes+1];
		thing.loadCostMatrix(inFile);	//Step 1
		thing.sourceNode = 1;
		writer.write("There are "+thing.numNodes+" nodes in the input graph. Below are all pairs of the shortest paths:\n\n");
		writer.flush();
		for(;thing.sourceNode<=thing.numNodes;thing.sourceNode++) {
		thing.setBest();	//Step 2
		thing.setFather();
		thing.setToDo();
		
	while(thing.doneToDo()!=true) {	//Step 8
			thing.minNode = thing.findMinNode(); //Step 3
			thing.toDo[thing.minNode]=0;
			thing.debugPrint(deBugFile);
			thing.currentNode =1;	//Step 4
			
			for(;thing.currentNode<=thing.numNodes;thing.currentNode++) {	//Step 6 & 7
				if(thing.toDo[thing.currentNode]>0) {	//Step 5
					int newcost = thing.computCost();
					if(newcost<thing.best[thing.currentNode]) {
						thing.best[thing.currentNode]=newcost;
						thing.father[thing.currentNode]= thing.minNode;
						thing.debugPrint(deBugFile);
					}
				}
			}
			
			
		}
		thing.currentNode = 1; //Step 9
		thing.printShortestPath(SSSfile); //Step 10
		}
		writer.close();
		reader.close();
		
	}
}