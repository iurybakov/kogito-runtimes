/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.codegen.sample.generator.config;

import org.drools.codegen.common.GeneratedFile;
import org.kie.kogito.codegen.api.ConfigGenerator;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.template.TemplatedGenerator;

import com.github.javaparser.ast.CompilationUnit;

public class SampleConfigGenerator implements ConfigGenerator {

    private final TemplatedGenerator generator;

    public SampleConfigGenerator(KogitoBuildContext context) {
        this.generator = TemplatedGenerator.builder()
                .withTemplateBasePath("class-templates/config")
                .build(context, "SampleConfig");
    }

    @Override
    public String configClassName() {
        return "SampleConfig";
    }

    @Override
    public GeneratedFile generate() {
        CompilationUnit compilationUnit = generator.compilationUnitOrThrow();
        return new GeneratedFile(APPLICATION_CONFIG_TYPE,
                generator.generatedFilePath(),
                compilationUnit.toString());
    }
}
