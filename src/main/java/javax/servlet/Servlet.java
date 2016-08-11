/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 *
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright 2004 The Apache Software Foundation
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
 */

package javax.servlet;

import java.io.IOException;

/**
 * Defines methods that all servlets must implement.
 *
 * <p>A servlet is a small Java program that runs within a Web server.
 * Servlets receive and respond to requests from Web clients,
 * usually across HTTP, the HyperText Transfer Protocol. 
 *
 * <p>To implement this interface, you can write a generic servlet
 * that extends
 * <code>javax.servlet.GenericServlet</code> or an HTTP servlet that
 * extends <code>javax.servlet.http.HttpServlet</code>.
 *
 * <p>This interface defines methods to initialize a servlet,
 * to service requests, and to remove a servlet from the server.
 * These are known as life-cycle methods and are called in the
 * following sequence:
 * <ol>
 * <li>The servlet is constructed, then initialized with the <code>init</code> method.
 * <li>Any calls from clients to the <code>service</code> method are handled.
 * <li>The servlet is taken out of service, then destroyed with the 
 * <code>destroy</code> method, then garbage collected and finalized.
 * </ol>
 *
 * <p>In addition to the life-cycle methods, this interface
 * provides the <code>getServletConfig</code> method, which the servlet 
 * can use to get any startup information, and the <code>getServletInfo</code>
 * method, which allows the servlet to return basic information about itself,
 * such as author, version, and copyright.
 *
 * <p>定义所有 servlet 都必须实现的方法集。
 *
 * <p>Servlet 是一个小的 Java 程序，运行在 Web 服务器。
 * Servlets 通常通过 HTTP 协议来接收和响应来自 Web 客户端的请求。
 *
 * <p>为了实现本接口，可以编写一个通用的继承 GenericServlet 的 servlet 实现
 * 或者继承 HttpServlet 的 HTTP servlet 实现。
 *
 * <p>本接口定义的方法用于初始化 servlet，为请求提供服务，从服务器删除 servlet。
 * 这些方法被称为生命周期方法，并按照下面的顺序调用这些方法：
 * <ol>
 * <li>构建 servlet 时，使用 init 方法来初始化
 * <li>由 service 方法来处理任何来自客户端的调用
 * <li>servlet 从服务中取出时，使用 destroy 方法来销毁，接着由垃圾收集器收集和终止
 * </ol>
 *
 * <p>除生命周期方法外，本接口还提供给 servlet 获取任何启动信息的 getServletConfig 方法和
 * 允许 servlet 返回关于自身的基本信息（作者、版本、版权）的 getServletInfo 方法。
 *
 * @author 	Various
 *
 * @see 	GenericServlet
 * @see 	javax.servlet.http.HttpServlet
 *
 */
// 核心接口 请求处理程序，用来接收和响应来自Web客户端的请求
public interface Servlet {

