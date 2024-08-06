import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {

    @Test
    void testAdd() {
        assertEquals(5, MathOperations.add(2, 3), 0.001);
        assertEquals(-1, MathOperations.add(-4, 3), 0.001);
    }

    @Test
    void testSubtract() {
        assertEquals(2, MathOperations.subtract(5, 3), 0.001);
        assertEquals(-7, MathOperations.subtract(-4, 3), 0.001);
    }

    @Test
    void testMultiply() {
        assertEquals(15, MathOperations.multiply(3, 5), 0.001);
        assertEquals(-12, MathOperations.multiply(-4, 3), 0.001);
    }

    @Test
    void testDivide() {
        assertEquals(2, MathOperations.divide(6, 3), 0.001);
        assertEquals(-2, MathOperations.divide(-6, 3), 0.001);
        assertThrows(ArithmeticException.class, () -> MathOperations.divide(5, 0));
    }

    @Test
    void testPower() {
        assertEquals(8, MathOperations.power(2, 3), 0.001);
        assertEquals(0.25, MathOperations.power(2, -2), 0.001);
    }

    @Test
    void testSquareRoot() {
        assertEquals(2, MathOperations.squareRoot(4), 0.001);
        assertEquals(3, MathOperations.squareRoot(9), 0.001);
        assertThrows(IllegalArgumentException.class, () -> MathOperations.squareRoot(-1));
    }

    @Test
    void testAbsoluteValue() {
        assertEquals(5, MathOperations.absoluteValue(-5), 0.001);
        assertEquals(5, MathOperations.absoluteValue(5), 0.001);
        assertEquals(0, MathOperations.absoluteValue(0), 0.001);
    }

    @Test
    void testFactorial() {
        assertEquals(1, MathOperations.factorial(0));
        assertEquals(1, MathOperations.factorial(1));
        assertEquals(120, MathOperations.factorial(5));
        assertThrows(IllegalArgumentException.class, () -> MathOperations.factorial(-1));
    }
}