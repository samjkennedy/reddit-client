package com.skennedy.reddit.client.common;

import com.skennedy.reddit.client.RedditWebApp;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;

import java.util.Arrays;

public abstract class AuthedIntegrationTest {

    //TODO: Lmao no, put in other repo
    private static final String clientId = "0d6KhZQkdHGm9A";
    private static final String clientSecret = "_wM4AZtTKFyS6M3roRThEqzTWGCAUA";

    private static Access access = new Access(
            "11894640-j0Hz5xqoem0EagvYXtf3m3Ti2ag7cw",
            "bearer",
            3600,
            Arrays.asList(OAuthScope.HISTORY, OAuthScope.IDENTITY, OAuthScope.MYSUBREDDITS, OAuthScope.READ, OAuthScope.SUBMIT, OAuthScope.VOTE, OAuthScope.SUBSCRIBE, OAuthScope.MYSUBREDDITS),
            "11894640--V1RV8gTDMhYUO4A0KxZOsRWKlDmnw"
    );

    public RedditWebApp getClient() throws Exception {
        return new RedditWebApp(access, clientId, clientSecret).refresh();
    }

}
