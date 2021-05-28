package com.skennedy.reddit.client.common;

import com.skennedy.reddit.client.RedditWebApp;
import com.skennedy.reddit.client.authorization.model.Access;

public abstract class AuthedIntegrationTest {

    //TODO: Lmao no, put in other repo
    private static final String clientId = "0d6KhZQkdHGm9A";
    private static final String clientSecret = "_wM4AZtTKFyS6M3roRThEqzTWGCAUA";

    private static Access access = new Access(
            "11894640-cL8yMKlgrrW_C-EBD9PlCtktZnngaQ",
            "bearer",
            3600,
            "identity mysubreddits read submit vote",
            "11894640-akut8s5Y3NJbRbAMbnXOvejrwOuy3Q"
    );

    public RedditWebApp getClient() throws Exception {
        return new RedditWebApp(access, clientId, clientSecret).refresh();
    }

}
