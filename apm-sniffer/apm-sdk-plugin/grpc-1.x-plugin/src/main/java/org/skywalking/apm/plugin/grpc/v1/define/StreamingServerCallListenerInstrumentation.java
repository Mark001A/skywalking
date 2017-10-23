/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.plugin.grpc.v1.define;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.skywalking.apm.agent.core.plugin.match.ClassMatch;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.skywalking.apm.agent.core.plugin.match.NameMatch.byName;

/**
 * {@link StreamingServerCallHandlerInstrumentation} presents that skywalking intercept the <code>onReady</code> method
 * by <code>org.skywalking.apm.plugin.grpc.v1.ServerCallOnReadyInterceptor</code>, the <code>onHalfClose</code> method
 * by <code>org.skywalking.apm.plugin.grpc.v1.ServerCallOnCloseInterceptor</code>, the <code>onMessage</code> method by
 * <code>org.skywalking.apm.plugin.grpc.v1.ServerCallOnMessageInterceptor</code> and the <code>onCancel</code> method by
 * <code>org.skywalking.apm.plugin.grpc.v1.ServerCallOnCancelInterceptor</code> in
 * <code>io.grpc.stub.ServerCalls$StreamingServerCallHandler$StreamingServerCallListener</code> class
 *
 * @author zhangxin
 */
public class StreamingServerCallListenerInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {
    private static final String ENHANCE_CLASS = "io.grpc.stub.ServerCalls$StreamingServerCallHandler$StreamingServerCallListener";
    public static final String ON_READY_METHOD = "onReady";
    public static final String ON_READ_INTERCEPT_CLASS = "org.skywalking.apm.plugin.grpc.v1.ServerCallOnReadyInterceptor";
    public static final String ON_HALF_CLOSE_METHOD = "onHalfClose";
    public static final String ON_HALF_CLOSE_INTERCEPT_CLASS = "org.skywalking.apm.plugin.grpc.v1.ServerCallOnCloseInterceptor";
    public static final String ON_MESSAGE_METHOD = "onMessage";
    public static final String ON_MESSAGE_INTERCEPT_CLASS = "org.skywalking.apm.plugin.grpc.v1.ServerCallOnMessageInterceptor";
    public static final String ON_CANCEL_METHOD = "onCancel";
    public static final String ON_CANCEL_INTERCEPT_CLASS = "org.skywalking.apm.plugin.grpc.v1.ServerCallOnCancelInterceptor";

    @Override protected ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[0];
    }

    @Override protected InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(ON_READY_METHOD);
                }

                @Override public String getMethodsInterceptor() {
                    return ON_READ_INTERCEPT_CLASS;
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(ON_HALF_CLOSE_METHOD);
                }

                @Override public String getMethodsInterceptor() {
                    return ON_HALF_CLOSE_INTERCEPT_CLASS;
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(ON_MESSAGE_METHOD);
                }

                @Override public String getMethodsInterceptor() {
                    return ON_MESSAGE_INTERCEPT_CLASS;
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(ON_CANCEL_METHOD);
                }

                @Override public String getMethodsInterceptor() {
                    return ON_CANCEL_INTERCEPT_CLASS;
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            }
        };
    }

    @Override protected ClassMatch enhanceClass() {
        return byName(ENHANCE_CLASS);
    }
}