    /**
     * Called by the servlet container to indicate to a servlet that the 
     * servlet is being placed into service.
     *
     * <p>The servlet container calls the <code>init</code>
     * method exactly once after instantiating the servlet.
     * The <code>init</code> method must complete successfully
     * before the servlet can receive any requests.
     *
     * <p>The servlet container cannot place the servlet into service
     * if the <code>init</code> method
     * <ol>
     * <li>Throws a <code>ServletException</code>
     * <li>Does not return within a time period defined by the Web server
     * </ol>
     *
     * <p>由 servlet 容器调用来表明一个 servlet 正在被放置到服务中。
     *
     * <p>在实例化 servlet 之后，servlet 容器只会调用一次 init 方法。
     * 在 servlet 可以接收到任何请求之前，init 方法必须成功完成。
     *
     * <p>如果 init 方法抛出一个Servlet异常，或者在一段时间内不能返回(由Web服务器定义，超时机制)，
     * servlet 容器不能将 servlet 放置到服务中。
     *
     *
     * @param config	a <code>ServletConfig</code> object
     *					containing the servlet's
     * 					configuration and initialization parameters (包含 Servlet 的配置信息和初始化参数)
     *
     * @exception ServletException 	if an exception has occurred that
     *					interferes with the servlet's normal
     *					operation
     *
     * @see 				UnavailableException
     * @see 				#getServletConfig
     *
     */
    // 核心方法 初始化Servlet并放置到服务中
    void init(ServletConfig config) throws ServletException;
    
    
    /**
     * Returns a {@link ServletConfig} object, which contains
     * initialization and startup parameters for this servlet.
     * The <code>ServletConfig</code> object returned is the one 
     * passed to the <code>init</code> method.
     *
     * <p>Implementations of this interface are responsible for storing the 
     * <code>ServletConfig</code> object so that this 
     * method can return it. The {@link GenericServlet}
     * class, which implements this interface, already does this.
     *
     * <p>返回 servlet 配置对象，其包含此 servlet 的初始化和启动参数。
     * 返回的 servlet 配置对象被传递到 init 方法。
     *
     * <p>此接口的实现负责存储 servlet 配置对象，这样本方法才能返回它。
     * 实现了本接口的 {@link GenericServlet} 类已经这样做了。
     *
     * @return		the <code>ServletConfig</code> object
     *			that initializes this servlet (初始化该 Servlet)
     *
     * @see 		#init
     *
     */
    // 核心方法 获取Servlet配置（Servlet上下文入口）
    ServletConfig getServletConfig();
    
    
    /**
     * Called by the servlet container to allow the servlet to respond to 
     * a request.
     *
     * <p>This method is only called after the servlet's <code>init()</code>
     * method has completed successfully.
     * 
     * <p>  The status code of the response always should be set for a servlet 
     * that throws or sends an error.
     *
     * <p>Servlets typically run inside multithreaded servlet containers
     * that can handle multiple requests concurrently. Developers must 
     * be aware to synchronize access to any shared resources such as files,
     * network connections, and as well as the servlet's class and instance 
     * variables. 
     * More information on multithreaded programming in Java is available in 
     * <a href="http://java.sun.com/Series/Tutorial/java/threads/multithreaded.html">
     * the Java tutorial on multi-threaded programming</a>.
     *
     * <p>由 servlet 容器调用以允许 Servlet 响应请求。
     *
     * <p>本方法仅在 servlet 的 init 方法已成功完成之后，才会被调用。
     *
     * <p>响应的状态代码总是应该被设置，即使 servlet 抛出或发送错误。
     *
     * <p>Servlets 通常运行在多线程的 servlet 容器中，容器可以同时处理多个请求。
     * 开发人员必须小心谨慎地同步访问任何共享的资源(如文件、网络连接)以及 servlet 类和实例变量。
     * Java 多线程编程的更多信息，可以在 <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/threads.html">
     *     Java 多线程编程的教程</a>找到。
     *
     *
     * @param req 	the <code>ServletRequest</code> object that contains
     *			the client's request (包含客户端请求的 Servlet 请求对象)
     *
     * @param res 	the <code>ServletResponse</code> object that contains
     *			the servlet's response (包含 servlet 响应的 Servlet 响应对象)
     *
     * @exception ServletException 	if an exception occurs that interferes
     *					with the servlet's normal operation (如果发生的异常干扰了 servlet 正常运行)
     *
     * @exception IOException 		if an input or output exception occurs (输入或输出发生异常)
     *
     */
    // 核心方法 响应请求
    void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException;
	
	
    /**
     * Returns information about the servlet, such
     * as author, version, and copyright.
     * 
     * <p>The string that this method returns should
     * be plain text and not markup of any kind (such as HTML, XML,
     * etc.).
     *
     * <p>返回有关 servlet 的信息。
     *
     * @return 		a <code>String</code> containing servlet information
     *
     */
    String getServletInfo();
    

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being taken out of service.  This method is
     * only called once all threads within the servlet's
     * <code>service</code> method have exited or after a timeout
     * period has passed. After the servlet container calls this 
     * method, it will not call the <code>service</code> method again
     * on this servlet.
     *
     * <p>This method gives the servlet an opportunity 
     * to clean up any resources that are being held (for example, memory,
     * file handles, threads) and make sure that any persistent state is
     * synchronized with the servlet's current state in memory.
     *
     * <p>由 servlet 容器调用来表明一个 servlet 正从服务中取出。
     * 本方法只会被调用一次，当 servlet 的 service 方法内的所有线程都已退出或者超过了超时时间之后。
     * 在 servlet 容器调用此方法之后，这个 servlet 将不会再调用 service 方法。
     *
     * <p>本方法使 servlet 有机会来清理正持有的任何资源(例如 内存、文件句柄、线程)，
     * 并确保任何持久性状态与内存中的 servlet 的当前状态保持同步。
     *
     */
    // 核心方法 servlet正从服务中取出(下线)并释放资源
    void destroy();
}
