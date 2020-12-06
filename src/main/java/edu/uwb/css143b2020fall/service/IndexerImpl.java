package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        LinkedList allWords = new LinkedList();

        //gather every word in all docs
        for (int i = 0; i < docs.size(); i++) {
            String doc = docs.get(i);
            String[] words = doc.split("\\s+");

            for (int j = 0; j < words.length; j++) {
                if (!allWords.contains(words[j]) && !words[j].equals("")) {
                    allWords.add(words[j]);
                }
            }
        }

        //add values to words              word -> document -> location
        for (int k = 0; k < allWords.size(); k++) {
            List<List<Integer>> allDocs = new ArrayList();
            for (int l = 0; l < docs.size(); l++) {
                List<Integer> tempDoc = new ArrayList();
                String doc = docs.get(l);
                String[] words = doc.split("\\s+");
                LinkedList wordsNew = new LinkedList();

                //remove empty splits
                for (int z = 0; z < words.length; z++) {
                    if (!(words[z].equals(""))) {
                        wordsNew.add(words[z]);
                    }
                }

                //set tempdoc to values
                for (int n = 0; n < wordsNew.size(); n++) {
                    if (allWords.get(k).equals(wordsNew.get(n))) {
                        tempDoc.add(n);
                    }
                }
                allDocs.add(tempDoc);
            }
            indexes.put((String) allWords.get(k), allDocs);
        }

        return indexes;
    }
}