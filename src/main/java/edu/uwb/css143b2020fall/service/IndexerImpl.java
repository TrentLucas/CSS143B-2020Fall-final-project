package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        LinkedList allWords = new LinkedList();

        //gather every word in all docs
        for (int a = 0; a < docs.size(); a++) {
            String doc = docs.get(a);
            String[] words = doc.split("\\s+");

            for (int b = 0; b < words.length; b++) {
                if (!allWords.contains(words[b]) && !words[b].equals("")) {
                    allWords.add(words[b]);
                }
            }
        }

        //add values to words              word -> document -> location
        for (int c = 0; c < allWords.size(); c++) {
            List<List<Integer>> allDocs = new ArrayList();

            for (int d = 0; d < docs.size(); d++) {
                List<Integer> tempDoc = new ArrayList();
                String doc = docs.get(d);
                String[] words = doc.split("\\s+");
                LinkedList tempDocWords = new LinkedList();

                //remove empty splits
                for (int e = 0; e < words.length; e++) {
                    if (!(words[e].equals(""))) {
                        tempDocWords.add(words[e]);
                    }
                }

                //set tempDoc to values
                for (int f = 0; f < tempDocWords.size(); f++) {
                    if (allWords.get(c).equals(tempDocWords.get(f))) {
                        tempDoc.add(f);
                    }
                }
                allDocs.add(tempDoc);
            }
            indexes.put((String) allWords.get(c), allDocs);
        }

        return indexes;
    }
}