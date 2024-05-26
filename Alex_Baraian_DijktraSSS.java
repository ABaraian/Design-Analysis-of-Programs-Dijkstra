import java.io.*;


public class Alex_Baraian_DijktraSSS {
	int numNodes;
	int sourceNode;
	int minNode;
	int currentNode;
	int newCost;
	int costMatrix[][];
	int father[];
	int toDo[];
	int best[];
	
	void loadCostMatrix(File inFile) throws IOException {
		
		for(int i=1;i<numNodes+1;i++) {
			for(int j=1;j<numNodes+1;j++) {
				if(i==j) {
					costMatrix[i][j]=0;
				}
				else {
					costMatrix[i][j]=999;
				}
			}
		}
		BufferedReader reader = new BufferedReader(new FileReader (inFile));
		reader.readLine();
		String line;
		int n1, n2, cost;
		while((line=reader.readLine())!=null) {
			String words[] = line.split("\\s");
			n1 = Integer.parseInt(words[0]);
			n2 = Integer.parseInt(words[1]);
			cost = Integer.parseInt(words[3]);
			costMatrix[n1][n2]= cost;
		}
		reader.close();
	}
	void setBest() {
		for(int i = 1; i<numNodes+1;i++) {
			best[i]= costMatrix[sourceNode][i];
		}
	}
	void setFather() {
		
		for(int i =1;i<numNodes+1;i++) {
			father[i]=sourceNode;
		}
	}
	void setToDo() {
		toDo = new int[numNodes+1];
		toDo[sourceNode]=0;
		for(int i =1; i<numNodes+1;i++) {
			toDo[i]=1;
		}
	}
	int findMinNode() {
		int minCost = 99999;
		int index =1;
		minNode=0;
		for(;index<=numNodes;index++) {
			if(toDo[index]>0) {
				if(best[index]<minCost) {
					minCost = best[index];
					minNode = index;
				}
			}
		}
		return minNode;
	}
	int computCost() {
		return best[minNode]+costMatrix[minNode][currentNode];
	}
	void debugPrint(File deBugFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(deBugFile,true));
		writer.write("The Source Node Is: " + sourceNode +"\n\n");
		writer.write("The Father Array:\n");
		for(int i =0;i<=numNodes;i++) {
			writer.write(father[i] + " ");
		}
		writer.write("\n\n");
		writer.write("The Best Array:\n");
		for(int i =0;i<=numNodes;i++) {
			writer.write(best[i] + " ");
		}
		writer.write("\n\n");
		writer.write("The toDo Array:\n");
		for(int i =0;i<=numNodes;i++) {
			writer.write(toDo[i] + " ");
		}
		writer.write("\n\n");
		writer.close();
	}
	boolean doneToDo() {
		for(int i = 1; i<numNodes+1;i++) {
			if(toDo[i]==1) {
				return false;
			}
		}
		return true;
	}
	
	void printShortestPath(File SSSfile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(SSSfile,true));
		writer.write("Source node = "+sourceNode+"\n\n");
		for(; currentNode<=numNodes;currentNode++) {
			writer.write("The path from "+ sourceNode +" to "+ currentNode+" : ");
		int cur= currentNode;
		while(cur!=sourceNode) {
				writer.write(cur + "<-" );	
			cur=father[cur];
		}
		writer.write(cur + ": cost = "+best[currentNode]+"\n");
		}
		writer.write("\n\n");
		writer.close();
	}
}
