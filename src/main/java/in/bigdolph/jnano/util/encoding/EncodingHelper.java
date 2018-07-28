package in.bigdolph.jnano.util.encoding;

public class EncodingHelper {
    
    public static final BaseEncoder HEXADECIMAL = new BaseEncoder("0123456789ABCDEF");
    
    public static final BaseEncoder NANO_BASE32 = new BaseEncoder("13456789abcdefghijkmnopqrstuwxyz");
    
}
