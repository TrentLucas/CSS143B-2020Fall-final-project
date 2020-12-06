package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        // add your code
        // step 1: split key phrase into words by space

        // step 2: get the documents that contain all the words in the given phrase
        // [0, 1, 3, 4, 5], [0, 2, 3, 4, 5]
        // get the common number (document id) of both lists -> [0, 3, 4, 5]

        // step 3: for each common document, get the location idx of each word in the search phrase
        // "is here already"
        // document 2: {1}, {2}, {3}

        // determine whether search words are in the correct order right next to each other
        // if yes doc 2 is one of the answers

        // do the location math, and return the documents that have common index after the calculation

        // [3, 4, 1, 2] [1, 3, 2] [2, 7, 8, 3, 5] [8, 3, 10, 2]

        // how to get the intersection of all the arrays?

        // [2, 3] ??

        // for each of the numbers in [ 2, 7, 8, 3, 5],
        // see if it exists in the other array

        // [2, 3]

        // hashmap
        // key: numbers
        // value: counts
        // 3 -> 4
        // 4 -> 1
        // 1 -> 2
        // 2 -> 4

        // 0(n^2), 0(n)

        return result;
    }
}