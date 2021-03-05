package com.crm.crm.repos;

import com.crm.crm.models.Students;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Students, Long> {
}
