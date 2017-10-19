
package controller.struts.action.common;


import controller.struts.action.common.util.ActionUtils;
import domain.common.Finder;
import domain.common.HavingNameAndOneIdDomainObject;
import domain.common.exception.*;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static common.beanFactory.BeanFactoryProvider.getBeanFactory;


public abstract class FormProviderHandlingExceptionsBaseAction extends HandlingExceptionsBaseAction {


    @Override
    public String execute() throws Exception {
        try {
            clearMessages();
            getLogger().debug("HandlingExceptionsBaseAction start execute");
            doExecute();
            getLogger().debug("HandlingExceptionsBaseAction executed success " + ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS));
            addActionMessage(getText(CustomResults.SUCCESS));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.SUCCESS);
        } catch (ValidationFailedException e) {
            getLogger().debug("INVALID_USER_INPUT result", e);
            fillActionErrors(e.getConstraintsViolations());
            addActionError(getText(CustomResults.INVALID_USER_INPUT));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INVALID_USER_INPUT);
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            getLogger().debug("RESOURCE_WITH_SUCH_ID_NOT_EXISTS result", e);
            addActionError(getText(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS);
        } catch (DataIntegrityViolationBusinessException e) {
            getLogger().debug("DATA_INTEGRITY_VIOLATION result", e);
            addActionError(getText(CustomResults.DATA_INTEGRITY_VIOLATION));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.DATA_INTEGRITY_VIOLATION);
        } catch (AuthorizationFailedException e) {
            getLogger().debug("AUTHORIZATION_ERROR result", e);
            addActionError(getText(CustomResults.AUTHORIZATION_ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.AUTHORIZATION_ERROR);
        } catch (UnchangingContentConstraintViolationException e) {
            getLogger().debug("UNCHANGING_CONTENT_CONSTRAINT_VIOLATION result", e);
            addActionError(getText(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.UNCHANGING_CONTENT_CONSTRAINT_VIOLATION);
        } catch (Exception e) {
            getLogger().debug("ERROR result");
            getLogger().error("Unknown exception is occured", e);
            addActionError(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        }
    }

    @Override
    public String input() throws Exception {
        try {
            clearMessages();
            getLogger().debug("FormProviderHandlingExceptionsBaseAction input start execute");
            doInput();
            getLogger().debug("FormProviderHandlingExceptionsBaseAction input has executed");
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.INPUT);
        } catch (EntityWithSuchIdDoesNotExistsBusinessException e) {
            getLogger().debug("RESOURCE_WITH_SUCH_ID_NOT_EXISTS result", e);
            addActionError(getText(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.RESOURCE_WITH_SUCH_ID_NOT_EXISTS);
        } catch (AuthorizationFailedException e) {
            getLogger().debug("AUTHORIZATION_ERROR result");
            addActionError(getText(CustomResults.AUTHORIZATION_ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.AUTHORIZATION_ERROR);
        } catch (Exception e) {
            getLogger().debug("ERROR result");
            getLogger().error("Unknown exception is occured", e);
            addActionError(getText(CustomResults.ERROR));
            return ActionUtils.getDecoratedByViewSettingsResult(CustomResults.ERROR);
        }
    }

    protected void doInput() throws Exception {
    }

    public Map<String, String> getEntitiesIdWithNamesMapByFinderName(String finderName) {
        Map<String, String> entitiesIdWithNamesMap = null;
        try {
            Finder entitiesFinder = (Finder) getBeanFactory().getBean(finderName);
            List allEntities = entitiesFinder.findAll(new Sort(Sort.Direction.ASC, "name"));
            entitiesIdWithNamesMap = new LinkedHashMap(allEntities.size());
            for (Object entity : allEntities) {
                entitiesIdWithNamesMap.put(((HavingNameAndOneIdDomainObject) entity).getId().toString(), ((HavingNameAndOneIdDomainObject) entity).getName());
            }
        } catch (Exception e) {
            entitiesIdWithNamesMap = new LinkedHashMap(0);
            getLogger().debug("Exception has occured on FormProviderHandlingExceptionsBaseAction.getEntitiesIdWithNamesMapByFinderName", e);
        }
        return entitiesIdWithNamesMap;
    }


}
