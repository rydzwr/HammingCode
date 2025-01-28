package com.rydzwr.hamming;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var dataBits = new int[64];
        for (var i = 0; i < dataBits.length; i++) {
            dataBits[i] = (i % 2 == 0) ? 0x00 : 0x01; // Alternating 0 and 1
        }

        System.out.println("Initial data bits (8x8 Matrix):");
        printMatrix(dataBits, -1, -1); // No corrupted or corrected bit initially

        // Step 1: Generate the Hamming Code for the initial data
        var hammingCode = HammingCode.getCode(dataBits, true);
        System.out.println("Generated Hamming Code: " + Arrays.toString(hammingCode));

        // Step 2: Introduce an error at position 10
        System.out.println("\nIntroducing an error at position 10 (index 10)...");
        dataBits[10] ^= 0x01; // Flip the bit at position 10

        System.out.println("Corrupted data bits (8x8 Matrix):");
        printMatrix(dataBits, 10, -1); // Highlight corrupted bit at index 10

        // Step 3: Attempt to detect and correct the error
        var corrected = HammingCode.tryCorrectData(dataBits, hammingCode);

        if (corrected) {
            System.out.println("\nError corrected!");
            System.out.println("Corrected data bits (8x8 Matrix):");
            printMatrix(dataBits, -1, 10); // Highlight corrected bit at index 10

            // Step 4: Revalidate the corrected data
            System.out.println("\nRevalidating the corrected data...");
            var revalidationCode = HammingCode.getCode(dataBits, true);
            if (Arrays.equals(revalidationCode, hammingCode)) {
                System.out.println("Revalidation successful! No errors detected in the corrected data.");
            } else {
                System.out.println("Revalidation failed! Errors are still present in the data.");
            }
        } else {
            System.out.println("\nNo error detected or multiple errors present. No correction applied.");
        }

        // Final Step: Print the final matrix to show the correction explicitly
        System.out.println("\nFinal data bits (8x8 Matrix) after correction:");
        printMatrix(dataBits, -1, -1); // Ensure clean display of the corrected matrix

        // Verify if the final matrix matches the initial matrix
        boolean matchesInitial = verifyMatrix(dataBits);
        System.out.println("\nDoes the final matrix match the initial matrix? " + matchesInitial);
    }

    /**
     * Utility method to format and print data bits as an 8x8 matrix.
     * @param dataBits The array of data bits.
     * @param corruptedIndex The index of the corrupted bit (use -1 if none).
     * @param correctedIndex The index of the corrected bit (use -1 if none).
     */
    private static void printMatrix(int[] dataBits, int corruptedIndex, int correctedIndex) {
        for (int i = 0; i < dataBits.length; i++) {
            if (i == corruptedIndex) {
                // Highlight corrupted bit
                System.out.print("{" + dataBits[i] + "} ");
            } else if (i == correctedIndex) {
                // Highlight corrected bit
                System.out.print("[" + dataBits[i] + "] ");
            } else {
                // Normal bit
                System.out.print(dataBits[i] + " ");
            }
            if ((i + 1) % 8 == 0) {
                System.out.println(); // Newline after every 8 elements
            }
        }
    }

    /**
     * Verify if the final data matrix matches the initial alternating 0-1 pattern.
     * @param dataBits The final corrected data bits.
     * @return True if the matrix matches the initial state, false otherwise.
     */
    private static boolean verifyMatrix(int[] dataBits) {
        for (int i = 0; i < dataBits.length; i++) {
            if (dataBits[i] != ((i % 2 == 0) ? 0x00 : 0x01)) {
                return false; // If any bit doesn't match, return false
            }
        }
        return true; // All bits match
    }
}
