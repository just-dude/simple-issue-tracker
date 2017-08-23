
package controller.struts.customComponent.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;


public class ThreadContextSetterInterceptor extends AbstractInterceptor {
    
    private static final Logger LOG = LogManager.getLogger(ThreadContextSetterInterceptor.class);
            
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result;
        try{                   
            HttpParameters parameters = ActionContext.getContext().getParameters();
            ServletActionContext.getRequest().getRemoteAddr();
            LOG.debug("ThreadContextSetterInterceptor parameters: "+parameters);
            LOG.debug("ThreadContextSetterInterceptor client ip address: "+ServletActionContext.getRequest().getRemoteAddr());
            ThreadContext.put("ipAddress", ServletActionContext.getRequest().getRemoteAddr().toString());
//            try{
//                if(SecurityUtils.getSubject().getPrincipals()!=null){
//                    ThreadContext.put("subjectPrincipals",SecurityUtils.getSubject().getPrincipals().asList().toString());
//                } else{
//                    LOG.debug("UserAccount have not authenticated");
//                }
//            } catch(Exception e){
//                LOG.error("ThreadContextSetterInterceptor SecurityUtils error",e);
//            }
        } catch(Exception e){
            LOG.error("On set thread context exception have occured",e);
        }              
        result=invocation.invoke();       
        ThreadContext.clearAll();       
        return result;
    }
}
