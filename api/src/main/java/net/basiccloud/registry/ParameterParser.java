package net.basiccloud.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Parameter parser.
 */
public class ParameterParser {

    private static Logger logger = LoggerFactory.getLogger(ParameterParser.class);

    private static final String REGISTRY_URL = "registryUrl";

    private ParameterParser() {
        // no instance
    }

    public static Optional<String> parseRegistryUrl(Map<String, String> parameters) {
        return parseByNameOrPostfix(parameters, REGISTRY_URL);
    }

    public static Optional<String> parseByNameOrPostfix(Map<String, String> parameters,
                                String name) {
        Iterator<String> iterator = parameters.keySet().iterator();
        final String postfix = "." + name;
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.equals(name) || key.endsWith(postfix)) {
                String value = parameters.get(key);
                if (value != null && value.trim().length() > 0) {
                    logger.info("parsed {}: key={},value={}", name, key, value.trim());
                    return Optional.of(value);
                } else {
                    logger.error("invalid {}: key={},value={}", name, key, value);
                }

            }
        }

        logger.error("no parameter {} found：the key should be {} or end with '{}'",
                name, name, postfix);
        return Optional.empty();
    }
}
