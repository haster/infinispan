package org.infinispan.persistence.remote.configuration;

import org.infinispan.commons.configuration.AbstractTypedPropertiesConfiguration;
import org.infinispan.commons.configuration.attributes.AttributeDefinition;
import org.infinispan.commons.configuration.attributes.AttributeSet;
import org.infinispan.commons.executors.ExecutorFactory;
import org.infinispan.executors.DefaultExecutorFactory;

public class ExecutorFactoryConfiguration extends AbstractTypedPropertiesConfiguration {
   static final AttributeDefinition<ExecutorFactory> EXECUTOR_FACTORY = AttributeDefinition.builder("executorFactory", null, ExecutorFactory.class)
         .initializer(DefaultExecutorFactory::new).immutable().build();

   static AttributeSet attributeDefinitionSet() {
      return new AttributeSet(ExecutorFactoryConfiguration.class, AbstractTypedPropertiesConfiguration.attributeSet(), EXECUTOR_FACTORY);
   };

   ExecutorFactoryConfiguration(AttributeSet attributes) {
      super(attributes);
   }

   public ExecutorFactory factory() {
      return attributes.attribute(EXECUTOR_FACTORY).get();
   }

   @Override
   public String toString() {
      return "ExecutorFactoryConfiguration [attributes=" + attributes + "]";
   }

   AttributeSet attributes() {
      return attributes;
   }

}
