package br.com.cepedi.Voll.api.audit.interceptor;

import br.com.cepedi.Voll.api.audit.record.DataRegisterAudit;
import br.com.cepedi.Voll.api.audit.service.AuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import br.com.cepedi.Voll.api.security.model.entitys.User;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AuditService auditService;

    private static ThreadLocal<String> clientIpAddress = new ThreadLocal<>();

    public static void setClientIpAddress(String ipAddress) {
        clientIpAddress.set(ipAddress);
    }

    public static void clearClientIpAddress() {
        clientIpAddress.remove();
    }

    @Before("execution(* br.com.cepedi.Voll.api.services..*(..)) || execution(* br.com.cepedi.Voll.api.security.service..*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String description = "Method execution";
        String username = null;
        Long userId = null;
        String origin = clientIpAddress.get();

        // Recupera informações do usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            userId = user.getId();
            username = user.getUsername();
        }

        DataRegisterAudit dataRegisterAudit = new DataRegisterAudit(methodName, description, userId, username, joinPoint.getTarget().getClass().getSimpleName(), origin);

        auditService.logEvent(dataRegisterAudit);
    }
}
