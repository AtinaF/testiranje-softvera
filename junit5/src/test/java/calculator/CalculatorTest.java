package calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
    Calculator calculator;

    @BeforeAll
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource(value={"2,7,9","7,2,9",
                    "-2,-7,-9","-7,-2,-9",
                    "3,-5,-2","-5,3,-2",
                    "0,0,0",
                    "6,0,6","-15,0,-15"})
    void testAdd(int a, int b, int expectedValue)
    {
        assertEquals(expectedValue, calculator.add(a, b),
                "Addition failed for " + a + "+" + b);
    }

    @ParameterizedTest
    @CsvSource(value={"20,8,12","8,20,-12",
                        "-5,-11,6","-10,-4,-6",
                        "10,-3,13","-10,3,-13",
                        "30,0,30","-50,0,-50"})
    void testSubstract(int a, int b, int expectedValue)
    {
        assertEquals(expectedValue, calculator.subtract(a, b),
                "Substruction failed for {a} - {b}");
    }


    @ParameterizedTest
    @CsvSource(value={"10,8,80","8,10,80",
                        "-3,-9,27","-9,-3,27",
                        "15,-2,-30","-2,15,-30",
                        "0, 0, 0",
                        "65,0,0","-23,0,0"})
    void testMultiply(int a, int b, int expectedValue) {
        assertEquals(expectedValue, calculator.multiply(a, b),
                "Multiplication failed for {a} * {b}");
    }

    @ParameterizedTest
    @CsvSource(value = {"8,4,2","5,10,0.5",
                        "-24,-6,4","-86,-100,0.86",
                        "90,-9,-10","6,-10,-0.6",
                        "-18,1,-18","35,1,35"})
    void testDivide(int a, int b, double expectedValue) {
        assertEquals(expectedValue, calculator.divide(a, b),
                "Division failed for values {a} / {b}");
    }

//    TODO
//    @Test
//    void testDivideWithZero() {
//        assertThrows(ArithmeticException.class, calculator.divide(9,0));
//    }



        @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }
}
