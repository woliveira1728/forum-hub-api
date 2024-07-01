package com.woliveira.forum_hub_api.repository;


import com.woliveira.forum_hub_api.model.TopicResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicResponsesRepository extends JpaRepository<TopicResponses, UUID> {
}
