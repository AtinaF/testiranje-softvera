package calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
    Calculator calculator;

    @BeforeAll
    void setUp() {
        calculator = new Calculator();
    }

    @Order(0)
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

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(files={"src/test/resources/params/calculatorTestingDataSource.csv"},
                    numLinesToSkip = 1)
    void testSubstract(int a, int b, int expectedValue)
    {
        assertEquals(expectedValue, calculator.subtract(a, b),
                "Substruction failed for {a} - {b}");
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource(value = "getArgumentsForMultiplication")
    void testMultiply(int a, int b, double expectedValue) {
        assertEquals(expectedValue, calculator.multiply(a, b),
                "Multiplication failed for {a} * {b}");
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("calculator.MethodSourceForMultiplicationTest#getArgumentsForMultiplication2")
    void testMultiply2(int a, int b, double expectedValue) {
        assertEquals(expectedValue, calculator.multiply(a, b),
                "Multiplication failed for '{a}' * '{b}'");
    }

    List<Arguments> getArgumentsForMultiplication()
    {
        return Arrays.asList (arguments(10,8,80),
                            arguments(8,10,80),
                            arguments(-3,-9,27),
                            arguments(-9,-3,27),
                            arguments(15,-2,-30),
                            arguments(-2,15,-30),
                            arguments(0, 0, 0),
                            arguments(65,0,0),
                            arguments(-23,0,0));
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource(value = {"8,4,2","5,10,0.5",
                        "-24,-6,4","-86,-100,0.86",
                        "90,-9,-10","6,-10,-0.6",
                        "-18,1,-18","35,1,35"})
    void testDivide(int a, int b, double expectedValue) {
        assertEquals(expectedValue, calculator.divide(a, b),
                "Division failed for values {a} / {b}");
    }

    @Test
    void divideWithZero(){
        assertThrows(ArithmeticException.class , () -> calculator.divide(4,0), "You can not divide with 0. ");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }
}
