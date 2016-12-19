package net.basiccloud.registry;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ServiceRegistryFactoryLoaderTest {

    @Test
    public void testLoad() throws Exception {
        Optional<ServiceRegistryFactory> factoryOptional = ServiceRegistryFactoryLoader.load();
        assertThat(factoryOptional.isPresent()).isTrue();
        ServiceRegistryFactory factory = factoryOptional.get();
        //System.out.printf("factory=" + factory.getClass().getName());
        Assertions.assertThat(factory.getName()).isEqualTo("mock1");
    }


    public static class ServiceRegistryFactoryMock1 implements ServiceRegistryFactory {

        @Override
        public String getName() {
            return "mock1";
        }

        @Override
        public int getPriority() {
            return 10;
        }

        @Override
        public ServiceRegistryConnection connect(Map<String, String> connParameters) {
            return null;
        }

        @Override
        public ServiceRegistryClient getClient(ServiceRegistryConnection conn) {
            return null;
        }

        @Override
        public ServiceRegistryServer getServer(ServiceRegistryConnection conn) {
            return null;
        }

        @Override
        public ServiceRegistryAdmin getAdmin(ServiceRegistryConnection conn) {
            return null;
        }
    }

    public static class ServiceRegistryFactoryMock2 implements ServiceRegistryFactory {

        @Override
        public String getName() {
            return "mock2";
        }

        @Override
        public int getPriority() {
            return 5;
        }

        @Override
        public ServiceRegistryConnection connect(Map<String, String> connParameters) {
            return null;
        }

        @Override
        public ServiceRegistryClient getClient(ServiceRegistryConnection conn) {
            return null;
        }

        @Override
        public ServiceRegistryServer getServer(ServiceRegistryConnection conn) {
            return null;
        }

        @Override
        public ServiceRegistryAdmin getAdmin(ServiceRegistryConnection conn) {
            return null;
        }
    }
}