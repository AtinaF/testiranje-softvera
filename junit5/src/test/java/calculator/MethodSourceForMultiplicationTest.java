package calculator;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MethodSourceForMultiplicationTest {
    public static List<Arguments> getArgumentsForMultiplication2()
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

}
