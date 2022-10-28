package com.khanhhoang.simpledictionary.service;

import com.khanhhoang.simpledictionary.model.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryServiceImpl implements IDictionaryService {
    private static Map<Integer, Dictionary> dictionaries = new HashMap<>();

    static {
        dictionaries.put(1, new Dictionary("hello", "xin chào"));
        dictionaries.put(2, new Dictionary("dog", "con chó"));
        dictionaries.put(3, new Dictionary("cat", "con mèo"));
        dictionaries.put(4, new Dictionary("bye", "tạm biệt"));
        dictionaries.put(5, new Dictionary("goodbye", "tạm biệt"));
        dictionaries.put(6, new Dictionary("mouse", "con chuột"));
    }

    @Override
    public List<Dictionary> findAll() {
        return new ArrayList<>(dictionaries.values());
    }
}
