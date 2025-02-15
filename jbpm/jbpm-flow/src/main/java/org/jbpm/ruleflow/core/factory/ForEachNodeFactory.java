/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.instance.impl.Action;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.DataDefinition;
import org.jbpm.workflow.core.node.CompositeContextNode;
import org.jbpm.workflow.core.node.ForEachNode;

public class ForEachNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractCompositeNodeFactory<ForEachNodeFactory<T>, T> {

    public static final String METHOD_COLLECTION_EXPRESSION = "collectionExpression";
    public static final String METHOD_OUTPUT_COLLECTION_EXPRESSION = "outputCollectionExpression";
    public static final String METHOD_INPUT_VARIABLE = "variable";
    public static final String METHOD_OUTPUT_VARIABLE = "outputVariable";
    public static final String METHOD_OUTPUT_TEMP = "tempVariable";
    public static final String METHOD_SEQUENTIAL = "sequential";

    public ForEachNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new ForEachNode(), id);
    }

    protected ForEachNode getForEachNode() {
        return (ForEachNode) node;
    }

    public ForEachNodeFactory<T> collectionExpression(String collectionExpression) {
        getForEachNode().setCollectionExpression(collectionExpression);
        getForEachNode().getMultiInstanceSpecification().setLoopDataInputRef(DataDefinition.toExpression(collectionExpression));
        return this;
    }

    @Override
    protected CompositeContextNode getCompositeNode() {
        return getForEachNode().getCompositeNode();
    }

    @Override
    public ForEachNodeFactory<T> variable(String variableName, DataType dataType) {
        return variable(variableName, variableName, dataType);
    }

    public ForEachNodeFactory<T> variable(String varRef, String variableName, DataType dataType) {
        getForEachNode().setInputRef(variableName);
        getForEachNode().addContextVariable(varRef, variableName, dataType);
        getForEachNode().getMultiInstanceSpecification().setInputDataItem(new DataDefinition(varRef, variableName, dataType.getStringType()));
        return this;
    }

    public ForEachNodeFactory<T> outputCollectionExpression(String collectionExpression) {
        getForEachNode().setOutputCollectionExpression(collectionExpression);
        getForEachNode().getMultiInstanceSpecification().setLoopDataOutputRef(DataDefinition.toExpression(collectionExpression));
        return this;
    }

    public ForEachNodeFactory<T> expressionLanguage(String exprLanguage) {
        getForEachNode().setExpressionLanguage(exprLanguage);
        return this;
    }

    public ForEachNodeFactory<T> completionAction(Action completionAction) {
        getForEachNode().setCompletionAction(completionAction);
        return this;
    }

    public ForEachNodeFactory<T> outputVariable(String variableName, DataType dataType) {
        return outputVariable(variableName, variableName, dataType);
    }

    public ForEachNodeFactory<T> tempVariable(String variableName, DataType dataType) {
        getForEachNode().addContextVariable(variableName, variableName, dataType);
        return this;
    }

    public ForEachNodeFactory<T> tempVariable(String varRef, String variableName, DataType dataType) {
        getForEachNode().addContextVariable(varRef, variableName, dataType);
        return this;
    }

    public ForEachNodeFactory<T> outputVariable(String varRef, String variableName, DataType dataType) {
        getForEachNode().setOutputRef(variableName);
        getForEachNode().addContextVariable(varRef, variableName, dataType);
        getForEachNode().getMultiInstanceSpecification().setOutputDataItem(new DataDefinition(varRef, variableName, dataType.getStringType()));
        return this;
    }

    public ForEachNodeFactory<T> waitForCompletion(boolean waitForCompletion) {
        getForEachNode().setWaitForCompletion(waitForCompletion);
        return this;
    }

    public ForEachNodeFactory<T> sequential(boolean sequential) {
        getForEachNode().setSequential(sequential);
        return this;
    }
}
