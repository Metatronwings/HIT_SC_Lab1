package P1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MagicSquare {
    public static void main(String[] args) {
        System.out.println(isLegalMagicSquare("1.txt"));
        System.out.println(isLegalMagicSquare("2.txt"));
        System.out.println(isLegalMagicSquare("3.txt"));
        System.out.println(isLegalMagicSquare("4.txt"));
        System.out.println(isLegalMagicSquare("5.txt"));

        System.out.println(generateMagicSquare(4));
        System.out.println(generateMagicSquare(-4));
        System.out.println(generateMagicSquare(3));
        System.out.println(generateMagicSquare(5));
    }

    private static boolean isLegalMagicSquare(String fileName) {
        Path fileDirectory = Paths.get("src/P1/txt");
        Path filePath = fileDirectory.resolve(fileName);

        String encoding = "UTF-8";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toFile()), encoding));
            String lineTxt = bufferedReader.readLine();

            String[] numsInALine = lineTxt.split("\t");
            int[][] numSquare = new int[numsInALine.length][numsInALine.length];
            int sum = 0;
            for (int i = 0; i < numsInALine.length; i++) {
                numSquare[0][i] = Integer.valueOf(numsInALine[i]);
                sum += Integer.valueOf(numsInALine[i]);
            }

            int cnt = 1;

            while ((lineTxt = bufferedReader.readLine()) != null) {
                numsInALine = lineTxt.split("\t");
                for (int i = 0; i <numsInALine.length; i++) {
                    numSquare[cnt][i] = Integer.valueOf(numsInALine[i]);
                }
                cnt++;
            }

            if (!testColumns(numSquare, sum)) {
                return false;
            }
            if (!testLines(numSquare, sum)) {
                return false;
            }
            if (!testDiagonal(numSquare, sum)) {
                return false;
            }
            if (!testAntiDiagonal(numSquare, sum)) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(fileName + " is not a square matrix.");
            return false;
        } catch (NumberFormatException e) {
            System.out.println(fileName + " has some invalid numbers or invalid separators.");
            return false;
        }

        return true;
    }

    private static boolean testLines(int[][] numSquare, int sum) {
        for (int i = 0; i < numSquare[0].length; i++) {
            int sumOfLine = 0;
            for (int j = 0; j < numSquare[0].length; j++) {
                sumOfLine += numSquare[i][j];
            }
            if (sumOfLine != sum) {
                return false;
            }
        }

        return true;
    }

    private static boolean testColumns(int[][] numSquare, int sum) {
        for (int i = 0; i < numSquare[0].length; i++) {
            int sumOfColumn = 0;
            for (int j = 0; j < numSquare[0].length; j++) {
                sumOfColumn += numSquare[j][i];
            }
            if (sumOfColumn != sum) {
                return false;
            }
        }

        return true;
    }

    private static boolean testDiagonal(int[][] numSquare, int sum) {
        for (int i = 0; i < numSquare[0].length; i++) {
            sum -= numSquare[i][i];
        }

        return sum == 0;
    }

    private static boolean testAntiDiagonal(int[][] numSquare, int sum) {
        for (int i = 0; i < numSquare[0].length; i++) {
            sum -= numSquare[numSquare.length-1-i][i];
        }

        return sum == 0;
    }

    private static boolean generateMagicSquare(int n) {
        try {
            int magic[][] = new int[n][n];
            int row = 0, col = n / 2, i, j, square = n * n;

            for (i = 1; i <= square; i++) {
                magic[row][col] = i;
                if (i % n == 0) {
                    row++;
                } else {
                    if (row == 0)
                        row = n - 1;
                    else
                        row--;
                    if (col == (n - 1))
                        col = 0;
                    else
                        col++;
                }
            }

            Path fileDirectory = Paths.get("src/P1/txt");
            Path filePath = fileDirectory.resolve("6.txt");

            if (!filePath.toFile().exists())
                Files.createFile(filePath);

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), StandardCharsets.UTF_8));

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    System.out.print(magic[i][j] + "\t");
                    writer.write(magic[i][j] + "\t");
                }
                writer.write("\n");
                System.out.println();
            }
            writer.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your input is even.");
            return false;
        } catch (NegativeArraySizeException e) {
            System.out.println("Your input is negative.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}

