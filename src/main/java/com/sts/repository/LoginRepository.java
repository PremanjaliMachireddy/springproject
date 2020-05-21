package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sts.model.UserLoginForm;

@Repository
public interface LoginRepository extends JpaRepository<UserLoginForm, Long> {

	@Query("SELECT count(*) from UserLoginForm login WHERE login.username=?1 AND login.password=?2")
	int getCount(String username, String password);

}
