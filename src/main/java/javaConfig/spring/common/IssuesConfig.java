package javaConfig.spring.common;

import dao.common.Dao;
import dao.common.SimpleJpaDao;
import dao.common.SoftDeletedModedJpaDao;
import dao.issues.IssueStatesJpaDao;
import dao.issues.IssuesJpaDao;
import domain.common.Finder;
import domain.common.SimpleFinder;
import domain.issues.Issue;
import domain.issues.IssueState;
import domain.issues.IssueType;
import domain.issues.IssuesFinder;
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
    public IssuesJpaDao issuesDao() {
        return new IssuesJpaDao(entityManager);
    }

    @Bean
    public IssuesJpaDao issuesWithoutSoftDeletedDao() {
        return new IssuesJpaDao(entityManager, SoftDeletedModedJpaDao.FindMode.WithoutSoftDeleted);
    }

    @Bean
    public Finder<Issue,Long> issuesFinder(IssuesJpaDao issuesDao) {
        return new IssuesFinder(issuesDao);
    }

    @Bean
    public Finder<Issue,Long> issuesWithoutSoftDeletedFinder(IssuesJpaDao issuesWithoutSoftDeletedDao) {
        return new IssuesFinder(issuesWithoutSoftDeletedDao);
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
    public Dao<IssueType,Long> issueTypesDao() {
        return new SoftDeletedModedJpaDao<IssueType,Long>(entityManager,IssueType.class);
    }

    @Bean
    public Dao<IssueType,Long> issueTypesWithoutSoftDeletedDao() {
        return new SoftDeletedModedJpaDao<IssueType,Long>(entityManager,IssueType.class, SoftDeletedModedJpaDao.FindMode.WithoutSoftDeleted);
    }

    @Bean
    public Finder<IssueType,Long> issueTypesFinder(Dao<IssueType,Long> issueTypesDao) {
        return new SimpleFinder<IssueType,Long>(issueTypesDao,IssueType.class);
    }

    @Bean
    public Finder<IssueType,Long> issueTypesWithoutSoftDeletedFinder(Dao<IssueType,Long> issueTypesWithoutSoftDeletedDao) {
        return new SimpleFinder<IssueType,Long>(issueTypesWithoutSoftDeletedDao,IssueType.class);
    }

    @Bean
    public EntityValidatorFactory<IssueType> issueTypeOnCreateValidatorFactory() {
        return new IssueTypeValidatorFactory.OnCreate(new ValidationContext("issueType"));
    }

    @Bean
    public EntityValidatorFactory<IssueType> issueTypeOnUpdateValidatorFactory() {
        return new IssueTypeValidatorFactory.OnUpdate(new ValidationContext("issueType"));
    }

    // IssueState

    @Bean
    public Dao<IssueState,Long> issueStatesDao() {
        return new IssueStatesJpaDao(entityManager);
    }

    @Bean
    public Dao<IssueState,Long> issueStatesWithoutSoftDeletedDao() {
        return new IssueStatesJpaDao(entityManager, SoftDeletedModedJpaDao.FindMode.WithoutSoftDeleted);
    }

    @Bean
    public Finder<IssueState,Long> issueStatesFinder(Dao issueStatesDao) {
        return new SimpleFinder<IssueState,Long>(issueStatesDao,IssueType.class);
    }

    @Bean
    public Finder<IssueState,Long> issueStatesWithoutSoftDeletedFinder(Dao issueStatesWithoutSoftDeletedDao) {
        return new SimpleFinder<IssueState,Long>(issueStatesWithoutSoftDeletedDao,IssueType.class);
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
