package com.doran.redis.invite.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.invite.key.Invite;

@Repository
public interface InviteRepository extends CrudRepository<Invite, String> {

    Optional<Invite> findInviteByUserId(int userId);
}
