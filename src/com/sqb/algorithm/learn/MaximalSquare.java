package com.sqb.algorithm.learn;

public class MaximalSquare {

	public static void main(String[] args) {
//		char[][] c = new char[4][5];
//		String s ="10100";
//		c[0] = s.toCharArray();
//		s = "10111";
//		c[1] = s.toCharArray();
//		s = "11111";
//		c[2] = s.toCharArray();
//		s = "10010";
//		c[3] = s.toCharArray();
//		char[][] c = new char[1][2];
//		String s= "01";
//		c[0] = s.toCharArray();
//		char[][] c = new char[3][4];
//		String s = "1111";
//		c[0] = s.toCharArray();
//		c[1] = s.toCharArray();
//		c[2] = s.toCharArray();
//		int a = maxSquareHeight(c, 0, 0);
//		char[][] c = new char[7][4];
//		String s = "0010";
//		int i = 0;
//		c[i++] = s.toCharArray();
//		s = "1111";
//		c[i++] = s.toCharArray();
//		s = "1111";
//		c[i++] = s.toCharArray();
//		s = "1110";
//		c[i++] = s.toCharArray();
//		s = "1100";
//		c[i++] = s.toCharArray();
//		s = "1111";
//		c[i++] = s.toCharArray();
//		s = "1110";
//		c[i++] = s.toCharArray();
//		int a = maxSquareHeight(c, 1, 0);
//		char[][] c = new char[6][6];
//		String s = "101101";
//		int i = 0;
//		c[i++] = s.toCharArray();
//		s = "111111";
//		c[i++] = s.toCharArray();
//		s = "011011";
//		c[i++] = s.toCharArray();
//		s = "111010";
//		c[i++] = s.toCharArray();
//		s = "011111";
//		c[i++] = s.toCharArray();
//		s = "110111";
//		c[i++] = s.toCharArray();
		
		char[][] c = new char[4][5];
		String s = "10100";
		int i = 0;
		c[i++] = s.toCharArray();
		s = "10111";
		c[i++] = s.toCharArray();
		s = "11111";
		c[i++] = s.toCharArray();
		s = "10010";
		c[i++] = s.toCharArray();
		int a = maximalSquare(c);
//		int a = maxSquareHeight(c, 4, 3);
		System.out.println(a);
	}
	
	// TODO
	public static int maximalSquare1(char[][] matrix) {
		int maxLen = 0; // 最长单边长
		int xMax = matrix.length;
		int yMax = matrix[0].length;
		for(int x = 0; x < xMax; x++) {
			for(int y = 0; y < yMax; y++) {
				char c = matrix[x][y];
				// TODO
				if(c == '1') {
					int sLen = 0; // 竖边
					int hLen = 0; // 横边
					int zLen = 0;
					for(int xt = x; xt < xMax; xt++) {
						for(int yt = y; yt < yMax; yt++) {
							char t = matrix[xt][yt];
							if(t != '1') {
								break;
							} else {
								hLen++;
							}
						}
						
					}
				}
				
				// 由于查询的是最大边长，故当剩余长度小于最长单边长时，可以不继续
				if(y + maxLen >= yMax) {
					break;
				}
			}
			// 由于查询的是最大边长，故当剩余长度小于最长单边长时，可以不继续
			if(x + maxLen >= xMax) {
				break;
			}
		}
		return 0;
    }
	
	public static int maximalSquare(char[][] matrix) {
		int max = 0;
		int xMax = matrix.length;
		if(xMax == 0) {
			return 0;
		}
		int yMax = matrix[0].length;
		if(yMax == 0) {
			return 0;
		}
		for(int x = 0; x < xMax; x++) {
			for(int y = 0; y < yMax; y++) {
				char c = matrix[x][y];
				if(c == '1') {
					int tmp = maxSquareHeight(matrix, x, y);
					max = tmp > max? tmp: max;
				}
			}
		}
		return max * max;
	}
	
	/**
	 * @param matrix
	 * @param x 纵
	 * @param y 横
	 * @return
	 */
	public static int maxSquareHeight(char[][] matrix, int x, int y) {
		int xMax = matrix.length;
		int yMax = matrix[0].length;
		if(x >= xMax) {
			return 0;
		}
		if(y >= yMax) {
			return 0;
		}
		
		// 
		int sLen = 1;
		int cxLen = 0;
		int max = 0;
		for(int x1 = x; x1 < xMax; x1++) {
			int cyLen = 0;
			for(int y1 = y; y1 < yMax; y1++) {
				char c = matrix[x1][y1];
				if(c != '1') {
					break;
				} else {
					cyLen++;
				}
			}
			if(cyLen == 0) {
				break;
			} else {
				cxLen++;
				if(cxLen == 1) {
					max = cyLen;
					sLen = cxLen;
					continue;
				}
				if(cyLen < max && cxLen <= max) {
					max = cyLen;
				}
				if(cxLen <= max ) {
					sLen = minThree(cxLen, cyLen, max);
				} else {
					break;
				}
			}
		}
		
		return sLen;
	}
	
	public static int minThree(int a, int b, int c) {
		if(a > b) {
			if(b > c) {
				return c;
			} else {
				return b;
			}
		} else {
			if(a > c) {
				return c;
			} else {
				return a;
			}
		}
	}
}
