package com.ryan.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

public class OauthUserApprovalHandler extends TokenServicesUserApprovalHandler {

    private OauthService oauthService;

    public OauthUserApprovalHandler() {
    	
    }


    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        OauthClientDetails clientDetails = oauthService.loadOauthClientDetails(authorizationRequest.getClientId());
        return (clientDetails != null && clientDetails.trusted());

    }

    public void setOauthService(OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
