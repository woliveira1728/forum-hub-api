package com.woliveira.forum_hub_challenger.repository;


import com.woliveira.forum_hub_challenger.model.TopicResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TopicResponsesRepository extends JpaRepository<TopicResponses, Long> {
}
