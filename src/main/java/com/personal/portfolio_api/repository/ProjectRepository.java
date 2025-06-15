package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
}
