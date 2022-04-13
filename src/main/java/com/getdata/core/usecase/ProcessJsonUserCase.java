package com.getdata.core.usecase;

import com.getdata.core.model.ApiResource;
import com.getdata.core.model.AuthorisationServers;
import com.getdata.core.model.Participant;
import com.getdata.core.model.ParticipantStatus;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ProcessJsonUserCase {

    public List<Participant> execute(final String participantJson) {
        log.info("Processing Json information...");
        final JsonArray jsonAsArray = JsonParser.parseString(participantJson).getAsJsonArray();
        final List<Participant> participantsFromJson = createParticipantFromJson(jsonAsArray);
        log.info("Process finish");
        return participantsFromJson;
    }

    private List<Participant> createParticipantFromJson(final JsonArray jsonAsArray) {
        final List<Participant> participants = new ArrayList<>();
        for (int i = 0; i < jsonAsArray.size(); i++) {

            final JsonObject participantJsonObject = (JsonObject) jsonAsArray.get(i);
            final String statusParticipant = openOrgDomainClaims(participantJsonObject);
            final String organisationName = String.valueOf(participantJsonObject.get("OrganisationName").getAsString());
            final String organisationId = String.valueOf(participantJsonObject.get("OrganisationId").getAsString());
            final List<AuthorisationServers> authorisationServers = openAuthorisationServers(participantJsonObject);

            authorisationServers.forEach(authorisationServer -> {

                final Participant participant = Participant.builder()
                        .organisationId(organisationId)
                        .status(ParticipantStatus.convert(statusParticipant))
                        .organisationName(organisationName)
                        .customerFriendlyName(authorisationServer.getCustomerFriendlyName())
                        .customerFriendlyLogoUri(authorisationServer.getCustomerFriendlyLogoUri())
                        .apiResources(authorisationServer.getApiResources())
                        .build();

                participants.add(participant);
            });

        }
        return participants;
    }

    private List<String> getApiDiscoveryEndpoints(final JsonElement apiResources) {
        final JsonArray apis = apiResources.getAsJsonObject().get("ApiDiscoveryEndpoints").getAsJsonArray();
        final List<String> endPoints = new ArrayList<>();
        for (int a = 0; a < apis.size(); a++) {
            endPoints.add(apis.get(a).getAsJsonObject().get("ApiEndpoint").getAsString());
        }
        return endPoints;
    }

    private List<AuthorisationServers> openAuthorisationServers(final JsonObject participantJsonObject) {
        final List<AuthorisationServers> authorisationServersList = new ArrayList<>();
        final JsonArray authorization = participantJsonObject.getAsJsonArray("AuthorisationServers");

        for (int i = 0; i < authorization.size(); i++) {
            final List<ApiResource> apiResources = getApiResources(authorization.get(i));
            final String customerFriendlyName = authorization.get(i).getAsJsonObject().get("CustomerFriendlyName").getAsString();
            final String customerFriendlyLogoUri = authorization.get(i).getAsJsonObject().get("CustomerFriendlyLogoUri").getAsString();

            final AuthorisationServers authorisationServers = AuthorisationServers.builder()
                    .customerFriendlyName(customerFriendlyName)
                    .customerFriendlyLogoUri(customerFriendlyLogoUri)
                    .apiResources(apiResources)
                    .build();

            authorisationServersList.add(authorisationServers);
        }
        return authorisationServersList;
    }

    private List<ApiResource> getApiResources(final JsonElement authorisationServers) {
        final JsonArray apiResources = authorisationServers.getAsJsonObject().get("ApiResources").getAsJsonArray();
        final List<ApiResource> apis = new ArrayList<>();
        for (int a = 0; a < apiResources.size(); a++) {

            final String apiFamilyType = apiResources.get(a).getAsJsonObject().get("ApiFamilyType").getAsString();
            final String apiVersion = apiResources.get(a).getAsJsonObject().get("ApiVersion").getAsString();
            final List<String> apiEndpoints = getApiDiscoveryEndpoints(apiResources.get(a));
            final ApiResource apiResource = ApiResource.builder()
                    .apiFamilyType(apiFamilyType)
                    .apiVersion(apiVersion)
                    .apiEndpoint(apiEndpoints)
                    .build();

            apis.add(apiResource);

        }
        return apis;
    }

    private String openOrgDomainClaims(final JsonObject participantJsonObject) {
        final JsonArray orgDomainClaims = participantJsonObject.getAsJsonArray("OrgDomainClaims");

        if (orgDomainClaims != null) {
            for (int i = 0; i < orgDomainClaims.size(); i++) {
                return orgDomainClaims.get(i).getAsJsonObject().get("Status").getAsString();
            }
        }
        return null;
    }

}
