
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Connor Finneran
 * @author Anthony Yeretzian
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        //creates an empty string to represent the natural number
        this.rep = "";
        //since we use "" as our representation of zero, we need to make sure
        //that "0" will never be used

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {
        this.createNewRep();
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        this.createNewRep();

        if (i > 0) {
            this.rep = Integer.toString(i);
        }
        //convert the integer to a string to represent the natural number

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        //set our representation equal to s if it is not equal to zero
        this.createNewRep();

        if (!s.equals("0")) {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        //need to use the toString method since we only know the declared type
        this.createNewRep();
        String s = n.toString();

        if (!s.equals("0")) {
            this.rep = s;
        }

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        /*
         * if either the natural number and the digit being added isn't zero, it
         * will add that digit to the end
         */
        String digit = Integer.toString(k);
        if (this.rep.length() > 0 || k > 0) {
            this.rep = this.rep + digit;
        }
    }

    @Override
    public final int divideBy10() {

        /*
         * set the remainder to zero to return zero if the length of the natural
         * number is zero
         */
        int remainder = 0;

        if (this.rep.length() > 0) {
            // Get last digit which is last character of this.rep
            remainder = Character
                    .getNumericValue(this.rep.charAt(this.rep.length() - 1));

            // Update this.rep to remove the last digit
            this.rep = this.rep.substring(0, this.rep.length() - 1);
        }

        return remainder;
    }

    @Override
    public final boolean isZero() {
        /*
         * if the length of the string is zero, the natural number it represents
         * is zero
         */
        return (this.rep.length() == 0);
    }

}
