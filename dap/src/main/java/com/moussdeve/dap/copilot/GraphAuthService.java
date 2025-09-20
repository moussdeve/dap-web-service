//*****************************************************************************************************************************************************************************
// * 
// * 
// * 
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.copilot;

import org.springframework.stereotype.Component;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;

@Component
public class GraphAuthService {
    public String getAccessToken() {
        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
            .clientId("<YOUR_CLIENT_ID>")
            .clientSecret("<YOUR_CLIENT_SECRET>")
            .tenantId("<YOUR_TENANT_ID>")
            .build();

        return credential.getToken(new com.azure.core.credential.TokenRequestContext()
            .addScopes("https://graph.microsoft.com/.default"))
            .block()
            .getToken();
    }
}
