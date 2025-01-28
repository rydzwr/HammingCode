package com.rydzwr.hamming;

public class HammingCode {
    public static int[] getCode(int[] dataBits64, boolean verbose) {
        if (verbose) System.out.println("Calculating Hamming Code...");
        System.out.println("===================================");

        int[] u = dataBits64;

        int p0 = calculateParity(u, 0b000001, verbose, "p0");
        int p1 = calculateParity(u, 0b000010, verbose, "p1");
        int p2 = calculateParity(u, 0b000100, verbose, "p2");
        int p3 = calculateParity(u, 0b001000, verbose, "p3");
        int p4 = calculateParity(u, 0b010000, verbose, "p4");
        int p5 = calculateParity(u, 0b100000, verbose, "p5");
        int p6 = calculateParity(u, 0b110000, verbose, "p6");

        if (verbose) {
            System.out.printf("Final Hamming Code (parity bits): p0=%d, p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d\n",
                    p0, p1, p2, p3, p4, p5, p6);
        }
        return new int[]{p0, p1, p2, p3, p4, p5, p6};
    }

    public static boolean tryCorrectData(int[] dataBits64, int[] hammingCode) {
        int newCheckBits = codeWordToIntegerWord(getCode(dataBits64, false));
        int receivedCheckBits = codeWordToIntegerWord(hammingCode);

        int syndrome = newCheckBits ^ receivedCheckBits;

        if (syndrome == 0) {
            return false;
        }

        if ((syndrome & (syndrome - 1)) == 0) {
            int parityIndex = Integer.numberOfTrailingZeros(syndrome);
            hammingCode[parityIndex] ^= 0x01; // Correct the parity bit
        } else {
            dataBits64[syndrome] ^= 0x01; // Correct the data bit
        }
        return true;
    }

    private static int codeWordToIntegerWord(int[] code) {
        int checkBits = 0;
        for (int i = 0; i < code.length; i++) {
            checkBits |= (code[i] << i);
        }
        return checkBits;
    }

    private static int calculateParity(int[] u, int mask, boolean verbose, String name) {
        int parity = 0;
        for (int i = 0; i < 64; i++) {
            if ((i & mask) == mask) {
                parity ^= u[i];
                if (verbose) System.out.printf("%s ^= u[%d] -> %d -> %s = %d\n", name, i, u[i], name, parity);
            }
        }
        return parity;
    }
}
