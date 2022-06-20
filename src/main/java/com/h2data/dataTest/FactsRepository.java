package com.h2data.dataTest;

import org.springframework.data.repository.CrudRepository;
import com.h2data.dataTest.Facts;

public interface FactsRepository extends CrudRepository<Facts,Integer> {

}
