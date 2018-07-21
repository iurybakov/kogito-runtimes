/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.testcoverage.common.util;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.KieBaseOption;

import static java.util.Arrays.asList;
import static org.drools.testcoverage.common.util.KieBaseModelProvider.RunType.FLOW_DSL;
import static org.drools.testcoverage.common.util.KieBaseModelProvider.RunType.FLOW_WITH_ALPHA_NETWORK;
import static org.drools.testcoverage.common.util.KieBaseModelProvider.RunType.PATTERN_DSL;
import static org.drools.testcoverage.common.util.KieBaseModelProvider.RunType.PATTERN_WITH_ALPHA_NETWORK;
import static org.drools.testcoverage.common.util.KieBaseModelProvider.RunType.STANDARD_WITH_ALPHA_NETWORK;

/**
 * Basic provider class for KieBaseModel instances.
 */
public interface KieBaseModelProvider {
    KieBaseModel getKieBaseModel(KieModuleModel kieModuleModel);
    KieBaseConfiguration getKieBaseConfiguration();
    RunType runType();
    void setAdditionalKieBaseOptions(KieBaseOption... options);
    boolean isIdentity();
    boolean isStreamMode();

    enum RunType {
        FLOW_DSL,
        PATTERN_DSL,
        STANDARD_FROM_DRL,
        STANDARD_WITH_ALPHA_NETWORK,
        PATTERN_WITH_ALPHA_NETWORK,
        FLOW_WITH_ALPHA_NETWORK
    }

    default boolean useCanonicalModel() {
        return asList(FLOW_DSL, PATTERN_DSL, FLOW_WITH_ALPHA_NETWORK, PATTERN_WITH_ALPHA_NETWORK).contains(runType());
    }

    default boolean useAlphaNetwork() {
        return asList(STANDARD_WITH_ALPHA_NETWORK, FLOW_WITH_ALPHA_NETWORK,PATTERN_WITH_ALPHA_NETWORK).contains(runType());
    }
}
