package com.khanhhoang.customermanagerprovincial.repository;

import com.khanhhoang.customermanagerprovincial.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
