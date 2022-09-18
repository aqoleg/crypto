package com.aqoleg.crypto.test;

import com.aqoleg.crypto.Rsa;

import java.util.Random;

class RsaTest {
    private boolean testResult = true;
    private boolean functionResult = true;

    public static void main(String[] args) {
        System.out.println("start");
        RsaTest test = new RsaTest();
        try {
            test.test();
        } catch (Throwable throwable) {
            System.out.println("no exception != " + throwable);
            test.testResult = false;
        }
        System.out.println("result " + (test.testResult ? "ok" : "not ok"));
        System.out.println("end");
    }

    private void test() {
        functionResult = true;
        assertThrows(UnsupportedOperationException.class, () -> Rsa.createKeyPair(6));
        Rsa.KeyPair keyPair = Rsa.createKeyPair(34);
        Rsa.PublicKey publicKey = keyPair.getPublicKey();
        byte[] msg = {4, 120, 8, -9, 7};
        assertThrows(NullPointerException.class, () -> keyPair.encrypt(null));
        assertThrows(NullPointerException.class, () -> publicKey.encrypt(null));
        assertThrows(NullPointerException.class, () -> keyPair.decrypt(null));
        assertThrows(UnsupportedOperationException.class, () -> keyPair.encrypt(msg));
        msg[0] = 1;
        byte[] cypher = publicKey.encrypt(msg);
        assertArrayEquals(cypher, keyPair.encrypt(msg));
        byte[] decrypt = keyPair.decrypt(cypher);
        assertArrayEquals(msg, decrypt);
        testResult &= functionResult;
        System.out.println("test() " + (functionResult ? "ok" : "not ok"));

        testWithKeyLength(20);
        testWithKeyLength(21);
        testWithKeyLength(82);
        testWithKeyLength(258);
        testWithKeyLength(514);
        testWithKeyLength(1002);
        testWithKeyLength(2007);
    }

    private void testWithKeyLength(int keyLength) {
        functionResult = true;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Rsa.KeyPair keyPair = Rsa.createKeyPair(keyLength);
            byte[] msg = new byte[keyLength >>> 3];
            for (int j = 0; j < msg.length; j++) {
                msg[j] = (byte) random.nextInt();
                if (j == 0 && msg[0] == 0) {
                    msg[0] = 1;
                }
            }
            assertArrayEquals(msg, keyPair.decrypt(keyPair.encrypt(msg)));
        }
        testResult &= functionResult;
        System.out.println("testWithKeyLength" + keyLength + " " + (functionResult ? "ok" : "not ok"));
    }

    private void assertArrayEquals(byte[] a, byte[] b) {
        int length = a.length;
        if (b.length != length) {
            functionResult = false;
            System.out.println("length " + length + " != " + b.length);
            return;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (a[i] != b[i]) {
                functionResult = false;
                System.out.println("i" + i + " " + a[i] + " != " + b[i]);
                return;
            }
        }
    }

    private void assertThrows(Class exception, Function function) {
        try {
            function.run();
        } catch (Throwable throwable) {
            if (!exception.isInstance(throwable)) {
                functionResult = false;
                System.out.println(exception.getName() + " != " + throwable);
            }
            return;
        }
        functionResult = false;
        System.out.println(exception.getName() + " != no exception");
    }

    interface Function {
        void run();
    }
}