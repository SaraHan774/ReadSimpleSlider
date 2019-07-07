package com.gahee.rss_v1.helpers;

public class LinearSearch {
      // Linear-search function to find the index of an element
        public static int findIndex(String [] array, String target)
        {
            // if array is Null
            if (array == null) {
                return -1;
            }

            // find length of array
            int len = array.length;
            int i = 0;

            // traverse in the array
            while (i < len) {

                // if the i-th element is t
                // then return the index
                if (array[i].equals(target)) {
                    return i;
                }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

}

