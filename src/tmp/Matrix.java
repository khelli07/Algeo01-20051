package src.tmp;

import java.util.Scanner;

public class Matrix {
  static double valUndef = -999.0;
  static int idxUndef = -1;
  public double[][] contents;
  public int rows;
  public int cols;

  static Scanner sc = new Scanner(System.in);

  // ===========
  // CONSTRUCTOR AND BASIC FUNCTIONS
  // ===========

  Matrix(int rows, int cols) {
    this.contents = new double[rows][cols];
    this.rows = rows;
    this.cols = cols;
  }

  public void readMatrix() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        this.contents[i][j] = sc.nextDouble();
      }
    }
  }

  public void displayMatrix() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        System.out.print(this.contents[i][j] + " ");
      }
      System.out.println("");
    }
  }

  public void swapRow(int row1, int row2) {
    for (int j = 0; j < this.cols; j++) {
      double temp = this.contents[row1][j];
      this.contents[row1][j] = this.contents[row2][j];
      this.contents[row2][j] = temp;
    }
  }

  public int getIdxUtama(int row) {
    int col = 0;
    while (col < this.cols) {
      if (this.contents[row][col] != 0) {
        return col; // break :-(
      } else {
        col++;
      }
    }
    return idxUndef;
  }

  // ===========
  // COPY MATRIX
  // ===========

  public Matrix copyMatrix() {
    Matrix mOut = new Matrix(this.rows, this.cols);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        mOut.contents[i][j] = this.contents[i][j];
      }
    }
    return mOut;
  }

  public Matrix copyMatrixWithoutLC() {
    Matrix mOut = new Matrix(this.rows, this.cols - 1);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols - 1; j++) {
        mOut.contents[i][j] = this.contents[i][j];
      }
    }
    return mOut;
  }

  public Matrix copyMatrixLC() {
    Matrix mOut = new Matrix(this.rows, 1);
    for (int i = 0; i < this.rows; i++) {
      mOut.contents[i][0] = this.contents[i][this.cols - 1];
    }
    return mOut;
  }

  // ===========
  // DETERMINANT BY COFACTOR
  // ===========

  public double getMinorEntry(int row, int col) {
    Matrix minor = new Matrix(this.rows - 1, this.cols - 1);
    int rowIdx = 0;
    for (int i = 0; i < this.cols; i++) {
      int colIdx = 0;
      if (i != row) {
        for (int j = 0; j < this.rows; j++) {
          if (j != col) {
            minor.contents[rowIdx][colIdx] = this.contents[i][j];
            colIdx++;
          }
        }
        rowIdx++;
      }
    }
    return minor.getDeterminantByCofactor();
  }

  public double getDeterminantByCofactor() {
    if (this.rows == 1) {
      return this.contents[0][0];
    } else if (this.rows == 2) {
      return (this.contents[0][0] * this.contents[1][1]) - (this.contents[0][1] * this.contents[1][0]);
    } else {
      double determinant = 0;
      int sign = 1;
      // Akan digunakan ekspansi kofaktor pada baris pertama.
      for (int j = 0; j < this.cols; j++) { // Proses baris pertama matriks.
        determinant += this.contents[0][j] * this.getMinorEntry(0, j) * sign;
        sign *= -1;
      }
      return determinant;
    }
  }

  // ===========
  // INVERSE BY ADJOIN
  // ===========

  public Matrix createTransposeMatrix() {
    Matrix tMatrix = new Matrix(this.rows, this.cols);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        tMatrix.contents[i][j] = this.contents[j][i];
      }
    }
    return tMatrix;
  }

  public Matrix createAdjoinMatrix() {
    Matrix cMatrix = new Matrix(this.rows, this.cols);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.cols; j++) {
        cMatrix.contents[i][j] = this.getMinorEntry(i, j) * ((i + j) % 2 == 0 ? 1 : -1);
      }
    }
    return cMatrix.createTransposeMatrix();
  }

  public Matrix createInverseMatrix() {
    Matrix iMatrix = this.createAdjoinMatrix();
    double det = this.getDeterminantByCofactor();
    if (det != 0) {
      double mult = 1 / det;
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.cols; j++) {
          iMatrix.contents[i][j] *= mult;
        }
      }
    }
    return iMatrix;
  }

  // ===========
  // EQUATION SOLVING BY CRAMER'S RULE
  // ===========

  public double[] solveByCramerRule() {
    double[] retArray;
    retArray = new double[this.cols - 1];
    if (this.cols - 1 == this.rows) { // Hanya bisa dilakukan untuk matriks persegi
      Matrix squareMatrix = this.copyMatrixWithoutLC();
      double det = squareMatrix.getDeterminantByCofactor();
      if (det != 0) { // Apabila det = 0, tidak bisa diselesaikan dengan aturan Cramer
        for (int col = 0; col < this.cols - 1; col++) {
          Matrix cramerMatrix = this.copyMatrixWithoutLC();
          for (int row = 0; row < this.rows; row++) {
            cramerMatrix.contents[row][col] = this.contents[row][this.cols - 1];
          }
          double cramerDet = cramerMatrix.getDeterminantByCofactor();
          retArray[col] = cramerDet / det;
        }
      }
    }
    return retArray;
  }

  // ===========
  // EQUATION SOLVING BY INVERSE
  // ===========

  public Matrix getMatrixMultipliedBy(Matrix m) {
    Matrix mMatrix = new Matrix(this.rows, m.cols);
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < m.cols; j++) {
        double sum = 0;
        for (int k = 0; k < this.cols; k++) {
          sum += this.contents[i][k] * m.contents[k][j];
        }
        mMatrix.contents[i][j] = sum;
      }
    }
    return mMatrix;
  }

  public double[] solveByInverse() {
    double[] retArray;
    retArray = new double[this.cols - 1];
    Matrix coeffMatrix = this.copyMatrixWithoutLC();
    Matrix valMatrix = this.copyMatrixLC();
    Matrix resMatrix = coeffMatrix.createInverseMatrix().getMatrixMultipliedBy(valMatrix);
    for (int i = 0; i < resMatrix.rows; i++) {
      retArray[i] = resMatrix.contents[i][0];
    }
    return retArray;
  }

  // ==========
  // INTERPOLATION
  // ==========

  public void solveInterpolation(double x) {
    double[] retArray;
    Matrix eqMatrix = new Matrix(this.rows, this.rows + 1);
    for (int i = 0; i < this.rows; i++) { // Proses semua titik yang diberikan
      for (int j = 0; j < this.rows; j++) { // Buat matriks eqMatrix
        eqMatrix.contents[i][j] = Math.pow(this.contents[i][0], j);
      }
      eqMatrix.contents[i][this.rows] = this.contents[i][1];
    }
    retArray = eqMatrix.solveByCramerRule();
    double approx = 0;
    System.out.println("Persamaan interpolasinya adalah: ");
    for (int i = 0; i < retArray.length; i++) { // Proses retArray
      if (i == 0) { // Konstanta
        System.out.print(retArray[i]);
      } else if (i == 1) {
        System.out.print(" + " + retArray[i] + "x");
      } else {
        System.out.print(" + " + retArray[i] + "x^" + i);
      }
      approx += Math.pow(x, i) * retArray[i];
    }
    System.out.println("");
    System.out.println("Taksiran untuk titik x=" + x + " adalah " + approx);
  }

  // ==========
  // DOUBLE REGRESSION
  // ==========

  public void solveRegression(double[] xTaksiran) {
    double[] retArray;
    Matrix eqMatrix = new Matrix(this.cols, this.cols + 1);
    // Pengisian eqMatrix
    for (int i = 0; i < eqMatrix.rows; i++) {
      if (i == 0) {
        for (int j = 0; j < eqMatrix.cols; j++) {
          if (j == 0) {
            eqMatrix.contents[i][j] = (double) this.cols - 1;
          } else {
            double sum = 0;
            for (int k = 0; k < this.rows; k++) {
              sum += this.contents[k][j - 1];
            }
            eqMatrix.contents[i][j] = sum;
          }
        }
      } else {
        for (int j = 0; j < eqMatrix.cols; j++) {
          if (j == 0) {
            double sum = 0;
            for (int k = 0; k < this.rows; k++) {
              sum += this.contents[k][i - 1];
            }
            eqMatrix.contents[i][j] = sum;
          } else {
            double sum = 0;
            for (int k = 0; k < this.rows; k++) {
              sum += this.contents[k][i - 1] * this.contents[k][j - 1];
            }
            eqMatrix.contents[i][j] = sum;
          }
        }
      }
    }

    retArray = eqMatrix.solveByCramerRule();
    double approx = 0;
    System.out.println("Persamaan regresinya adalah: ");
    for (int i = 0; i < retArray.length; i++) {
      if (i == 0) {
        System.out.print("yi = " + retArray[i]);
        approx += retArray[i];
      } else {
        System.out.print(" + " + retArray[i] + "x" + i + "i");
        approx += retArray[i] * xTaksiran[i - 1];
      }
    }
    System.out.println(" + epsilon_i");
    System.out.print("Taksiran untuk");
    for (int i = 0; i < xTaksiran.length; i++) {
      System.out.print(" x" + (i + 1) + "=" + xTaksiran[i]);
    }
    System.out.println(" adalah " + approx);
  }
}