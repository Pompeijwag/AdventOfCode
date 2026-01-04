import org.ejml.simple.SimpleMatrix;

public class TestEJML {
    public static void main(String[] args) {
        SimpleMatrix A = new SimpleMatrix(new double[][] {
            {1, 2},
            {3, 4}
        });

        System.out.println("Inverse:");
        A.invert().print();
    }
}
