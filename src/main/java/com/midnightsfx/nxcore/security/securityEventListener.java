package com.midnightsfx.nxcore.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by MidnightsFX on 7/28/17.
 */
@Component
public class securityEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(securityEventListener.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public securityEventListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener(condition = "#event.auditEvent.type != 'CUSTOM_AUDIT_EVENT'")
    @Async
    public void onAuditEvent(AuditApplicationEvent event) {
        AuditEvent actualAuditEvent = event.getAuditEvent();

        LOG.info("On audit application event: timestamp: {}, principal: {}, type: {}, data: {}",
                actualAuditEvent.getTimestamp(),
                actualAuditEvent.getPrincipal(),
                actualAuditEvent.getType(),
                actualAuditEvent.getData()
        );
        applicationEventPublisher.publishEvent(
                new AuditApplicationEvent(
                        new AuditEvent(actualAuditEvent.getPrincipal(), "CUSTOM_AUDIT_EVENT")
                )
        );
    }

    @EventListener(condition = "#event.auditEvent.type == 'CUSTOM_AUDIT_EVENT'")
    @Async
    public void onCustomAuditEvent(AuditApplicationEvent event) {
        LOG.info("Handling custom audit event ...");
    }

}
