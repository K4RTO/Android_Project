package com.example.ga_23s2.model.postService;

/**
 * Calculation of Damerau–Levenshtein distance
 *
 * @author Huanjie Zhang
 */
public class StringSimilarity {

  private final String a;
  private final String b;
  private int[][] matrix;
  private final int MAX_ROW;
  private final int MAX_COLUMN;

  public StringSimilarity(String a, String b) {
    this.a = a;
    this.b = b;
    this.MAX_ROW = a.length() + 1;
    this.MAX_COLUMN = b.length() + 1;
    this.matrix = new int[MAX_ROW][MAX_COLUMN];
    calDistance();
  }

  private void calDistance() {
    // matrix[0][0] = 0; // by definition
    for (int row = 1; row < MAX_ROW; row++) matrix[row][0] = row;
    for (int col = 1; col < MAX_COLUMN; col++) matrix[0][col] = col;
    //
    for (int row = 1; row < MAX_ROW; row++) {
      for (int col = 1; col < MAX_COLUMN; col++) {
        int one = a.charAt(row - 1) == b.charAt(col - 1) ? 0 : 1;
        int result =
            min(matrix[row][col - 1] + 1, matrix[row - 1][col] + 1, matrix[row - 1][col - 1] + one);
        if (row > 1
            && col > 1
            && a.charAt(row - 1) == b.charAt(col - 2)
            && a.charAt(row - 2) == b.charAt(col - 1))
          result = min(result, matrix[row - 2][col - 2] + one);
        matrix[row][col] = result;
      }
    }
  }

  private int min(int a1, int... an) {
    int result = a1;
    for (int value : an) if (value < result) result = value;
    return result;
  }

  public int getDistance() {
    return matrix[MAX_ROW - 1][MAX_COLUMN - 1];
  }

  public double getSimilarity() {
    return 1 - ((double) getDistance()) / Math.max(a.length(), b.length());
  }
}
