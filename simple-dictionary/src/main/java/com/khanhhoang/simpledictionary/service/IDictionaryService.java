package com.khanhhoang.simpledictionary.service;

import com.khanhhoang.simpledictionary.model.Dictionary;

import java.util.List;

public interface IDictionaryService {
    List<Dictionary> findAll();
}
