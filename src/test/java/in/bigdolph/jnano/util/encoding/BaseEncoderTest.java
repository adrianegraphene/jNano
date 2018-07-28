package in.bigdolph.jnano.util.encoding;

import in.bigdolph.jnano.tests.UtilTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class BaseEncoderTest {
    
    BaseEncoder encoder16 = new BaseEncoder("0123456789ABCDEF");
    
    
    @Test @Category(UtilTests.class)
    public void testCharTable() {
        //Values
        assertEquals(0, encoder16.getValueFromChar('0'));
        assertEquals(15, encoder16.getValueFromChar('F'));
        //Chars
        assertEquals('0', encoder16.getCharFromValue(0));
        assertEquals('F', encoder16.getCharFromValue(15));
    }
    
    
    @Test @Category(UtilTests.class)
    public void encodeTest() {
        byte[] bytes = {(byte)0xFF, (byte)0xF0, (byte)0x0F, (byte)0x0E};
        assertEquals("FFF00F0E", encoder16.encode(bytes));
    }
    
    
    @Test @Category(UtilTests.class)
    public void decodeTest() {
        byte[] response = encoder16.decode("FFF00F0E");
        byte[] expected = {(byte)0xFF, (byte)0xF0, (byte)0x0F, (byte)0x0E};
        assertArrayEquals(expected, response);
    }
    
}