package net.basiccloud.registry;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterParserTest {
    private static String registryUrl = "etcd://192.1680.1:9080";

    @Test
    public void parseRegistryUrl() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("registryUrl", registryUrl);
        parameters.put("a", "aaa");
        parameters.put("b", "bbb");
        Optional<String> registryUrlOptional = ParameterParser.parseRegistryUrl(parameters);
        assertThat(registryUrlOptional.isPresent()).isTrue();
        String registryUrl = registryUrlOptional.get();
        assertThat(registryUrl).isEqualTo(registryUrl);
    }

    @Test
    public void parseRegistryUrl_postfix() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("light.server.registryUrl", registryUrl);
        parameters.put("a", "aaa");
        parameters.put("b", "bbb");
        Optional<String> registryUrlOptional = ParameterParser.parseRegistryUrl(parameters);
        assertThat(registryUrlOptional.isPresent()).isTrue();
        String registryUrl = registryUrlOptional.get();
        assertThat(registryUrl).isEqualTo(registryUrl);
    }

    @Test
    public void parseRegistryUrl_emptyValue() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("light.server.registryUrl", "");
        parameters.put("a", "aaa");
        parameters.put("b", "bbb");
        Optional<String> registryUrlOptional = ParameterParser.parseRegistryUrl(parameters);
        assertThat(registryUrlOptional.isPresent()).isFalse();
    }

    @Test
    public void parseRegistryUrl_noData() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("a", "aaa");
        parameters.put("b", "bbb");
        Optional<String> registryUrlOptional = ParameterParser.parseRegistryUrl(parameters);
        assertThat(registryUrlOptional.isPresent()).isFalse();
    }
}