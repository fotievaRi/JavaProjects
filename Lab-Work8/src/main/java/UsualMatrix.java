public class UsualMatrix {
    private int[][] matrix;
    private  int row;
    private int column;
    UsualMatrix(int row, int column) {
        this.row = row;
        this.column = column;
        matrix = new int[row][column];
    }

    UsualMatrix(UsualMatrix obj) {
        matrix = new int[obj.getSizeRow()][obj.getSizeColumn()];
        row = obj.getSizeRow();
        column = getSizeColumn();
        for (int i = 0; i < obj.getSizeRow(); i++) {
            System.arraycopy(obj.matrix[i], 0, matrix[i], 0, obj.getSizeColumn());
        }
    }
     int getRow(){
        return row;
     }

     int getColumn(){
        return column;
     }
    public UsualMatrix sum(UsualMatrix obj) {
        if (getSizeColumn() != obj.getSizeColumn() && getSizeRow() != obj.getSizeRow())
            throw new RuntimeException("Error! The matrix have different sizes!");
        UsualMatrix result = new UsualMatrix(row,column);
        for (int i = 0; i < getSizeRow(); i++) {
            for (int j = 0; j < getSizeColumn(); j++) {
                result.setElement(i,j,matrix[i][j] + obj.getElement(i,j) );
            }
        }
        return result;
    }

    public UsualMatrix product(UsualMatrix obj) {
        if (getSizeColumn() != obj.getSizeRow())
            throw new RuntimeException("Error! Product of matrices with these sizes is undefined!");

        UsualMatrix result = new UsualMatrix(row,obj.getColumn());

        for (int i = 0; i < getSizeRow(); i++) {
            for (int j = 0; j < obj.getSizeColumn(); j++) {
                for (int k = 0; k < obj.getSizeRow(); k++) {
                    result.setElement(i,j, result.getElement(i,j)+ matrix[i][k] * obj.getElement(k,j));
                }
            }
        }

        return result;
    }

    public void setElement(int row, int column, int value) {
        if (row < 0 || column < 0 || row >= getSizeRow() || column >= getSizeColumn())
            throw new RuntimeException("Error! Outside the matrix!");

        matrix[row][column] = value;
    }

    public int getElement(int row, int column) {
        if (row < 0 || column < 0 || row >= getSizeRow() || column >= getSizeColumn())
            throw new RuntimeException("Error! Outside the matrix!");

        return matrix[row][column];
    }

    public int getSizeColumn() {
        return matrix[0].length;
    }

    public int getSizeRow() {
        return matrix.length;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof UsualMatrix))
            return false;

        UsualMatrix mtrx = (UsualMatrix) obj;
        for (int i = 0; i < getSizeRow(); i++)
            for (int j = 0; j < getSizeColumn(); j++)
                if (matrix[i][j] != mtrx.matrix[i][j])
                    return false;

        return true;
    }

    public String toString() {
        StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < getSizeRow(); i++) {
            matrixToString.append("[");
            for (int j = 0; j < getSizeColumn(); j++) {
                matrixToString.append(matrix[i][j]);
                if (j != getSizeColumn() - 1) matrixToString.append(" ");
            }
            matrixToString.append("]\n");
        }
        return matrixToString.toString();
    }
}