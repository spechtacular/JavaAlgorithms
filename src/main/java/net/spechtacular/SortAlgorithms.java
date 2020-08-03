package net.spechtacular;

import java.util.Arrays;
import java.util.logging.Logger;

public class SortAlgorithms {

    private Logger LOGGER;

    SortAlgorithms(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    void bubbleSort(int[] input) {
        LOGGER.info("bubbleSort input="+Arrays.toString(input));

        for (int lastUnsortedIndex = input.length - 1; lastUnsortedIndex > 0;
             lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (input[i] > input[i + 1]) {
                    swap(input, i, i + 1);
                }
            }
        }

        LOGGER.info("bubbleSort output="+Arrays.toString(input));

    }

    void selectionSort(int[] input) {
        LOGGER.info("selectionSort input="+Arrays.toString(input));

        for (int lastUnsortedIndex = input.length - 1; lastUnsortedIndex > 0;
             lastUnsortedIndex--) {

            int largest = 0;

            for (int i = 1; i <= lastUnsortedIndex; i++) {
                if (input[i] > input[largest]) {
                    largest = i;
                }
            }
            swap(input, largest, lastUnsortedIndex);
        }

        LOGGER.info("selectionSort output="+Arrays.toString(input));

    }

    void insertionSort(int[] input) {
        LOGGER.info("insertionSort input="+Arrays.toString(input));


        for (int firstUnsortedIndex = 1; firstUnsortedIndex < input.length;
             firstUnsortedIndex++) {
            int newElement = input[firstUnsortedIndex];

            int i;
            for (i = firstUnsortedIndex; i > 0 && input[i - 1] > newElement; i--) {
                input[i] = input[i - 1];
            }

            input[i] = newElement;
        }

        LOGGER.info("insertionSort output="+Arrays.toString(input));


    }

    void insertionSortRecursive(int[] input, int numItems) {
        LOGGER.info("insertionSortRecursive input="+Arrays.toString(input));

        if (numItems < 2)
            return;

        insertionSortRecursive(input, numItems-1);

        int newElement = input[numItems - 1];

        int i;
        for (i = numItems - 1; i > 0 && input[i - 1] > newElement; i--) {
            input[i] = input[i - 1];
        }

        input[i] = newElement;

        LOGGER.info("insertionSortRecursive output="+Arrays.toString(input));

    }

    void shellSort(int[] input) {
        LOGGER.info("shellSort input="+Arrays.toString(input));

    }

    void mergeSort(int[] input, int start, int end) {
        LOGGER.info("mergeSort input="+Arrays.toString(input));


        if (end - start < 2) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        merge(input, start, mid, end);

        LOGGER.info("mergeSort output="+Arrays.toString(input));

    }

    public static void merge(int[] input, int start, int mid, int end) {

        // changed to descending order
        if (input[mid - 1] >= input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        int[] temp = new int[end - start];
        while (i < mid && j < end) {
            // changed to descending order
            temp[tempIndex++] = input[i] >= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);

    }


    void quickSort(int[] input, int start, int end) {
        LOGGER.info("quickSort input="+Arrays.toString(input));

        if (end - start < 2) {
            return;
        }

        int pivotIndex = partition(input, start, end);
        quickSort(input, start, pivotIndex);
        for (int i: input) {
            System.out.println(i);
        }
        quickSort(input, pivotIndex + 1, end);
        LOGGER.info("quickSort output="+Arrays.toString(input));

    }

    public static int partition(int[] input, int start, int end) {
        // This is using the first element as the pivot
        int pivot = input[start];
        int i = start;
        int j = end;

        while (i < j) {

            // NOTE: empty loop body
            while (i < j && input[--j] >= pivot);
            if (i < j) {
                input[i] = input[j];
            }

            // NOTE: empty loop body
            while (i < j && input[++i] <= pivot);
            if (i < j) {
                input[j] = input[i];
            }

        }

        input[j] = pivot;
        return j;

    }


    void countingSort(int[] input, int min, int max) {
        LOGGER.info("countingSort input="+Arrays.toString(input));

        int[] countArray = new int[(max - min) + 1];

        for (int i: input) {
            countArray[input[i] - min]++;
        }

        int j = 0;
        for (int i = min; i <= max; i++) {
            while (countArray[i - min] > 0) {
                input[j++] = i;
                countArray[i - min]--;
            }
        }

        LOGGER.info("countingSort output="+Arrays.toString(input));

    }

    void radixSort(int[] input,  int radix, int width) {
        LOGGER.info("radixSort input="+Arrays.toString(input));

        for (int i = 0; i < width; i++) {
            radixSingleSort(input, i, radix);
        }

        LOGGER.info("radixSort results="+ Arrays.toString(input));

    }

    private static void radixSingleSort(int[] input, int position, int radix) {

        int numItems = input.length;
        int[] countArray = new int[radix];

        for (int value: input) {
            countArray[getDigit(position, value, radix)]++;
        }
        // Adjust the count array
        for (int j = 1; j < radix; j++) {
            countArray[j] += countArray[j - 1];
        }

        int[] temp = new int[numItems];
        for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
            temp[--countArray[getDigit(position, input[tempIndex], radix)]] =
                    input[tempIndex];
        }

        for (int tempIndex = 0; tempIndex < numItems; tempIndex++) {
            input[tempIndex] = temp[tempIndex];
        }
    }

    void radixSortString(String[] input,  int radix, int width) {
        LOGGER.info("radixSortString input="+Arrays.toString(input));

        for (int i = width-1; i >= 0; i--) {
            radixSingleSortString(input, i, radix);
        }

        LOGGER.info("radixSortString results="+ Arrays.toString(input));

    }

    void radixSingleSortString(String[] input, int position, int radix) {

        int numItems = input.length;
        int[] countArray = new int[radix];

        for (String value: input) {
            countArray[getCharacter(position, value)]++;
        }

        // Adjust the count array
        for (int j = 1; j < radix; j++) {
            countArray[j] += countArray[j - 1];
        }

        String[] temp = new String[numItems];
        for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
            temp[--countArray[getCharacter(position, input[tempIndex])]] =
                    input[tempIndex];
        }

        for (int tempIndex = 0; tempIndex < numItems; tempIndex++) {
            input[tempIndex] = temp[tempIndex];
        }
    }


    public static int getDigit(int position, int value, int radix) {
        return value / (int) Math.pow(radix, position) % radix;
    }

    public static int getCharacter(int position, String value) {
        int convertedPosition = value.charAt(position)-'a';
        return convertedPosition;
    }



    static void swap(int[] array, int i, int j) {

        if (i == j) {
            return;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void parallelSort(int[] input) {
        LOGGER.info("parallelSort input="+Arrays.toString(input));
        // multithreaded sort
        Arrays.parallelSort(input);
        LOGGER.info("parallelSort output="+Arrays.toString(input));
    }
}
