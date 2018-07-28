package in.bigdolph.jnano.util;

/**
 * A static class listing the official and well-known public addresses.
 * Address values <b>do not</b> include any prefix and are in their raw form with the checksum.
 * <br><br>
 * To apply the appropriate prefix, prepend <code>AddressUtil.DEFAULT_PREFIX_NAME + AddressUtil.DEFAULT_PREFIX_CHAR</code> to the address.
 */
public final class KnownAccounts {
    private KnownAccounts() {} //Static class
    
    /**
     * The official genesis address.
     */
    public static final String GENESIS = "3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3";
    public static final String LANDING = "13ezf4od79h1tgj9aiu4djzcmmguendtjfuhwfukhuucboua8cpoihmh8byo";
    public static final String FAUCET = "35jjmmmh81kydepzeuf9oec8hzkay7msr6yxagzxpcht7thwa5bus5tomgz9";
    public static final String BURN = "1111111111111111111111111111111111111111111111111111hifc8npp";
    
    /**
     * A list of official assigned representatives.
     */
    public static final String[] OFFICIAL_REPRESENTATIVES = {
            "3arg3asgtigae3xckabaaewkx3bzsh7nwz7jkmjos79ihyaxwphhm6qgjps4", "1stofnrxuz3cai7ze75o174bpm7scwj9jn3nxsn8ntzg784jf1gzn1jjdkou",
            "1q3hqecaw15cjt7thbtxu3pbzr1eihtzzpzxguoc37bj1wc5ffoh7w74gi6p", "3dmtrrws3pocycmbqwawk6xs7446qxa36fcncush4s1pejk16ksbmakis78m",
            "3hd4ezdgsp15iemx7h81in7xz5tpxi43b6b41zn3qmwiuypankocw3awes5k", "1awsn43we17c1oshdru4azeqjz9wii41dy8npubm4rg11so7dx3jtqgoeahy",
            "1anrzcuwe64rwxzcco8dkhpyxpi8kd7zsjc1oeimpc3ppca4mrjtwnqposrs", "1hza3f7wiiqa7ig3jczyxj5yo86yegcmqk3criaz838j91sxcckpfhbhhra1"};
    
}
