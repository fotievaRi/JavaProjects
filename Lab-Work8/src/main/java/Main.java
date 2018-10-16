import java.util.Random;

class Main {
    public static void main(String[] args) throws Exception {
        int size1 = 2;
        int size2 = 3;

        UsualMatrix matrix1 = new UsualMatrix(size1, size2);
        Random rnd = new Random();
        int c= 1;
        for (int i = 0; i < size1; i++)
            for (int j = 0; j < size2; j++){
                matrix1.setElement(i, j, c);
                c++;
            }
        c=1;

        UsualMatrix matrix2 = new UsualMatrix(size2, size1);
        for (int i = 0; i < size2; i++)
            for (int j = 0; j < size1; j++){
                matrix2.setElement(i, j, c);
                c++;
            }
        long start = System.nanoTime();
        ParalleMatrixProduct pmp = new ParalleMatrixProduct(32);
        UsualMatrix ParallelMatrixTest = pmp.product(matrix1, matrix2);
        long finish = System.nanoTime();
        float time= (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Time multithreaded: " + time + "s");
        System.out.println("Result matrix\n"+ParallelMatrixTest);

        start = System.nanoTime();
        matrix1.product(matrix2);
        finish = System.nanoTime();
        time= (float) ((finish - start) * 1.0 / 1000000000);
        System.out.println("Time onethreaded: " + time + "s");
        System.out.println("Result matrix\n"+matrix1);
        System.out.println(matrix1.equals(ParallelMatrixTest));
    }
}
