import java.util.Random;

class Main {
    public static void main(String[] args) throws Exception {
        int size1 = 1100;
        int size2 = 1000;

        UsualMatrix matrix1 = new UsualMatrix(size1, size2);
        Random rnd = new Random();
        for (int i = 0; i < size1; i++)
            for (int j = 0; j < size2; j++){
                matrix1.setElement(i,j, rnd.nextInt(10));
            }


        UsualMatrix matrix2 = new UsualMatrix(size2, size1);
        for (int i = 0; i < size2; i++)
            for (int j = 0; j < size1; j++){
                matrix2.setElement(i, j, rnd.nextInt(10));
            }

        long start = System.nanoTime();
        ParalleMatrixProduct pmp = new ParalleMatrixProduct(32);
        UsualMatrix ParallelMatrixTest = pmp.product(matrix1, matrix2);
        long finish = System.nanoTime();
        float time= (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Time multithreaded: " + time + "s");

        start = System.nanoTime();
        UsualMatrix result = matrix1.product(matrix2);
        finish = System.nanoTime();
        time= (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Time onethreaded: " + time + "s");
        System.out.println(result.equals(ParallelMatrixTest));
    }
}
