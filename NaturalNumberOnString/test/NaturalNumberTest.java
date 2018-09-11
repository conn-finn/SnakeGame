
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Tests of constructor
     */

    /**
     * Test case for the no argument constructor.
     */
    @Test
    public void testNoArgConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(n, nExpected);
    }

    /**
     * Test case for constructor with int argument.
     */
    @Test
    public void testIntConstructor() {
        int i = 267;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for boundary int argument.
     */
    @Test
    public void testIntConstructorZero() {
        int i = 0;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for challenging int constructor.
     */
    @Test
    public void testIntConstructorMaxInt() {
        //max int value is 2147483647
        int i = Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for boundary string argument constructor.
     */
    @Test
    public void testStringConstructorZero() {
        String s = "0";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for routine string argument constructor.
     */
    @Test
    public void testStringConstructorInteger() {
        String s = "999999";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for challenging string argument constructor.
     */
    @Test
    public void testStringConstructorMaxInt() {
        //tests above max int value
        String s = "1" + Integer.toString(Integer.MAX_VALUE);
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        assertEquals(n, nExpected);
    }

    /**
     * Test case for routine Natural Number argument constructor.
     */
    @Test
    public void testNNConstructor() {
        NaturalNumber nExpected = this.constructorRef(2342);
        NaturalNumber n = this.constructorTest(nExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test for Natural Number constructor with argument greater
     * than max int value.
     */
    @Test
    public void testNNConstructorMaxInt() {
        NaturalNumber nExpected = this
                .constructorRef("1" + Integer.toString(Integer.MAX_VALUE));
        NaturalNumber n = this.constructorTest(nExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Boundary test for NaturalNumber constructor.
     */
    @Test
    public void testNNConstructorZero() {
        NaturalNumber nExpected = this.constructorRef(0);
        NaturalNumber n = this.constructorTest(nExpected);
        assertEquals(n, nExpected);
    }

    /*
     * isZero test cases.
     */

    /**
     * Tests isZero for a no-argument constructor.
     */
    @Test
    public final void testIsZeroDefault() {
        // default value is 0
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(n, nExpected);
        assertEquals(true, n.isZero());
    }

    /**
     * Tests isZero with a constructor which has an argument.
     */
    @Test
    public final void testIsZeroTrue() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n, nExpected);
        assertEquals(true, n.isZero());
    }

    /**
     * Tests isZero in a routine case in which the value returned is false.
     */
    @Test
    public final void testIsZeroFalse() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(5);

        assertEquals(n, nExpected);
        assertEquals(false, n.isZero());
    }

    /*
     * multiplyBy10 test cases.
     */

    /**
     * Tests multiplyBy10 in the boundary case in which n is zero.
     */
    @Test
    public final void testMultiplyBy10Zero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(5);

        n.multiplyBy10(5);

        assertEquals(n, nExpected);
    }

    /**
     * Tests multiplyBy10 in a routine case.
     */
    @Test
    public final void testMultiplyBy10NonZero() {
        NaturalNumber n = this.constructorTest(123);
        NaturalNumber nExpected = this.constructorRef(1234);

        n.multiplyBy10(4);

        assertEquals(n, nExpected);
    }

    /**
     * Tests multiplyBy10 in the challenging case in which n is greater than the
     * max integer value.
     */
    @Test
    public final void testMultiplyBy10OverMaxInt() {
        String s = "1" + Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s + "3");

        n.multiplyBy10(3);

        assertEquals(n, nExpected);
    }

    /**
     * Test the boundary case in which 0 argument.
     */
    @Test
    public final void testMultiplyBy10AddZero() {
        NaturalNumber n = this.constructorTest(22);
        NaturalNumber nExpected = this.constructorRef(220);
        n.multiplyBy10(0);

        assertEquals(n, nExpected);
    }

    /**
     * Test for boundary case where n is zero and adding zero.
     */
    @Test
    public final void testMultiplyBy10ZeroAddZero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        n.multiplyBy10(0);

        assertEquals(n, nExpected);
    }

    /*
     * divideBy10 test cases
     */

    /**
     * Tests divideBy10 in the boundary case in which n is zero.
     */
    @Test
    public final void testDivideBy10Zero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        int nReturns = n.divideBy10();
        int nExpectedReturns = 0;

        assertEquals(nReturns, nExpectedReturns);
        assertEquals(n, nExpected);

    }

    /**
     * Tests divideBy10 in a routine case.
     */
    @Test
    public final void testDivideBy10NonZero() {
        NaturalNumber n = this.constructorTest(513);
        NaturalNumber nExpected = this.constructorRef(51);

        int nReturns = n.divideBy10();
        int nExpectedReturns = 3;

        assertEquals(nReturns, nExpectedReturns);
        assertEquals(n, nExpected);
    }

    /**
     * Tests divideBy10 in the challenging case in which n is greater than the
     * max integer value.
     */
    @Test
    public final void testDivideBy10OverMaxInt() {
        String s = Integer.MAX_VALUE + "15";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE + "1");

        int nReturns = n.divideBy10();
        int nExpectedReturns = 5;

        assertEquals(nReturns, nExpectedReturns);
        assertEquals(n, nExpected);
    }
}
