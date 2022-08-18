package sudoku;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
	
		List<List<Integer>> permutations = permute(new int[] {1,2,3,4,5,6,7,8,9});
		
		System.out.println(permutations.size() + " permutations");
		long it=0;
		for(List<Integer> numbers : permutations)
		{
			int[][] problem = new int[3][3];
			problem[0][0] = numbers.get(0);
			problem[0][1] = numbers.get(1);
			problem[0][2] = numbers.get(2);

			problem[1][0] = numbers.get(3);
			problem[1][1] = numbers.get(4);
			problem[1][2] = numbers.get(5);

			problem[2][0] = numbers.get(6);
			problem[2][1] = numbers.get(7);
			problem[2][2] = numbers.get(8);

			System.out.print(it++ + ".");
			boolean ret = evaluate(problem);
			if (ret) {
				System.exit(0);
			} else {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static boolean evaluate(int[][] problem) {

		List<Integer> expectedSumResults = List.of(11, 13, 14, 21, 23);
	
		System.out.println("Analyzing:");
		int[] colSums = new int[3];
		int[] rowSums = new int[3];

		for (int row = 0; row < 3; row++) {
			int rowSum = 0;
			for (int col = 0; col < 3; col++) {
				System.out.print("|" + problem[row][col] + "| ");
				rowSum += problem[row][col];
				colSums[row] += problem[col][row];
			}
			System.out.println("=" + rowSum);
			rowSums[row] = rowSum;
		}

		for (int colSum : colSums) {
			System.out.print("=" + colSum + " ");
		}

		System.out.println("");

		Set<Integer> allSums = new HashSet();
		allSums.add(colSums[0]);
		allSums.add(colSums[1]);
		allSums.add(colSums[2]);
		allSums.add(rowSums[0]);
		allSums.add(rowSums[1]);
		allSums.add(rowSums[2]);

		if (allSums.containsAll(expectedSumResults)) {

			System.out.println("===> foundSums:" + allSums);
			return true;
		}

		return false;
	}
	
	
	public static List<List<Integer>> permute(int[] nums) {
	    List<List<Integer>> results = new ArrayList<List<Integer>>();
	    if (nums == null || nums.length == 0) {
	        return results;
	    }
	    List<Integer> result = new ArrayList<>();
	    dfs(nums, results, result);
	    return results;
	}

	public static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result) {
	    if (nums.length == result.size()) {
	        List<Integer> temp = new ArrayList<>(result);
	        results.add(temp);
	    }        
	    for (int i=0; i<nums.length; i++) {
	        if (!result.contains(nums[i])) {
	            result.add(nums[i]);
	            dfs(nums, results, result);
	            result.remove(result.size() - 1);
	        }
	    }
	}

}
