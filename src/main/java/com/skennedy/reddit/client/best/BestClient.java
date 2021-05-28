package com.skennedy.reddit.client.best;

import com.skennedy.reddit.client.best.request.BestRequest;

public interface BestClient {
    BestRequest best() throws IllegalAccessException;
}
