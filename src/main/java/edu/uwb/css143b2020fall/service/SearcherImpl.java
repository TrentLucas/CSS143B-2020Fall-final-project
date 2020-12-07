package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        String[] words = keyPhrase.split("\\s+");
        Map<String, List<List<Integer>>> allWords = new HashMap<>();

        // empty list for if the word isnt in the index
        LinkedList emptyWords = new LinkedList();
        for(int a = 0; a < index.get("hello").size(); a++) {
            emptyWords.add(null);
        }

        //store all instances of each search word
        for (int b = 0; b < words.length; b++) {
            LinkedList tempWords = new LinkedList();
            if (index.containsKey(words[b])) {
                for (int c = 0; c < index.get(words[b]).size(); c++) {
                    tempWords.add(index.get(words[b]).get(c));
                }
                if (allWords.containsKey(words[b])) {
                    allWords.put(words[b] + "1", tempWords);
                } else {
                    allWords.put(words[b], tempWords);
                }
            } else {
                if (allWords.containsKey(words[b])) {
                    allWords.put(words[b] + "1", tempWords);
                } else {
                    allWords.put(words[b], tempWords);
                }
            }
        }

        List<List<Integer>> firstWord = allWords.get(words[0]);

        //if one key word
        if (allWords.size() == 1) {
            for (int f = 0; f < firstWord.size(); f++) {
                if (firstWord.get(f) != null) {
                    if (!(firstWord.get(f).isEmpty())) {
                        result.add(f);
                    }
                }
            }
            return result;
        }

        //if more than 1 key word
        if (allWords.size() > 1) {
            for (int g = 1; g < allWords.size(); g++) {
                Map<Integer, List<Integer>> allDocs1 = new HashMap<>();
                Map<Integer, List<Integer>> allDocs2 = new HashMap<>();
                for (int h = 0; h < allWords.get(words[0]).size(); h++) {
                        if (allWords.get(words[0]).get(h) != null && allWords.get(words[g]).get(h) != null) {
                            if (!(allWords.get(words[0]).get(h).isEmpty()) && !(allWords.get(words[g]).get(h).isEmpty())) {
                                allDocs1.put(h, allWords.get(words[0]).get(h));
                                allDocs2.put(h, allWords.get(words[g]).get(h));
                            }
                        }
                }
                // if docs are null
                if (allDocs1.isEmpty() || allDocs2.isEmpty()) {
                    return result;
                }
                //if docs arent null
                for (int k = 0; k < emptyWords.size(); k++) {
                    LinkedList allDocs1Array = new LinkedList();
                    LinkedList allDocs2Array = new LinkedList();
                    if (allDocs1.containsKey(k)) {
                        for (int l = 0; l < allDocs1.get(k).size(); l++) {
                            allDocs1Array.add(allDocs1.get(k).get(l));
                        }
                        for (int n = 0; n < allDocs2.get(k).size(); n++) {
                            allDocs2Array.add(allDocs2.get(k).get(n));
                        }
                        for (int m = 0; m < allDocs2Array.size(); m++) {
                            int second = (int) allDocs2Array.get(m);
                            second = second - g;
                            for (int o = 0; o < allDocs1Array.size(); o++) {
                                if (second == (int) allDocs1Array.get(o)) {
                                    if (!(result.contains(k))) {
                                        result.add(k);
                                    }
                                    allDocs1Array = new LinkedList();
                                    allDocs2Array = new LinkedList();
                                } else {
                                    if (result.contains(k)) {
                                        result.remove(k);
                                    }
                                }
                            }
                        }
                    }
                }
                for (int z = 0; z < result.size(); z++) {
                    if (!(allDocs1.containsKey(result.get(z)))) {
                        result.remove(z);
                        z--;
                    }
                }
            }
            return result;
        }

        // if no key words
        return result;
    }
}

/*
Map<String, List<List<Integer>>> allWords = new HashMap<>();

        // empty list for if the word isnt in the index
        LinkedList emptyWords = new LinkedList();
        for(int a = 0; a < index.get("hello").size(); a++) {
            emptyWords.add(null);
        }

        //store all instances of each search word
        for (int b = 0; b < words.length; b++) {
            LinkedList tempWords = new LinkedList();
            if (index.containsKey(words[b])) {
                for (int c = 0; c < index.get(words[b]).size(); c++) {
                    tempWords.add(index.get(words[b]).get(c));
                }
                allWords.put(words[b], tempWords);
            } else {
                allWords.put(words[b], emptyWords);
            }
        }
 */



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