package com.khanhhoang.phonemanagement.repository;

import com.khanhhoang.phonemanagement.model.Smartphone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartphoneRepository extends CrudRepository<Smartphone, Long> {
}
