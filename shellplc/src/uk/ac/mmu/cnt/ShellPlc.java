package uk.ac.mmu.cnt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellPlc  {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int nRows= 8;
		int nCols= 8;
		
		//tables
		double data [][]= new double[nRows][nCols];
		char map[][] = new char [nRows][nCols];
		BufferedReader console=
				new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the file name");
		String filename = console.readLine();
		try{ BufferedReader file= new BufferedReader (new FileReader(filename+".dat"));
		
		
		for(int i =0; i<nRows; i++){
			
				String row[] = file.readLine().split(" ");
				
				for (int j=0; j<nCols; j++ ){
					data[i][j]= Double.parseDouble(row[j]);
				}
		}
		file.close();
		}catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		
		for(int i=0; i<nRows; i++) {
			for(int j =0; j<nCols; j++){
				map[i][j]='o';
			}
		}
		
		for(int i =1; i<(nRows - 1); i++){
			for(int j=1; j<(nCols - 1); j++){
				
				double sum = data[i-1][j]+data[i][j-1]+
												data[i+1][j] + data[1][j+1] ;
				double average = sum/ 4;
				
				if(data[i][j] > average){
						map[i][j] = 'x';
				}
			}
		}
		
		for(int i =0; i<nRows; i++){
			for(int j = 0; j<nCols; j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();		}
		
	}

}
