package com.aqoleg.crypto.test;

class AllTests {
    public static void main(String[] args) {
        System.out.println("EccTest");
        EccTest.main(null);
        System.out.println();
        System.out.println("HmacSha512Test");
        HmacSha512Test.main(null);
        System.out.println();
        System.out.println("Ripemd160Test");
        Ripemd160Test.main(null);
        System.out.println();
        System.out.println("RsaTest");
        RsaTest.main(null);
        System.out.println();
        System.out.println("Sha256Test");
        Sha256Test.main(null);
        System.out.println();
        System.out.println("Sha512Test");
        Sha512Test.main(null);
        System.out.println();
        System.out.println("Shake128Test");
        Shake128Test.main(null);
    }
}