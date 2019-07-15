
package com.transactions.domainkernel;

public class PropertyRequiredException extends RuntimeException {
    private final String modelName;
    private final String propertyName;

    public PropertyRequiredException(String modelName, String propertyName) {
        this.modelName = modelName;
        this.propertyName = propertyName;
    }

    @Override
    public String getMessage() {
        return String.format( "Property %s of model %s must not be null !", this.propertyName, this.modelName );

    }
}
