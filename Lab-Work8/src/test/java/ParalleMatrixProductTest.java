import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParalleMatrixProductTest {

    @Test
    void product() {
        int size1 = 2;
        int size2 =3;
        UsualMatrix m1=new UsualMatrix(size1,size2);
        for(int i= 0; i<size1;i++){
            for (int j=0; j<size2; j++){
                m1.setElement(i,j,i+j);
            }
        }
        UsualMatrix m2=new UsualMatrix(size2,size1);
        for(int i= 0; i<size2;i++){
            for (int j=0; j<size1; j++){
                m2.setElement(i,j,i+j);
            }
        }
        ParalleMatrixProduct result = new ParalleMatrixProduct(4);
        UsualMatrix expected = new UsualMatrix(size1,size1);
        expected.setElement(0,0,5);
        expected.setElement(0,1,8);
        expected.setElement(1,0,8);
        expected.setElement(1,1,14);
        assertEquals(expected,result.product(m1,m2));
    }
}