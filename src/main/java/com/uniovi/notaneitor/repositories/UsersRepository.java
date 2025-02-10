package com.uniovi.notaneitor.repositories;
import com.uniovi.notaneitor.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long>{
    User findByDni(String dni);
}