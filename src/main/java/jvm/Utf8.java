package jvm;


import java.io.UnsupportedEncodingException;

/**
 * Utf8
 *
 * @author virgilin
 * @date 2019/3/6
 */
public class Utf8 {
    public static void main(String[] args) {
        //6e61 6d65
        byte[] b = {(byte) 0x6e, (byte) 0x61, (byte) 0x6d, (byte) 0x65};
        try {
            System.out.println(new String(b, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}