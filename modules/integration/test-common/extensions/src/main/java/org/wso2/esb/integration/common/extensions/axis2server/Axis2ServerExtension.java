/*
*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.esb.integration.common.extensions.axis2server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.automation.engine.extensions.interfaces.ExecutionListenerExtension;

public class Axis2ServerExtension implements ExecutionListenerExtension {
    Axis2ServerManager serverManager;
    private static final Log log = LogFactory.getLog(Axis2ServerExtension.class);
    private String executionEnvironment;

    public void initiate() throws Exception {

    }

    public void onExecutionStart() throws Exception {
        serverManager = new Axis2ServerManager();
        serverManager.start();
        log.info(".................Deploying services..............");
        serverManager.deployService(ServiceNameConstants.LB_SERVICE_1);
        serverManager.deployService(ServiceNameConstants.SIMPLE_STOCK_QUOTE_SERVICE);
        serverManager.deployService(ServiceNameConstants.SECURE_STOCK_QUOTE_SERVICE);
        serverManager.deployService(ServiceNameConstants.SIMPLE_AXIS2_SERVICE);
    }

    public void onExecutionFinish() throws Exception {
        serverManager.stop();
    }

    private static void handleException(String msg, Exception e) {
        log.error(msg, e);
        throw new RuntimeException(msg, e);
    }
}
