/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.clustering.infinispan.subsystem;

import org.jboss.as.naming.deployment.JndiName;

/**
 * @author Paul Ferraro
 */
class InfinispanJndiName {

    private static final String DEFAULT_JNDI_NAMESPACE = "java:jboss";

    private static JndiName defaultCacheContainerJndiName(String containerName) {
        return JndiName.of(DEFAULT_JNDI_NAMESPACE).append(InfinispanExtension.SUBSYSTEM_NAME).append("container").append(containerName);
    }

    private static JndiName defaultCacheJndiName(String containerName, String cacheName) {
        // Append to container jdni, as cache names only have to be unique per container
        return defaultCacheContainerJndiName(containerName).append("cache").append(cacheName);
    }

    private static JndiName toJndiName(String value) {
        return value.startsWith("java:") ? JndiName.of(value) : JndiName.of(DEFAULT_JNDI_NAMESPACE).append(value.startsWith("/") ? value.substring(1) : value);
    }

    static String createCacheJndiName(String jndiNameString, String containerName, String cacheName) {
        JndiName jndiName = (jndiNameString != null) ? InfinispanJndiName.toJndiName(jndiNameString) : InfinispanJndiName.defaultCacheJndiName(containerName, cacheName);
        return jndiName.getAbsoluteName();
    }

    static String createCacheContainerJndiName(String containerName) {
        return InfinispanJndiName.defaultCacheContainerJndiName(containerName).getAbsoluteName();
    }
}
