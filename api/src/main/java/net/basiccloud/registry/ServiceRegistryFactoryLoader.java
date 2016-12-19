package net.basiccloud.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.google.common.base.Preconditions.checkState;

/**
 * Loader to load factory implementation.
 */
public class ServiceRegistryFactoryLoader {

    private static Logger logger = LoggerFactory.getLogger(ServiceRegistryFactoryLoader.class);

    public static Optional<ServiceRegistryFactory> load() {
        List<ServiceRegistryFactory> factories = getAllImplementations();
        if (factories.isEmpty()) {
            logger.error("no implementation of ServiceRegistryFactory found in classpath");
            return Optional.empty();
        }

        if (factories.size() == 1) {
            logger.info("only one implementation of ServiceRegistryFactory found and actived");
            return Optional.of(factories.get(0));
        }

        return Optional.of(chooseHighestPriority(factories));
    }

    private static List<ServiceRegistryFactory> getAllImplementations() {
        List<ServiceRegistryFactory> factories = new ArrayList<>();
        ServiceLoader<ServiceRegistryFactory> loader = ServiceLoader.load(ServiceRegistryFactory.class);
        for (ServiceRegistryFactory factory : loader) {
            logger.info("find ServiceRegistryFactory implementation: name={}, priority={}, class={}",
                    factory.getName(), factory.getPriority(), factory.getClass().getName());
            factories.add(factory);
        }
        return factories;
    }

    private static ServiceRegistryFactory chooseHighestPriority(List<ServiceRegistryFactory> factories) {
        checkState(factories.size() > 1);

        ServiceRegistryFactory result = factories.get(0);
        for (int i = 1; i < factories.size(); i++) {
            ServiceRegistryFactory factory = factories.get(i);
            if (factory.getPriority() > result.getPriority()) {
                result = factory;
            }
        }

        logger.info("ServiceRegistryFactory implementation {} is finally activated by priority", result.getName());
        return result;
    }
}
