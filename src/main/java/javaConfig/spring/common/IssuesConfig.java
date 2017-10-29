package javaConfig.spring.common;

import dao.common.JpaDao;
import dao.issues.IssueStatesJpaDao;
import domain.common.Finder;
import domain.common.SimpleFinder;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.issues.IssueStatesFinder;
import domain.issues.IssueType;
import domain.issues.validation.IssueStateValidatorFactory;
import domain.issues.validation.IssueTypeValidatorFactory;
import domain.issues.validation.IssueValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smartvalidation.validator.entityValidator.EntityValidatorFactory;
import smartvalidation.validator.entityValidator.ValidationContext;

import javax.persistence.EntityManager;


@Configuration
public class IssuesConfig {

    @Autowired
    EntityManager entityManager;

    // Issue

    @Bean
    public JpaDao<Issue,Long> issuesDao() {
        return new JpaDao<Issue,Long>(entityManager,Issue.class);
    }

    @Bean
    public Finder<Issue,Long> issuesFinder(JpaDao<Issue,Long> issuesDao) {
        return new SimpleFinder<Issue,Long>(issuesDao);
    }

    @Bean
    public EntityValidatorFactory<Issue> issueOnCreateValidatorFactory() {
        return new IssueValidatorFactory.OnCreate(new ValidationContext("issue"));
    }

    @Bean
    public EntityValidatorFactory<Issue> issueOnUpdateValidatorFactory() {
        return new IssueValidatorFactory.OnUpdate(new ValidationContext("issue"));
    }

    // IssueType

    @Bean
    public JpaDao<IssueType,Long> issueTypesDao() {
        return new JpaDao<IssueType,Long>(entityManager,IssueType.class);
    }

    @Bean
    public Finder<IssueType,Long> issueTypesFinder(JpaDao<IssueType,Long> issueTypesDao) {
        return new SimpleFinder<IssueType,Long>(issueTypesDao);
    }

    @Bean
    public EntityValidatorFactory<IssueType> issueTypeOnCreateValidatorFactory() {
        return new IssueTypeValidatorFactory.OnCreate(new ValidationContext("issueTypes"));
    }

    @Bean
    public EntityValidatorFactory<IssueType> issueTypeOnUpdateValidatorFactory() {
        return new IssueTypeValidatorFactory.OnUpdate(new ValidationContext("issueTypes"));
    }

    // IssueState

    @Bean
    public IssueStatesJpaDao issueStatesDao() {
        return new IssueStatesJpaDao(entityManager);
    }

    @Bean
    public Finder<IssueState,Long> issueStatesFinder(IssueStatesJpaDao issueStatesDao) {
        return new IssueStatesFinder(issueStatesDao);
    }

    @Bean
    public EntityValidatorFactory<IssueState> issueStateOnCreateValidatorFactory() {
        return new IssueStateValidatorFactory.OnCreate(new ValidationContext("issueState"));
    }

    @Bean
    public EntityValidatorFactory<IssueState> issueStateOnUpdateValidatorFactory() {
        return new IssueStateValidatorFactory.OnUpdate(new ValidationContext("issueState"));
    }

}
