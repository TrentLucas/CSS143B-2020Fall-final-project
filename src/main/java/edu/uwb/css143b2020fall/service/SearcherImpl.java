package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        Map<String, List<List<Integer>>> indexesOfWords = new HashMap<>();

        // splits keyPhrase
        String[] words = keyPhrase.split("\\s+");

        // checks to see if keyPhrase words are in index
        // creates emptyDocs for words that are not in index
        LinkedList emptyDocs = new LinkedList();

        for (int a = 0; a < words.length; a++) {
            if (index.containsKey(words[a])) {
                for (int b = 0; b < index.get(words[a]).size(); b++) {
                    emptyDocs.add(null);
                }
            } else {
                return result;
            }
        }

        // stores all instances of the keyPhrase words
        for (int c = 0; c < words.length; c++) {
            LinkedList tempWords = new LinkedList();

            if (index.containsKey(words[c])) {
                for (int d = 0; d < index.get(words[c]).size(); d++) {
                    tempWords.add(index.get(words[c]).get(d));
                }
            }
            if (indexesOfWords.containsKey(words[c])) {
                indexesOfWords.put(words[c] + "~", tempWords);
            } else {
                indexesOfWords.put(words[c], tempWords);
            }
        }

        // all parts below are for the searcher

        // if the keyPhrase is 1 word
        if (words.length == 1) {
            List<List<Integer>> wordPointer = indexesOfWords.get(words[0]);
            for (int e = 0; e < wordPointer.size(); e++) {
                if (wordPointer.get(e) != null) {
                    if (!(wordPointer.get(e).isEmpty())) {
                        result.add(e);
                    }
                }
            }
            return result;
        }



        // if the keyPhrase is more than 1 word
        if (words.length > 1) {
            for (int f = 1; f < indexesOfWords.size(); f++) {
                Map<Integer, List<Integer>> docsForFirstWord = new HashMap<>();
                Map<Integer, List<Integer>> docsForSecondWord = new HashMap<>();

                for (int g = 0; g < indexesOfWords.get(words[0]).size(); g++) {
                    List<Integer> firstWordPointer = indexesOfWords.get(words[0]).get(g);
                    List<Integer> secondWordPointer = indexesOfWords.get(words[f]).get(g);

                        if (firstWordPointer != null && secondWordPointer != null) {
                            if (!(firstWordPointer.isEmpty()) && !(secondWordPointer.isEmpty())) {
                                docsForFirstWord.put(g, firstWordPointer);
                                docsForSecondWord.put(g, secondWordPointer);
                            }
                        }
                }

                // checker for if any docs are null
                if (docsForFirstWord.isEmpty() || docsForSecondWord.isEmpty()) {
                    return result;
                }

                // searcher algorithm to see if the keyPhrase is in the document
                for (int h = 0; h < emptyDocs.size(); h++) {
                    LinkedList docPointerForFirstWord = new LinkedList();
                    LinkedList docPointerForSecondWord = new LinkedList();

                    if (docsForFirstWord.containsKey(h)) {
                        for (int i = 0; i < docsForFirstWord.get(h).size(); i++) {
                            docPointerForFirstWord.add(docsForFirstWord.get(h).get(i));
                        }
                        for (int j = 0; j < docsForSecondWord.get(h).size(); j++) {
                            docPointerForSecondWord.add(docsForSecondWord.get(h).get(j));
                        }

                        for (int k = 0; k < docPointerForSecondWord.size(); k++) {
                            int location = (int) docPointerForSecondWord.get(k);
                            location -= f;

                            for (int l = 0; l < docPointerForFirstWord.size(); l++) {

                                //adding documents to result
                                if (location == (int) docPointerForFirstWord.get(l)) {
                                    if (!(result.contains(h))) {
                                        result.add(h);
                                    }
                                    docPointerForFirstWord = new LinkedList();
                                    docPointerForSecondWord = new LinkedList();
                                } else {
                                    if (result.contains(h)) {
                                        result.remove(h);
                                    }
                                }
                            }
                        }
                    }
                }

                // checker for if previous results from searcher are correct
                for (int m = 0; m < result.size(); m++) {
                    if (!(docsForFirstWord.containsKey(result.get(m)))) {
                        result.remove(m);
                        m--;
                    }
                }
            }
            return result;
        }



        // if keyPhrase has no words
        return result;
    }
}