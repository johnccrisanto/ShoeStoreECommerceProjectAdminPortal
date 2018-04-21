package com.johncrisanto.shoestoreadminportal.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.johncrisanto.shoestoreadminportal.entity.User;
import com.johncrisanto.shoestoreadminportal.entity.security.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	PasswordResetToken findByToken(String token);

	PasswordResetToken findByUser(User user);

	Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

	@Modifying
	@Query("delete from PasswordResetToken s where s.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);
}
