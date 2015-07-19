package set;

import java.util.Arrays;

public class Elements{
	
	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> sortList = new MaxSet<Integer>();
		for (int i =0;i<ints.length;i++){
			int e = ints[i];
			sortList.add(e);
			
			}
		
		int[] ints2 = new int[sortList.size()];
		
		while (sortList.size()>0){
			ints2[sortList.size()-1] =	sortList.getMax();
			sortList.remove(sortList.getMax());
		}
		
		return ints2;
			}

	public static void main(String[] args) {
		int[] ints = {34, 37, 34, 3, 2, 1, 89, 5, 0};
	System.out.println(Arrays.toString(uniqueElements(ints)));
		
		
		

	}

}
