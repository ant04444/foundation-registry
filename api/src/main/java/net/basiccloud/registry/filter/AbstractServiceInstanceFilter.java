package net.basiccloud.registry.filter;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Abstract ServiceInstanceFilter.
 */
public abstract class AbstractServiceInstanceFilter implements ServiceInstanceFilter {

    protected final String name;

    public AbstractServiceInstanceFilter(String name) {
        checkArgument(Strings.isNullOrEmpty(name), "name shold not be null or empty");
        this.name = name;
    }

    @Override
    public String getFilterName() {
        return this.name;
    }
}
