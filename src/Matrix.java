package src;

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
        // Menghindari signed zero :-(
        if (this.contents[i][j] == 0) {
          System.out.print(0.0 + " ");
        } else {
          System.out.print(this.contents[i][j] + " ");
        }
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
  // MATRIX MANIPULATIONS
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
  // DETERMINANT BY GAUSS REDUCTION
  // ===========

  public double getDeterminantByOBE() {
    Matrix mat = this.copyMatrix();
    for (int k = 1; k < mat.rows; k++) {
      for (int i = k; i < mat.rows; i++) {
        double p = mat.contents[i][k - 1];
        double q = mat.contents[k - 1][k - 1];

        if (q != 0) {
          for (int j = 0; j < mat.rows; j++) {
            mat.contents[i][j] = mat.contents[i][j] - (p / q) * (mat.contents[k - 1][j]);
          }
        }
      }
    }

    double det = 1;
    for (int i = 0; i < mat.rows; i++) {
      det *= mat.contents[i][i];
    }
    return det;
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
  // INVERSE BY GAUSS JORDAN
  // ===========

  public boolean isInvertible() {
    boolean matrixInvertible = true;
    int i = 0;
    while (matrixInvertible && i < this.rows) {
      // Kalau ada yang 0 semua di kiri, maka salah
      if (this.getIdxUtama(i) == this.rows) {
        matrixInvertible = false;
      }
      i++;
    }
    return matrixInvertible;
  }

  public Matrix copyWithIdentity() {
    Matrix mOut = new Matrix(this.rows, this.cols * 2);
    for (int i = 0; i < mOut.rows; i++) {
      for (int j = 0; j < this.rows; j++) {
        mOut.contents[i][j] = this.contents[i][j];
      }

      for (int j = this.rows; j < mOut.cols; j++) {
        if (j == i + this.rows) {
          mOut.contents[i][j] = 1;
        } else {
          mOut.contents[i][j] = 0;
        }
      }
    }

    return mOut;
  }

  public Matrix getInverse() {
    int cols = this.cols / 2;
    Matrix mOut = new Matrix(this.rows, cols);

    for (int i = 0; i < this.rows; i++) {
      for (int j = cols; j < this.cols; j++) {
        mOut.contents[i][j - cols] = this.contents[i][j];
      }
    }

    return mOut;
  }

  public void inverseByOBE() {
    Matrix matWithIdentity = this.copyWithIdentity();
    matWithIdentity.gElimination();

    if (matWithIdentity.isInvertible()) {
      matWithIdentity.jElimination();

      Matrix matInverse = matWithIdentity.getInverse();
      matInverse.displayMatrix();
    } else {
      System.out.println("Matriks tidak memiliki balikan.");
    }
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

  // ===========
  // GAUSSIAN ELIMINATION
  // ===========

  // --------- lEONIE ----------
  public Matrix gaussianElimination() {
    Matrix m = this.copyMatrix();
    int row = 0;
    for (int col = 0; col < this.cols; col++) {
      if (row < m.rows) {
        // Mencari nilai maksimum dari kolom
        double maxVal = m.contents[row][col];
        int maxIdx = row;
        for (int i = row + 1; i < m.rows; i++) {
          if (m.contents[i][col] > maxVal) {
            maxVal = m.contents[i][col];
            maxIdx = i;
          }
        }
        // Tukar baris yang mengandung nilai maksimum ke baris awal
        if (maxIdx != row) {
          m.swapRow(row, maxIdx);
        }
        if (m.contents[row][col] != 0) {
          // Ubah nilai tidak nol pertama baris awal menjadi 1
          double x = m.contents[row][col];
          for (int i = col; i < m.cols; i++) {
            m.contents[row][i] /= x;
          }
          // Ubah nilai di bawah elemen pada baris row dan kolom col menjadi 0
          for (int i = row + 1; i < m.rows; i++) {
            if (m.contents[i][col] != 0) {
              x = m.contents[i][col];
              for (int j = col; j < m.cols; j++) {
                m.contents[i][j] -= x * m.contents[row][j];
              }
            }
          }
          row += 1;
        }
      }
    }
    return m;
  }

  public Matrix gaussJordanElimination() {
    Matrix m = this.gaussianElimination();
    for (int row = 1; row < m.rows; row++) {
      // Cari indeks kolom yang mengandung nilai 1 pada matriks
      boolean isFound = false;
      int col = 0;
      while (col < (m.cols - 1) && !(isFound)) {
        if (m.contents[row][col] == 1) {
          isFound = true;
        } else {
          col += 1;
        }
      }
      if (isFound) {
        // Ubah nilai di atas elemen pada baris row dan kolom col menjadi 0
        for (int i = (row - 1); i >= 0; i--) {
          if (m.contents[i][col] != 0) {
            double x = m.contents[i][col];
            for (int j = col; j < m.cols; j++) {
              m.contents[i][j] -= x * m.contents[row][j];
            }
          }
        }
      }
    }
    return m;
  }

  // ---------- KHELLI ----------
  public void gElimination() {
    // GAUSS ELIMINATION
    for (int k = 0; k < this.rows; k++) {
      // Swap baris terlebih dahulu
      int minIdx = k;
      for (int row = k; row < this.rows; row++) {
        if (this.getIdxUtama(row) == -1) {
          minIdx = row;
        } else if (this.getIdxUtama(row) < this.getIdxUtama(minIdx)) {
          minIdx = row;
        }
      }
      this.swapRow(k, minIdx);

      if (this.getIdxUtama(k) == -1) {
        this.swapRow(k, this.rows - 1);
        // Yang 0 semua dipindahkan ke bawah sekali
      }

      // Baris yang harus dibagi untuk mendapat 1 utama
      int idxUtama = this.getIdxUtama(k);
      if (idxUtama != -1) {
        double divisor = this.contents[k][idxUtama];
        for (int col = 0; col < this.cols; col++) {
          this.contents[k][col] = this.contents[k][col] / divisor;

        }

        // Reduksi baris di bawah 1 utama
        for (int i = k + 1; i < this.rows; i++) {
          double multiplier = this.contents[i][idxUtama];
          for (int j = 0; j < this.cols; j++) {
            this.contents[i][j] -= multiplier * this.contents[k][j];
          }
        }
      }
    }
  }

  public void jElimination() {
    // JORDAN ELIMINATION
    // Harus lakukan gauss dulu ya ;)
    for (int k = this.rows - 1; k > -1; k--) {
      int idxUtama = this.getIdxUtama(k);

      // Reduksi baris di atas 1 utama
      for (int i = k - 1; i > -1; i--) {
        double multiplier = this.contents[i][idxUtama];
        for (int j = 0; j < this.cols; j++) {
          this.contents[i][j] -= multiplier * this.contents[k][j];
        }
      }
    }
  }

  // ===========
  // EQUATION SOLVING BY GAUSS JORDAN
  // ===========

  // GAUSS
  public void solveByGauss() {
    Matrix mat = this.copyMatrix();
    mat.gElimination();

    // Hitung solusi -> sebanyak kolom - 1
    int lastRow = mat.rows - 1;
    int lastCol = mat.cols - 1;
    int idxUtama;

    // Cek apakah punya solusi atau tidak
    boolean matrixSolvable = true;
    for (int i = lastRow; i > -1; i--) {
      // Kalau ada yang elemen utamanya di daerah paling kanan,
      // maka tidak solveable.
      if (mat.getIdxUtama(i) == lastCol) {
        matrixSolvable = false;
      }
    }

    if (matrixSolvable) {
      Matrix matSols = new Matrix(mat.cols - 1, mat.cols);

      for (int i = 0; i < matSols.rows; i++) {
        for (int j = 0; j < matSols.cols; j++) {
          matSols.contents[i][j] = 0;
        }
      }

      for (int j = 0; j < matSols.cols - 1; j++) {
        matSols.contents[j][0] = valUndef;
      }

      for (int i = lastRow; i > -1; i--) {
        idxUtama = mat.getIdxUtama(i);
        if (idxUtama != -1) {
          // Inisialisasi Koefisien
          for (int col = idxUtama + 2; col < matSols.cols; col++) {
            matSols.contents[idxUtama][col] = -1 * mat.contents[i][col - 1];
          }

          // Inisialisasi Konstanta
          matSols.contents[idxUtama][0] = mat.contents[i][lastCol];

          // Operasi
          for (int j = 1; j < matSols.cols; j++) {
            double coeff = matSols.contents[idxUtama][j];
            for (int col = 0; col < matSols.cols; col++) {
              if (matSols.contents[j - 1][col] != valUndef) {
                matSols.contents[idxUtama][col] += coeff * matSols.contents[j - 1][col];
              }
            }
            if (matSols.contents[j - 1][0] != valUndef) {
              matSols.contents[idxUtama][j] = 0;
            }
          }

        }
      }

      // I/O Field
      for (int i = 0; i < matSols.rows; i++) {
        boolean isFirst = true;
        System.out.printf("x%d = ", i + 1);
        double constant = matSols.contents[i][0];

        int asciiCounter = 97;
        if (constant != valUndef && constant != 0) {
          System.out.printf("%f", constant);
          isFirst = false;
        }
        if (constant == valUndef) {
          System.out.printf("" + (char) (asciiCounter + i));
          isFirst = false;
        }
        for (int j = 1; j < matSols.cols; j++) {
          if (matSols.contents[i][j] < 0) {
            if (!isFirst) {
              System.out.printf(" - " + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
            } else {
              System.out.printf("-" + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
              isFirst = false;
            }
          } else if (matSols.contents[i][j] > 0) {
            if (!isFirst) {
              System.out.printf(" + " + matSols.contents[i][j] + (char) (asciiCounter));
            } else {
              System.out.printf("" + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
              isFirst = false;
            }
          } else {
            System.out.printf("");
          }
          asciiCounter++;
        }
        System.out.println();
      }

    } else {
      System.out.println("SPL tidak memiliki solusi.");
    }
  }

  // GAUSS JORDAN
  public String[] solveByGaussJordan() {
    Matrix m = this.gaussJordanElimination();
    String[] retArray;
    retArray = new String[m.cols - 1];
    String[] var = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z" };
    String[] varArray;
    varArray = new String[m.cols - 1];
    double[] valArray;
    valArray = new double[m.cols - 1];
    for (int i = 0; i < m.cols - 1; i++) {
      varArray[i] = var[i];
      valArray[i] = -999;
    }

    boolean flag = true; // true ketika spl memiliki solusi
    int row = m.rows - 1;
    int col;
    boolean isFound;
    double cons;

    while (row >= 0 && flag) {
      isFound = false;
      col = 0;
      while (col < m.cols && flag && !(isFound)) {
        if (m.contents[row][col] != 0) {
          isFound = true;
          if (col == m.cols - 1) {
            flag = false;
          } else {
            valArray[col] = m.contents[row][m.cols - 1];
            varArray[col] = ""; //
            for (int i = col + 1; i < m.cols - 1; i++) { // loop utk mengecek nilai elemen pada kolom berikutnya
              if (m.contents[row][i] != 0) {
                if (valArray[i] != -999) { // jika variabel memiliki nilai
                  valArray[col] -= m.contents[row][i] * valArray[i];
                } else { // jika variabel tidak memiliki nilai
                  cons = -1 * m.contents[row][i];
                  if (varArray[col] == "") {
                    if (cons > 0) {
                      if (cons == 1) {
                        varArray[col] = varArray[i];
                      } else if (cons == -1) {
                        varArray[col] = "-" + varArray[i];
                      } else {
                        varArray[col] = cons + varArray[i];
                      }
                    } else {
                      varArray[col] = "-" + m.contents[row][i] + varArray[i];
                    }
                  } else {
                    if (cons > 0) {
                      if (cons == 1) {
                        varArray[col] = varArray[col] + " + " + varArray[i];
                      } else if (cons == -1) {
                        varArray[col] = varArray[col] + " - " + varArray[i];
                      } else {
                        varArray[col] = varArray[col] + " + " + cons + varArray[i];
                      }
                    } else {
                      varArray[col] = varArray[col] + " - " + m.contents[row][i] + varArray[i];
                    }
                  }
                }
              }
            }
          }
        }
        col += 1;
      }
      row -= 1;
    }
    if (flag) {
      for (int i = 0; i < m.cols - 1; i++) { // menyatukan varArray dan valArray
        if (varArray[i] == "") {
          if (valArray[i] != -999) {
            retArray[i] = "" + valArray[i];
          }
        } else {
          if (valArray[i] != -999 && valArray[i] != 0) {
            if (valArray[i] > 0) {
              retArray[i] = varArray[i] + " + " + valArray[i];
            } else {
              retArray[i] = varArray[i] + " - " + (-1 * valArray[i]);
            }
          } else {
            retArray[i] = varArray[i];
          }
        }
      }
    } else {
      retArray[0] = "false";
    }
    return retArray;
  }

  // ==========
  // INTERPOLATION
  // ==========

  public void solveInterpolation(double x) {
    String[] retArray;
    Matrix eqMatrix = new Matrix(this.rows, this.rows + 1);
    for (int i = 0; i < this.rows; i++) { // Proses semua titik yang diberikan
      for (int j = 0; j < this.rows; j++) { // Buat matriks eqMatrix
        eqMatrix.contents[i][j] = Math.pow(this.contents[i][0], j);
      }
      eqMatrix.contents[i][this.rows] = this.contents[i][1];
    }
    retArray = eqMatrix.solveByGaussJordan();
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
      approx += Math.pow(x, i) * Double.parseDouble(retArray[i]);
    }
    System.out.println("");
    System.out.println("Taksiran untuk titik x=" + x + " adalah " + approx);
  }

  // ==========
  // DOUBLE REGRESSION
  // ==========

  public void solveRegression(double[] xTaksiran) {
    String[] retArray;
    Matrix eqMatrix = new Matrix(this.cols, this.cols + 1);
    // Pengisian eqMatrix
    for (int i = 0; i < eqMatrix.rows; i++) {
      if (i == 0) {
        for (int j = 0; j < eqMatrix.cols; j++) {
          if (j == 0) {
            eqMatrix.contents[i][j] = (double) this.rows;
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

    eqMatrix.displayMatrix();

    retArray = eqMatrix.solveByGaussJordan();
    double approx = 0;
    System.out.println("Persamaan regresinya adalah: ");
    for (int i = 0; i < retArray.length; i++) {
      if (i == 0) {
        System.out.print("yi = " + retArray[i]);
        approx += Double.parseDouble(retArray[i]);
      } else {
        System.out.print(" + " + retArray[i] + "x" + i + "i");
        approx += Double.parseDouble(retArray[i]) * xTaksiran[i - 1];
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