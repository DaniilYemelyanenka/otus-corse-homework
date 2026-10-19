package by.yemelyanenko;

import java.util.*;

public class WordArray {

    private final String[] words;

    public WordArray(String[] args){
        this.words = args;
    }

    public List<String>  findUnique(){
        Set<String> uniqueWords = new HashSet<>();
        for(String word : words){
            uniqueWords.add(word);
        }
        return new ArrayList<>(uniqueWords);
    }

    public Map<String,Integer> findWordFrequencies(){
        Map<String,Integer> wordFrequenciesMap= new HashMap<>();
        for(String word : words){
            wordFrequenciesMap.merge(word,1,Integer::sum);
        }
        return wordFrequenciesMap;
    }
}
